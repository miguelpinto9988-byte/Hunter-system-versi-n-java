#!/usr/bin/env bash
set -euo pipefail

cd "$(dirname "$0")/.."
readonly version=2.1.21
readonly maven=https://repo.maven.apache.org/maven2
readonly cache="${XDG_CACHE_HOME:-$HOME/.cache}/hunter-kotlin/$version"
readonly destination="$PWD/build/hunter-web"
mkdir -p "$cache" build

download() {
    local path="$1" file="$cache/${1##*/}"
    if [[ ! -s "$file" ]]; then
        local tmp
        tmp=$(mktemp "$cache/download.XXXXXX")
        if ! curl -fsSL "$maven/$path" -o "$tmp"; then
            rm -f "$tmp"
            return 1
        fi
        mv "$tmp" "$file"
    fi
}

for artifact in kotlin-compiler kotlin-stdlib kotlin-script-runtime; do
    download "org/jetbrains/kotlin/$artifact/$version/$artifact-$version.jar"
done
download "org/jetbrains/kotlin/kotlin-stdlib-js/$version/kotlin-stdlib-js-$version.klib"
download org/jetbrains/kotlin/kotlin-reflect/1.6.10/kotlin-reflect-1.6.10.jar
download org/jetbrains/kotlinx/kotlinx-coroutines-core-jvm/1.8.0/kotlinx-coroutines-core-jvm-1.8.0.jar
download org/jetbrains/intellij/deps/trove4j/1.0.20200330/trove4j-1.0.20200330.jar

work=$(mktemp -d "$PWD/build/.hunter-web.XXXXXX")
readonly output="$work/site"
trap 'rm -rf "$work"' EXIT
compiler=(java -cp "$cache/*" org.jetbrains.kotlin.cli.js.K2JSCompiler)
library="$cache/kotlin-stdlib-js-$version.klib"
compile() {
    local name="$1"
    shift
    local digest artifact source
    digest=$({
        declare -f compile
        for source in "$@"; do
            basename "$source"
            sha256sum < "$source"
        done
    } | sha256sum | cut -d' ' -f1)
    artifact="$cache/modules/$name-$digest.js"
    if [[ -s "$artifact" ]]; then
        mkdir -p "$work/$name"
        cp "$artifact" "$work/$name/$name.js"
        return
    fi
    "${compiler[@]}" -libraries "$library" -Xir-produce-klib-file \
        -ir-output-dir "$work" -ir-output-name "$name" "$@"
    "${compiler[@]}" -libraries "$library" -Xinclude="$work/$name.klib" \
        -Xir-produce-js -Xir-dce -module-kind plain -main call \
        -ir-output-dir "$work/$name" -ir-output-name "$name"
    mkdir -p "$cache/modules"
    local staging
    staging=$(mktemp "$cache/modules/compiled.XXXXXX")
    cp "$work/$name/$name.js" "$staging"
    mv "$staging" "$artifact"
}
python3 tools/incluir_datos_kotlin.py kotlin-web/data/catalog46.json "$work/CatalogData.kt" catalogPayload
compile routine-sequence kotlin-web/RoutineSequence.kt
compile hunter-guided kotlin-web/GuidedSession.kt kotlin-web/GuidedScreen.kt
compile blocks-v22 kotlin-web/SessionBlocks.kt
compile modal-viewport kotlin-web/ModalViewport.kt
compile bootstrap-v20 kotlin-web/Bootstrap.kt
compile boot-v37 kotlin-web/Boot.kt
compile hunter-sync3879 kotlin-web/ProfileSync.kt
compile hunter-profile3878 kotlin-web/UnifiedProfile.kt
compile HunterResources kotlin-web/Resources.kt
compile hunter-catalog3846 kotlin-web/Catalog.kt "$work/CatalogData.kt"
compile hunter-schedule3813 kotlin-web/Schedule.kt
compile hunter-native3875 kotlin-web/NativeBridge.kt
compile hunter-screens kotlin-web/Screens.kt kotlin-web/Calendar.kt kotlin-web/Goals.kt kotlin-web/Missions.kt kotlin-web/MissionWindow.kt kotlin-web/MissionEditor.kt
compile hunter-visibility kotlin-web/Visibility.kt
compile hunter-plan3810 kotlin-web/Planning.kt
compile hunter-constraints3811 kotlin-web/Constraints.kt
compile hunter-metadata3812 kotlin-web/Metadata.kt

# Los grupos se compilan por separado para limitar el uso de memoria de Kotlin/JS.
mapfile -t groups < <(python3 -c 'import json; print(*json.load(open("kotlin-web/app/modules.json"))["stages"], sep="\n")')
for group in "${groups[@]}"; do
    compile "$group" kotlin-web/app/BrowserInterop.kt kotlin-web/app/"$group"/*.kt
done

readonly fonts=https://raw.githubusercontent.com/google/fonts/main/ofl
mkdir -p "$cache/fonts" "$output/fonts"
while IFS=, read -r asset size hash status; do
    [[ "$asset" == assets/fonts/*.ttf ]] || continue
    name=${asset##*/}
    case "$name" in
        BarlowCondensed-*) family=barlowcondensed ;;
        PirataOne-*) family=pirataone ;;
        *) exit 1 ;;
    esac
    if ! echo "$hash  $cache/fonts/$name" | sha256sum --check --status 2>/dev/null; then
        curl -fsSL "$fonts/$family/$name" -o "$work/$name"
        echo "$hash  $work/$name" | sha256sum --check --status
        mv "$work/$name" "$cache/fonts/$name"
    fi
    if [[ ! -s "$cache/fonts/$family-OFL.txt" ]]; then
        curl -fsSL "$fonts/$family/OFL.txt" -o "$work/$family-OFL.txt"
        mv "$work/$family-OFL.txt" "$cache/fonts/$family-OFL.txt"
    fi
    cp "$cache/fonts/$name" "$cache/fonts/$family-OFL.txt" "$output/fonts/"
done < inventario_apk_sha256.csv

# La APK aporta HTML, estilos y medios. Todos los scripts se construyen desde Kotlin o JSON.
python3 - "$output" <<'PYTHON'
from pathlib import Path
import shutil
import sys
target = Path(sys.argv[1])
shutil.copytree("apk_contents/assets", target, dirs_exist_ok=True, ignore=shutil.ignore_patterns("*.js"))
PYTHON
for name in modal-viewport bootstrap-v20 boot-v37 HunterResources hunter-catalog3846 hunter-schedule3813 \
    hunter-native3875 hunter-screens hunter-guided routine-sequence blocks-v22 hunter-plan3810 \
    hunter-constraints3811 hunter-metadata3812; do
    cp "$work/$name/$name.js" "$output/"
done
{
    cat "$work/hunter-visibility/hunter-visibility.js"
    printf '\n;\n/* Hunter 38.78 perfil */\n'
    cat "$work/hunter-profile3878/hunter-profile3878.js"
    printf '\n;\n/* Hunter System 38.79 sincronizacion */\n'
    cat "$work/hunter-sync3879/hunter-sync3879.js"
} > "$output/hunter-visibility3874.js"
python3 tools/enlazar_recursos_kotlin.py "$output"
python3 tools/enlazar_aplicacion_kotlin.py "$output" "$work"
if [[ -e "$destination" ]]; then
    mv "$destination" "$work/previous"
fi
if ! mv "$output" "$destination"; then
    [[ ! -e "$work/previous" ]] || mv "$work/previous" "$destination"
    exit 1
fi
