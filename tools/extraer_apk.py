#!/usr/bin/env python3
"""Extrae sin modificar las entradas internas de una APK local."""
import argparse, pathlib, zipfile
parser=argparse.ArgumentParser()
parser.add_argument('apk',type=pathlib.Path)
parser.add_argument('--destino',type=pathlib.Path,default=pathlib.Path('apk_contents_completo'))
a=parser.parse_args(); n=0
with zipfile.ZipFile(a.apk) as z:
 if z.testzip() is not None: raise ValueError('APK dañada')
 for entry in z.infolist():
  path=pathlib.PurePosixPath(entry.filename)
  if path.is_absolute() or '..' in path.parts: raise ValueError('Ruta insegura: '+entry.filename)
  if entry.is_dir(): continue
  out=a.destino/pathlib.Path(*path.parts)
  out.parent.mkdir(parents=True,exist_ok=True)
  out.write_bytes(z.read(entry.filename)); n+=1
print(f'Extraidos {n} archivos en {a.destino}')
