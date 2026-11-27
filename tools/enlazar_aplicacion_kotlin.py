"""Activa los módulos Kotlin en el mismo orden de inicialización de la APK."""
import argparse
import json
from pathlib import Path

manifest = Path(__file__).resolve().parents[1] / "kotlin-web/app/modules.json"


def write(path, data):
    stage = path.with_suffix(path.suffix + ".tmp")
    stage.write_bytes(data)
    stage.replace(path)


def link(output, compiled_path):
    config = json.loads(manifest.read_text())
    page = output / "index.html"
    markup = page.read_text()
    entry = '<script defer src="app.js"></script>'
    if markup.count(entry) != 1:
        raise ValueError("Punto de carga ambiguo para las pantallas")
    markup = markup.replace(entry, '<script defer src="hunter-screens.js"></script>'
                            '<script defer src="hunter-guided.js"></script>' + entry)
    loaded = set()
    for filename, parts in config["links"].items():
        segments = []
        dependencies = []
        for part in parts:
            if "compiled" in part:
                segments.append((output / part["compiled"]).read_text())
                continue
            group = part["group"]
            script = compiled_path / group / (group + ".js")
            if group not in loaded:
                write(output / (group + ".js"), script.read_bytes())
                dependencies.append(f'<script defer src="{group}.js"></script>')
                loaded.add(group)
            for kind in ("var", "let"):
                bindings = dict.fromkeys(symbol["name"] for symbol in part["top"]
                                         if (symbol["kind"] == "var") == (kind == "var"))
                if kind == "var":
                    segments.extend(f"function {name}(){{}}" for name in bindings)
                elif bindings:
                    segments.append(kind + " " + ", ".join(bindings) + ";")
            segments.append(f'window.HunterKotlinModules[{json.dumps(part["unit"])}]();')
        script_tag = f'<script defer src="{filename}"></script>'
        if markup.count(script_tag) != 1:
            raise ValueError(f"Punto de carga ambiguo para {filename}")
        markup = markup.replace(script_tag, "".join(dependencies) + script_tag)
        write(output / filename, "\n".join(segments).encode())
    write(page, markup.encode())


if __name__ == "__main__":
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("output", type=Path)
    parser.add_argument("compiled_path", type=Path)
    args = parser.parse_args()
    link(args.output, args.compiled_path)
