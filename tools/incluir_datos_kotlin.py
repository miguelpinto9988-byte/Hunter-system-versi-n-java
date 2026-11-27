"""Crea una constante Kotlin/JS a partir de un recurso JSON para uso sin conexión."""
import json
import sys
from pathlib import Path


def kotlin_data(source, destination, function_name):
    payload = source.read_text(encoding="utf-8")
    json.loads(payload)
    chunks = [payload[index:index + 8192] for index in range(0, len(payload), 8192)]
    literals = [json.dumps(chunk, ensure_ascii=True).replace("$", "\\$") for chunk in chunks]
    destination.write_text(
        f"internal fun {function_name}(): dynamic {{\n"
        '    val json: dynamic = js("JSON")\n'
        "    val chunks = arrayOf(\n        " + ",\n        ".join(literals) + "\n    )\n"
        '    return json.parse(chunks.joinToString(""))\n}\n',
        encoding="utf-8",
    )


if __name__ == "__main__":
    kotlin_data(Path(sys.argv[1]), Path(sys.argv[2]), sys.argv[3])
