"""Enlaza datos JSON con Kotlin/JS conservando las declaraciones de los scripts clásicos."""
import json
import sys
from pathlib import Path


# Los enlaces const pertenecen al ámbito léxico global del documento.
RESOURCES = (
    ("anatomy-surface", "window.AnatomySurface15", False),
    ("asterion-data3868", "window.HunterPortableData3868", True),
    ("creator-research", "window.CreatorResearch", False),
    ("creator-seed", "window.CreatorSeed", False),
    ("hunter-groups3849-data", "window.HunterGroupRows49", False),
    ("offline-manifest-v20", "window.OfflineManifest20", False),
    ("motions", "const MotionDB", False),
    ("hd-motion-v16", "const HDMotion16", True),
    ("demos", "const DemoLibrary", False),
)


def link(output):
    for filename, binding, frozen in RESOURCES:
        payload = Path("kotlin-web/data", filename + ".json").read_text(encoding="utf-8")
        json.loads(payload)
        literal = json.dumps(payload, ensure_ascii=True)
        statement = f"{binding} = HunterResources.decodeResource({literal}, {json.dumps(frozen)});\n"
        (output / (filename + ".js")).write_text(statement, encoding="utf-8")
    page = output / "index.html"
    markup = page.read_bytes()
    first_deferred = b'<script defer src="hunter-bundle3874-01.js"></script>'
    if markup.count(first_deferred) != 1:
        raise ValueError("Punto de arranque de recursos ambiguo")
    page.write_bytes(markup.replace(first_deferred, b'<script defer src="HunterResources.js"></script>' + first_deferred))


if __name__ == "__main__":
    link(Path(sys.argv[1]))
