#!/usr/bin/env python3
"""Repackage the recovered Android host with the compiled Kotlin/JS assets.
This is a test build, not a native Kotlin Android port.
"""
import struct
from pathlib import Path
from zipfile import ZipFile, ZIP_STORED, ZIP_DEFLATED

ROOT = Path(__file__).resolve().parents[1]
HOST = ROOT / "apk_contents"
WEB = ROOT / "build/hunter-web"
OUT = ROOT / "build/HunterSystem_38.80_Kotlin-unsigned.apk"
OLD_PACKAGE = "com.huntersystem.p78"
TEST_PACKAGE = "com.huntersystem.k80"

def patch_manifest(data):
    # Preserve compiled resource IDs and all XML chunks other than the string
    # pool and the manifest versionCode. The recovered manifest uses UTF-16.
    assert struct.unpack_from("<HHI", data, 0) == (3, 8, len(data))
    kind, header, size, count, styles, flags, strings, styles_start = struct.unpack_from("<HHI5I", data, 8)
    assert (kind, header, styles, styles_start) == (1, 28, 0, 0)
    assert not flags & 0x100, "Unexpected UTF-8 string pool"
    values = []
    for i in range(count):
        off = struct.unpack_from("<I", data, 8 + header + 4*i)[0]
        p = 8 + strings + off
        length = struct.unpack_from("<H", data, p)[0]
        assert length < 0x8000
        values.append(data[p+2:p+2+2*length].decode("utf-16le"))
    assert OLD_PACKAGE in values and "38.78" in values and "Hunter v38.78" in values
    # Keep old fully qualified class names because classes.dex is unchanged.
    names = {OLD_PACKAGE: TEST_PACKAGE, "38.78": "38.80", "Hunter v38.78": "Hunter v38.80"}
    for relative in (".MainActivity", ".ReminderReceiver", ".CreatorSyncJob"):
        assert relative in values
        names[relative] = OLD_PACKAGE + relative
    offsets, payload = [], bytearray()
    for value in values:
        value = names.get(value, value)
        raw = value.encode("utf-16le")
        offsets.append(len(payload))
        payload += struct.pack("<H", len(raw)//2) + raw + b"\0\0"
    payload += b"\0" * (-len(payload) % 4)
    start = header + 4*count
    pool = struct.pack("<HHI5I", 1, header, start+len(payload), count, 0, flags & ~1, start, 0)
    pool += struct.pack("<" + "I"*count, *offsets) + payload
    result = bytearray(data[:8] + pool + data[8+size:])
    struct.pack_into("<I", result, 4, len(result))
    p = 8 + len(pool)
    changed = False
    while p < len(result):
        typ, hdr, n = struct.unpack_from("<HHI", result, p)
        assert n >= hdr >= 8 and p+n <= len(result)
        if typ == 0x102:
            name = struct.unpack_from("<I", result, p+20)[0]
            if values[name] == "manifest":
                attr_start, attr_size, attr_count = struct.unpack_from("<HHH", result, p+24)
                assert attr_size == 20
                for i in range(attr_count):
                    a = p+16+attr_start+i*attr_size
                    idx = struct.unpack_from("<I", result, a+4)[0]
                    if values[idx] == "versionCode":
                        assert result[a+15] == 0x10
                        struct.pack_into("<I", result, a+16, 134)
                        changed = True
        p += n
    assert changed
    return result

def add_file(z, source, name):
    stored = name in ("resources.arsc", "AndroidManifest.xml") or source.suffix.lower() in {
        ".mp4", ".webm", ".webp", ".png", ".jpg", ".jpeg", ".wav", ".mp3", ".ogg", ".ttf", ".so"
    }
    z.write(source, name, compress_type=ZIP_STORED if stored else ZIP_DEFLATED)

assert (WEB / "index.html").is_file(), "Run tools/compilar_web_kotlin.sh first"
assert list(WEB.rglob("*.js")), "Compiled Kotlin/JS output missing"
assert (HOST / "classes.dex").is_file() and (HOST / "resources.arsc").is_file()
OUT.parent.mkdir(parents=True, exist_ok=True)
with ZipFile(OUT, "w", compression=ZIP_DEFLATED, compresslevel=6) as z:
    z.writestr("AndroidManifest.xml", patch_manifest((HOST/"AndroidManifest.xml").read_bytes()), compress_type=ZIP_STORED)
    for source in sorted(HOST.rglob("*")):
        if not source.is_file():
            continue
        rel = source.relative_to(HOST)
        if rel.parts[0] in ("assets", "META-INF") or rel.as_posix() == "AndroidManifest.xml":
            continue
        add_file(z, source, rel.as_posix())
    for source in sorted(WEB.rglob("*")):
        if source.is_file():
            add_file(z, source, "assets/" + source.relative_to(WEB).as_posix())
with ZipFile(OUT) as z:
    assert z.testzip() is None
    assert "assets/index.html" in z.namelist()
    assert len(z.namelist()) == len(set(z.namelist()))
print(OUT)
