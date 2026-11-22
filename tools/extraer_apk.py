#!/usr/bin/env python3
"""Extrae TODAS las entradas de una APK y prepara su contenido para GitHub.

Uso:
    python3 tools/extraer_apk.py HunterSystem_38.79_Mision_Dinamica_Actualizada.apk
    python3 tools/extraer_apk.py HunterSystem_38.79_Mision_Dinamica_Actualizada.apk --publicar

Ejecutar desde la raíz de un clon de este repositorio.
El contenido no recompila el APK: los fuentes nativos Java no están en la APK.
"""
import argparse
import csv
import hashlib
from pathlib import Path
import subprocess
import sys
import zipfile


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("apk", type=Path, help="Ruta del APK original 38.79")
    parser.add_argument("--publicar", action="store_true",
                        help="Hacer git add, commit y push origin main")
    args = parser.parse_args()

    repo = Path(__file__).resolve().parent.parent
    destination = repo / "apk_contents"
    inventory = repo / "inventario_apk_sha256.csv"

    if not args.apk.is_file():
        parser.error("El archivo APK indicado no existe")

    rows = []
    with zipfile.ZipFile(args.apk) as source:
        bad = source.testzip()
        if bad:
            raise ValueError(f"APK corrupta: {bad}")
        for item in source.infolist():
            if item.is_dir():
                continue
            relative = Path(item.filename)
            if relative.is_absolute() or ".." in relative.parts:
                raise ValueError(f"Ruta insegura en APK: {item.filename}")
            target = destination / relative
            target.parent.mkdir(parents=True, exist_ok=True)
            data = source.read(item.filename)
            target.write_bytes(data)
            rows.append((item.filename, len(data), hashlib.sha256(data).hexdigest()))

    with inventory.open("w", newline="", encoding="utf-8") as out:
        writer = csv.writer(out)
        writer.writerow(["ruta_en_apk", "bytes", "sha256"])
        writer.writerows(rows)

    print(f"Extraídos y verificados {len(rows)} archivos en {destination}")
    print(f"Inventario: {inventory}")
    print(f"SHA-256 APK: {hashlib.sha256(args.apk.read_bytes()).hexdigest()}")

    if args.publicar:
        expected = "b722d7112e3e73ff74c608579efb8129fe3c7e26e60ba3ce68e44ddb513fc40e"
        actual = hashlib.sha256(args.apk.read_bytes()).hexdigest()
        if actual != expected:
            raise ValueError("Esta APK no coincide con la versión 38.79 verificada. No se publicará.")
        for command in (
            ["git", "add", "-A", "apk_contents", "inventario_apk_sha256.csv"],
            ["git", "commit", "-m", "Importar contenido completo de APK Hunter System 38.79"],
            ["git", "push", "origin", "main"],
        ):
            print("+", " ".join(command))
            subprocess.run(command, cwd=repo, check=True)


if __name__ == "__main__":
    try:
        main()
    except (OSError, zipfile.BadZipFile, ValueError, subprocess.CalledProcessError) as exc:
        sys.exit(str(exc))
