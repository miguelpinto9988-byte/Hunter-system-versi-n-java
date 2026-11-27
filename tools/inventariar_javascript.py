"""Enumera el JavaScript de la APK y el cargado por la compilación Kotlin."""
import argparse
import json
from html.parser import HTMLParser
from pathlib import Path
from urllib.parse import urlsplit


class ScriptInventory(HTMLParser):
    def __init__(self):
        super().__init__()
        self.scripts = []
        self.inline_scripts = 0
        self.inline_handlers = 0

    def handle_starttag(self, tag, attrs):
        attributes = dict(attrs)
        self.inline_handlers += sum(name.startswith("on") for name, _ in attrs)
        if tag != "script":
            return
        if "src" not in attributes:
            self.inline_scripts += 1
            return
        self.scripts.append({
            "src": attributes["src"],
            "defer": "defer" in attributes,
            "async": "async" in attributes,
        })


def inventory(directory, reference=None):
    parser = ScriptInventory()
    parser.feed((directory / "index.html").read_text(encoding="utf-8"))
    assets = {path.relative_to(directory).as_posix() for path in directory.rglob("*.js")}
    direct = {urlsplit(script["src"]).path for script in parser.scripts}
    report = {
        "scripts_in_load_order": parser.scripts,
        "inline_scripts": parser.inline_scripts,
        "inline_handlers_in_entrypoint": parser.inline_handlers,
        "missing_direct_sources": sorted(direct - assets),
        "not_directly_referenced": sorted(assets - direct),
        "javascript_files": len(assets),
    }
    if reference is not None:
        report["identical_direct_sources"] = [
            path for path in sorted(direct & assets)
            if (reference / path).is_file()
            and (directory / path).read_bytes() == (reference / path).read_bytes()
        ]
    return report


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument("directory", type=Path)
    parser.add_argument("--reference", type=Path, help="Directorio original para comparar scripts de entrada")
    args = parser.parse_args()
    report = inventory(args.directory, args.reference)
    print(json.dumps(report, indent=2, ensure_ascii=False))
    return bool(report["missing_direct_sources"])


if __name__ == "__main__":
    raise SystemExit(main())
