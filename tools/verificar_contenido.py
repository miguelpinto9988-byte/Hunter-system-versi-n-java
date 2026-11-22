#!/usr/bin/env python3
"""Comprueba la integridad de una extracción del APK 38.79."""
import csv, hashlib, pathlib, sys
root=pathlib.Path(__file__).resolve().parents[1]
base=root/'apk_contents'
errors=[]; ok=0; omitted=0
with (root/'inventario_apk_sha256.csv').open(encoding='utf8',newline='') as f:
 for row in csv.DictReader(f):
  path=base/row['ruta_en_apk']
  if row['estado_en_paquete']!='incluido':
   omitted+=1; continue
  if not path.is_file(): errors.append('FALTA '+str(path)); continue
  if path.stat().st_size!=int(row['bytes']): errors.append('TAMANO '+str(path)); continue
  if hashlib.sha256(path.read_bytes()).hexdigest()!=row['sha256']: errors.append('HASH '+str(path)); continue
  ok+=1
print(f'Verificados: {ok}; omitidos expresamente: {omitted}; errores: {len(errors)}')
for e in errors[:40]:print(e,file=sys.stderr)
sys.exit(1 if errors else 0)
