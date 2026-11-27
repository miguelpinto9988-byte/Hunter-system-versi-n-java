@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installcataloguev30

import hunter.browser.*

@JsName("JSON") private external var global_JSON: dynamic
@JsName("Engine") private external var global_Engine: dynamic
@JsName("Error") private external var global_Error: dynamic
@JsName("ExercisePack23") private external var global_ExercisePack23: dynamic
@JsName("MotionDB") private external var global_MotionDB: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installCatalogueV30() {
    dynamicValue((fun(root: dynamic): dynamic {
        val cp: dynamic = (fun(x: dynamic): dynamic {
            return global_JSON.parse(global_JSON.stringify(x))
        })
        val gym: dynamic = jsonValue("[[\"press45\",\"Prensa de piernas a 45 grados\",\"gym-leg-press23\",\"legpress\",0,\"Piernas\",{\"angle\":45}],[\"presssingle\",\"Prensa de piernas unilateral\",\"gym-leg-press23\",\"legpress\",1,\"Piernas\",{\"single\":true}],[\"pendulum\",\"Sentadilla en máquina péndulo\",\"gym-hack23\",\"squat\",1,\"Piernas\",{\"pendulum\":true}],[\"belt\",\"Sentadilla en máquina con cinturón\",\"gym-hack23\",\"squat\",1,\"Piernas\",{\"belt\":true}],[\"vsquat\",\"Sentadilla en máquina de palanca en V\",\"gym-hack23\",\"squat\",2,\"Piernas\",{\"vlever\":true}],[\"smithsplit\",\"Sentadilla dividida en Smith\",\"dbsplit\",\"lunge\",1,\"Piernas\",{\"gear\":\"smith\",\"back\":true}],[\"smiththrust\",\"Hip thrust en máquina Smith\",\"gym-thrust-bb23\",\"hipthrust\",1,\"Cadera\",{\"gear\":\"smith\",\"bench\":true}],[\"smithrdl\",\"Peso muerto rumano en Smith\",\"gym-rdl-bb23\",\"hinge\",1,\"Piernas\",{\"gear\":\"smith\"}],[\"hamstanding\",\"Curl femoral de pie en máquina\",\"gym-ham-seated23\",\"hamcurl\",0,\"Piernas\",{\"standing\":true,\"single\":true}],[\"tibialis\",\"Dorsiflexión en máquina de tibial anterior\",\"toelift\",\"ankle\",0,\"Piernas\",{\"tibialis\":true,\"seated\":true}],[\"calfseat\",\"Elevación de talones en máquina sentado\",\"seatedcalf\",\"calf\",0,\"Piernas\",{\"seated\":true}],[\"calfdonkey\",\"Elevación de talones en máquina burro\",\"calf\",\"calf\",1,\"Piernas\",{\"hinged\":true}],[\"adduct\",\"Aducción de cadera en máquina\",\"gym-hip-machine23\",\"hip-machine\",0,\"Cadera\",{\"adduct\":true,\"seated\":true}],[\"kickmachine\",\"Extensión de cadera en máquina de glúteo\",\"standingextend\",\"hip-extension\",0,\"Cadera\",{\"support\":true,\"single\":true}],[\"thrustmachine\",\"Hip thrust en máquina de glúteo\",\"hipthrust\",\"hipthrust\",0,\"Cadera\",{\"belt\":true,\"bench\":true}],[\"abmachine\",\"Flexión abdominal en máquina\",\"curlup\",\"crunch\",1,\"Abdomen\",{\"seated\":true,\"spineMove\":true}],[\"backmachine\",\"Extensión de tronco sentado en máquina\",\"hinge\",\"back-extension\",1,\"Espalda\",{\"seated\":true,\"spineMove\":true}],[\"rotary\",\"Rotación controlada en máquina de torso\",\"seatedturn\",\"rotation\",1,\"Abdomen\",{\"seated\":true,\"spineMove\":true}],[\"pullassist\",\"Dominada en máquina asistida\",\"gym-lat-neutral23\",\"pullup\",1,\"Espalda\",{\"assisted\":true}],[\"dipassist\",\"Fondos en máquina asistida\",\"gym-rope-tricep23\",\"dip\",1,\"Brazos\",{\"assisted\":true}],[\"highrow\",\"Remo alto en máquina de palancas\",\"gym-cable-high-row23\",\"cable-row\",1,\"Espalda\",{\"high\":true,\"seated\":true}],[\"supportrow\",\"Remo en máquina con apoyo de pecho\",\"gym-cable-row23\",\"cable-row\",0,\"Espalda\",{\"chestSupport\":true,\"seated\":true}],[\"pressone\",\"Press de pecho unilateral en máquina\",\"gym-chest-machine23\",\"machine-press\",1,\"Pecho\",{\"single\":true,\"seated\":true}],[\"pressincline\",\"Press inclinado de pecho en máquina\",\"gym-chest-machine23\",\"machine-press\",1,\"Pecho\",{\"incline\":true,\"seated\":true}],[\"shoulderm\",\"Press de hombros en máquina\",\"seatedpress\",\"overhead\",0,\"Hombros\",{\"seated\":true}],[\"lateralm\",\"Elevación lateral en máquina\",\"seatedlateral\",\"lateral\",0,\"Hombros\",{\"seated\":true}],[\"reversedeck\",\"Apertura posterior en máquina pec deck\",\"reversefly\",\"fly\",0,\"Espalda\",{\"reverse\":true,\"seated\":true}],[\"preacherm\",\"Curl predicador en máquina\",\"gym-preacher-bb23\",\"curl\",0,\"Brazos\",{\"preacher\":true,\"seated\":true}],[\"tricepsm\",\"Extensión de tríceps sentado en máquina\",\"gym-rope-tricep23\",\"tricep\",0,\"Brazos\",{\"seated\":true}],[\"pulloverm\",\"Pullover sentado en máquina\",\"pullover\",\"straight-pull\",1,\"Espalda\",{\"seated\":true}],[\"hipflexc\",\"Flexión de cadera en polea con tobillera\",\"standingcore\",\"hip-flexion\",1,\"Cadera\",{\"gear\":\"cable\",\"single\":true}],[\"abductc\",\"Abducción de cadera en polea con tobillera\",\"standingabduct\",\"hip-abduction\",1,\"Cadera\",{\"gear\":\"cable\",\"single\":true}],[\"adductc\",\"Aducción de cadera en polea con tobillera\",\"standingabduct\",\"hip-adduction\",1,\"Cadera\",{\"gear\":\"cable\",\"single\":true}],[\"kickc\",\"Extensión de cadera en polea con tobillera\",\"standingextend\",\"hip-extension\",1,\"Cadera\",{\"gear\":\"cable\",\"single\":true}],[\"crunchc\",\"Flexión abdominal arrodillado en polea\",\"curlup\",\"crunch\",1,\"Abdomen\",{\"gear\":\"cable\",\"kneeling\":true,\"spineMove\":true}],[\"pullthrough\",\"Extensión de cadera entre piernas en polea\",\"gym-rdl-bb23\",\"hinge\",1,\"Cadera\",{\"gear\":\"cable\",\"pullthrough\":true}],[\"stepmill\",\"Subida continua en escaladora de peldaños\",\"stepup\",\"step\",0,\"Cardio\",{\"gear\":\"cardio\",\"cardio\":true}],[\"bike\",\"Pedaleo en bicicleta estática\",\"seatedmarch\",\"cycle\",0,\"Cardio\",{\"gear\":\"cardio\",\"cardio\":true}],[\"elliptical\",\"Paso coordinado en elíptica\",\"walk\",\"elliptical\",0,\"Cardio\",{\"gear\":\"cardio\",\"cardio\":true}],[\"skierg\",\"Tracción en ergómetro de esquí\",\"gym-straight-pull23\",\"ski\",1,\"Cardio\",{\"gear\":\"cardio\",\"cardio\":true}],[\"airbike\",\"Pedaleo y empuje alterno en bicicleta de aire\",\"seatedmarch\",\"cycle\",1,\"Cardio\",{\"gear\":\"cardio\",\"air\":true,\"cardio\":true}],[\"recumbent\",\"Pedaleo en bicicleta reclinada\",\"seatedmarch\",\"cycle\",0,\"Cardio\",{\"gear\":\"cardio\",\"reclined\":true,\"cardio\":true}]]")
        val home: dynamic = jsonValue("[[\"squatnarrow\",\"Sentadilla con postura estrecha\",\"squat\",\"squat\",1,\"Piernas\",{\"narrow\":true}],[\"squatheels\",\"Sentadilla con talones sobre cuña de ejercicio\",\"squat\",\"squat\",1,\"Piernas\",{\"heelWedge\":true}],[\"squatpulse\",\"Sentadilla con pulsos en recorrido inferior\",\"squat\",\"squat\",1,\"Piernas\",{\"pulses\":true}],[\"cyclistdb\",\"Sentadilla ciclista con mancuerna al pecho\",\"goblet\",\"squat\",2,\"Piernas\",{\"narrow\":true,\"heelWedge\":true,\"db\":true}],[\"lungefront\",\"Zancada al frente con mancuernas\",\"dblunge\",\"lunge\",1,\"Piernas\",{\"forward\":true,\"db\":true}],[\"lungeoffset\",\"Zancada atrás con mancuerna en un lado\",\"dblunge\",\"lunge\",2,\"Piernas\",{\"offset\":true,\"db\":true}],[\"splithigh\",\"Sentadilla dividida con pie delantero en plataforma\",\"dbsplit\",\"lunge\",2,\"Piernas\",{\"frontElevated\":true,\"step\":true,\"db\":true}],[\"rdlstagger\",\"Peso muerto rumano con apoyo en B\",\"rdl\",\"hinge\",1,\"Piernas\",{\"staggered\":true,\"db\":true}],[\"rdlsuit\",\"Peso muerto rumano con carga unilateral\",\"rdl\",\"hinge\",2,\"Piernas\",{\"singleLoad\":true,\"db\":true}],[\"bridgemarch\",\"Puente de glúteos con marcha alterna\",\"bridge\",\"bridge\",1,\"Cadera\",{\"march\":true}],[\"bridgesqueeze\",\"Puente de glúteos con presión de cojín\",\"bridge\",\"bridge\",0,\"Cadera\",{\"squeeze\":true}],[\"thrustsingle\",\"Hip thrust a una pierna con banco\",\"hipthrust\",\"hipthrust\",2,\"Cadera\",{\"single\":true,\"bench\":true}],[\"plankjack\",\"Plancha alta con saltos de apertura de pies\",\"pushup\",\"plank\",2,\"Abdomen\",{\"jack\":true,\"impact\":\"Con saltos\"}],[\"plankstep\",\"Plancha alta con pasos laterales de manos\",\"pushup\",\"plank\",2,\"Abdomen\",{\"handwalk\":true}],[\"plankdrag\",\"Plancha alta con arrastre lateral de mancuerna\",\"pushup\",\"plank\",2,\"Abdomen\",{\"drag\":true,\"db\":true}],[\"sideabduct\",\"Plancha lateral con abducción de pierna\",\"full-sideplank19\",\"side-plank\",2,\"Abdomen\",{\"legLift\":true}],[\"sidehip\",\"Plancha lateral corta con elevación de pelvis\",\"sideplank\",\"side-plank\",1,\"Abdomen\",{\"knees\":true,\"hipLift\":true}],[\"bearreach\",\"Cuadrupedia suspendida con alcance al frente\",\"bearhold\",\"bear\",2,\"Abdomen\",{\"reach\":true}],[\"deadbugpress\",\"Dead bug con presión de manos contra rodillas\",\"deadbug\",\"deadbug\",1,\"Abdomen\",{\"press\":true}],[\"reversebird\",\"Extensión contralateral tumbado con talón apoyado\",\"deadbug\",\"deadbug\",0,\"Abdomen\",{\"heelSupport\":true}],[\"scappush\",\"Flexión escapular en plancha alta\",\"pushup\",\"push\",1,\"Pecho\",{\"scapular\":true}],[\"pushwide\",\"Flexión de suelo con manos algo más separadas\",\"pushup\",\"push\",1,\"Pecho\",{\"wide\":true}],[\"pushcloseknee\",\"Flexión de manos próximas con rodillas apoyadas\",\"kneepush\",\"push\",0,\"Pecho\",{\"close\":true,\"knees\":true}],[\"pikepause\",\"Flexión pica con pausa cerca del suelo\",\"pike19\",\"pike\",2,\"Hombros\",{\"bottomHold\":true}],[\"lateralstepover\",\"Paso lateral sobre línea con rodilla alta\",\"stepcross\",\"agility\",1,\"Cardio\",{\"over\":true}],[\"hopforward\",\"Salto corto hacia delante con aterrizaje estable\",\"lowhop\",\"jump\",1,\"Cardio\",{\"forward\":true,\"impact\":\"Con saltos\"}],[\"hip9090\",\"Rotación de caderas sentado en posición 90–90\",\"hipcircle\",\"hip9090\",0,\"Movilidad\",{\"seated\":true}],[\"wallangel\",\"Ángel de pared con brazos y codos apoyados\",\"wallslide\",\"wall-angel\",0,\"Movilidad\",{\"support\":true}]]")
        val instructions: dynamic = jsonValue("{\"hip-extension\":[\"Sujeta el apoyo y conserva el abdomen activo y la pelvis estable.\",\"Lleva una pierna hacia atrás desde la cadera sin arquear la espalda.\",\"Devuelve la pierna con control y cambia de lado al terminar.\"],\"hip-flexion\":[\"Sujeta el apoyo y coloca la tobillera cerca del tobillo.\",\"Eleva una rodilla hacia delante sin inclinar el tronco.\",\"Baja el pie con control antes de repetir y cambiar de lado.\"],\"hip-abduction\":[\"Colócate de lado al cable con una mano en el apoyo.\",\"Separa la pierna de trabajo hacia el lado sin subir la pelvis.\",\"Vuelve lentamente y cambia de lado al completar la serie.\"],\"hip-adduction\":[\"Colócate de lado al cable y sujeta el apoyo.\",\"Acerca la pierna con tobillera hacia la línea media sin girar el tronco.\",\"Vuelve al lado de inicio con control y cambia de pierna.\"],\"crunch\":[\"Ajusta el apoyo y mantén la pelvis estable; no tires del cuello.\",\"Acerca las costillas a la pelvis con una flexión corta y controlada.\",\"Regresa al recorrido inicial sin impulso ni dolor.\"],\"back-extension\":[\"Ajusta asiento y almohadilla con los pies apoyados.\",\"Mueve el tronco desde una ligera inclinación hasta quedar erguido.\",\"Evita sobreextender la espalda; vuelve despacio al inicio.\"],\"rotation\":[\"Ajusta el asiento y apoya pelvis y pies.\",\"Realiza una rotación corta del tronco dentro de un recorrido cómodo.\",\"Regresa sin impulso; trabaja ambos lados con la misma carga.\"],\"pullup\":[\"Ajusta la asistencia y sube al apoyo con los agarres firmes.\",\"Acerca el pecho hacia los agarres llevando codos hacia los costados.\",\"Desciende con control y sal del equipo cuando el apoyo esté estable.\"],\"dip\":[\"Ajusta la asistencia y coloca las manos en barras paralelas.\",\"Flexiona los codos con el tronco estable, sin forzar el hombro.\",\"Empuja las barras para subir; conserva una carga controlable.\"],\"cycle\":[\"Ajusta asiento y pedales para conservar una flexión suave de rodilla al extender la pierna.\",\"Pedalea de forma continua con rodillas dirigidas hacia los pies.\",\"Reduce el ritmo antes de detenerte o bajar de la bicicleta.\"],\"elliptical\":[\"Apoya ambos pies y sujeta las asas antes de comenzar.\",\"Alterna piernas y brazo contrario sin rebotar ni girar las rodillas.\",\"Reduce el ritmo gradualmente antes de bajar del equipo.\"],\"ski\":[\"Sujeta las asas altas con rodillas suaves y espalda estable.\",\"Lleva las manos hacia los muslos acompañando con una pequeña bisagra de cadera.\",\"Vuelve las asas arriba con control sin perder la postura.\"],\"ankle\":[\"Conserva la pierna apoyada y el tobillo libre para moverse.\",\"Acerca la punta del pie hacia la espinilla contra la resistencia.\",\"Devuelve la punta lentamente sin mover la rodilla.\"],\"deadbug\":[\"Túmbate boca arriba con costillas y pelvis estables.\",\"Extiende lentamente un brazo y la pierna contraria; conserva el apoyo lumbar.\",\"Vuelve y alterna, acortando el recorrido si pierdes ese apoyo.\"],\"agility\":[\"Comienza con pies paralelos en un espacio despejado y sin obstáculos rígidos.\",\"Alterna pasos sobre la línea con rodilla alta y apoyo estable.\",\"Cambia de dirección con pasos cortos, sin cruzar las rodillas.\"],\"jump\":[\"Comienza con pies estables y flexiona ligeramente caderas y rodillas.\",\"Impúlsate en un salto corto hacia delante.\",\"Aterriza suave, estabiliza rodillas y pelvis y regresa caminando al inicio.\"],\"hip9090\":[\"Siéntate con ambas rodillas flexionadas y apoya las manos detrás si lo necesitas.\",\"Cambia ambas rodillas de un lado al otro desde las caderas sin forzar el recorrido.\",\"Mantén el tronco estable y termina si aparece dolor.\"],\"wall-angel\":[\"Apoya espalda y brazos contra la pared dentro de un recorrido cómodo.\",\"Desliza manos y codos hacia arriba sin arquear la zona lumbar.\",\"Baja con control conservando el contacto posible sin forzar hombros.\"]}")
        val details: dynamic = jsonValue("{\"squatnarrow\":\"Pies algo más próximos; rodillas y puntas conservan la misma dirección.\",\"squatheels\":\"Usa una cuña de ejercicio firme, no objetos que rueden.\",\"squatpulse\":\"Tras bajar, alterna dos recorridos cortos antes de volver a subir.\",\"cyclistdb\":\"Cuña estable y carga cerca del pecho; baja solo donde conserves el control.\",\"lungeoffset\":\"La carga permanece en una mano; cambia de lado entre series.\",\"rdlstagger\":\"El pie trasero actúa como apoyo auxiliar; la carga principal está en la pierna delantera.\",\"bridgemarch\":\"Mantén la pelvis elevada mientras despega un pie y después el contrario.\",\"bridgesqueeze\":\"Coloca un cojín entre rodillas; presiona suavemente sin girar pies ni pelvis.\",\"plankjack\":\"Abre y cierra los pies con pequeños saltos manteniendo ambas manos fijas.\",\"plankdrag\":\"Una mano cruza bajo el torso para arrastrar la mancuerna; la otra mantiene el apoyo.\",\"plankstep\":\"Da un paso lateral con mano y pie y vuelve; conserva el tronco alineado.\",\"sideabduct\":\"La pierna superior se eleva y vuelve mientras pelvis y hombro de apoyo permanecen estables.\",\"sidehip\":\"Con rodillas apoyadas, eleva y baja la pelvis sin colapsar el hombro.\",\"deadbugpress\":\"Presiona mano y rodilla del lado que no se extiende.\",\"reversebird\":\"El talón que se aleja desliza por el suelo.\",\"scappush\":\"Codos extendidos: aproxima y separa omóplatos con un recorrido corto.\",\"pikepause\":\"Mantén una breve pausa cerca del suelo antes de empujar.\",\"press45\":\"Pies apoyados en el trineo inclinado; espalda y pelvis permanecen sobre el respaldo.\",\"presssingle\":\"Una pierna empuja y vuelve con control; la otra queda fuera de la plataforma.\"}")
        val ids: dynamic = js("[]")
        val bases: dynamic = js("[]")
        if (true) {
            val iterator3: dynamic = iteratorOf(arrayOf<dynamic>(arrayOf<dynamic>("gym", gym), arrayOf<dynamic>("home", home)))
            while (true) {
                val next4: dynamic = iterator3.next()
                if (truth(next4.done)) break
                var place: dynamic = absent
                var rows: dynamic = absent
                val unpacked5: dynamic = fromIterable(next4.value)
                place = unpacked5[0]
                rows = unpacked5[1]
                if (true) {
                    val iterator6: dynamic = iteratorOf(rows)
                    while (true) {
                        val next7: dynamic = iterator6.next()
                        if (truth(next7.done)) break
                        var key: dynamic = absent
                        var name: dynamic = absent
                        var parentId: dynamic = absent
                        var kind: dynamic = absent
                        var tier: dynamic = absent
                        var group: dynamic = absent
                        var params: dynamic = absent
                        val unpacked8: dynamic = fromIterable(next7.value)
                        key = unpacked8[0]
                        name = unpacked8[1]
                        parentId = unpacked8[2]
                        kind = unpacked8[3]
                        tier = unpacked8[4]
                        group = unpacked8[5]
                        params = unpacked8[6]
                        val parent: dynamic = global_Engine.byId(parentId)
                        if (truth((!truth(parent)))) {
                            throw global_Error(jsAdd("Padre V30 ausente: ", parentId))
                        }
                        val gymOnly: dynamic = (place === "gym")
                        val p: dynamic = jsObject { obj9 ->
                            copyFields(obj9, params)
                            obj9["gear"] = jsOr(params.gear) { (if (truth(gymOnly)) "machine" else absent) }
                        }
                        val id: dynamic = jsAdd(jsAdd(jsAdd(place, "-"), key), "30")
                        val steps: dynamic = jsArray { array10 ->
                            appendAll(array10, jsOr(jsOr(instructions[kind]) { global_ExercisePack23.rules[kind] }) { js("[]") })
                            appendAll(array10, (if (truth(details[key])) arrayOf<dynamic>(details[key]) else js("[]")))
                        }
                        if (truth(jsLess(steps.length, 3))) {
                            throw global_Error(jsAdd("Instrucción V30 ausente: ", kind))
                        }
                        val e: dynamic = jsObject { obj11 ->
                            copyFields(obj11, cp(parent))
                            obj11["id"] = id
                            obj11["baseId"] = id
                            obj11["name"] = name
                            obj11["variant"] = "base"
                            obj11["level"] = tier
                            obj11["progressionTier19"] = tier
                            obj11["progressionTier23"] = tier
                            obj11["pack23"] = true
                            obj11["pack30"] = true
                            obj11["gymOnly23"] = gymOnly
                            obj11["recipe30"] = kind
                            obj11["param30"] = p
                            obj11["sourceOnly"] = false
                            obj11["originalVideo15"] = false
                            obj11["group"] = group
                            obj11["unit"] = (if (truth(jsOr(p.cardio) { dynamicValue(jsonValue("[\"hip9090\",\"wall-angel\"]")).includes(kind) })) "sec" else "reps")
                            obj11["category"] = (if (truth((group === "Movilidad"))) "Movilidad" else (if (truth((group === "Cardio"))) "Cardio" else "Fuerza"))
                            obj11["equipment"] = (if (truth(gymOnly)) "gym" else (if (truth(p.db)) "db" else "body"))
                            obj11["requirements"] = (if (truth(gymOnly)) js("[]") else jsArray { array12 ->
                                appendAll(array12, (if (truth(p.db)) jsonValue("[\"db\"]") else js("[]")))
                                appendAll(array12, (if (truth(p.bench)) jsonValue("[\"bench\"]") else js("[]")))
                                appendAll(array12, (if (truth(p.step)) jsonValue("[\"step\"]") else js("[]")))
                            })
                            obj11["gymRequirements23"] = (if (truth(gymOnly)) jsArray { array13 ->
                                array13.push(p.gear)
                                appendAll(array13, (if (truth(p.bench)) jsonValue("[\"bench\"]") else js("[]")))
                            } else js("[]"))
                            obj11["loaded23"] = jsOr(jsAnd(gymOnly) { (p.gear !== "cardio") }) { (!truth((!truth(p.db)))) }
                            obj11["impact"] = jsOr(p.impact) { "Sin saltos" }
                            obj11["spineMove"] = (!truth((!truth(p.spineMove))))
                            obj11["kneeBend"] = dynamicValue(jsonValue("[\"squat\",\"lunge\",\"hinge\",\"legpress\",\"hamcurl\",\"step\",\"cycle\",\"jump\"]")).includes(kind)
                            obj11["wristLoad"] = dynamicValue(jsonValue("[\"push\",\"pike\",\"plank\",\"side-plank\",\"bear\"]")).includes(kind)
                            obj11["overhead"] = dynamicValue(jsonValue("[\"overhead\",\"pullup\",\"ski\"]")).includes(kind)
                            obj11["floor"] = jsAnd((!truth(gymOnly))) { dynamicValue(jsonValue("[\"push\",\"pike\",\"plank\",\"side-plank\",\"bear\",\"bridge\",\"deadbug\",\"hip9090\"]")).includes(kind) }
                            obj11["seated"] = jsOr((!truth((!truth(p.seated))))) { dynamicValue(jsonValue("[\"cycle\",\"legpress\"]")).includes(kind) }
                            obj11["sides"] = (!truth((!truth(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(p.single) { p.offset }) { p.singleLoad }) { p.march }) { p.legLift }) { (kind === "lunge") }) { kind.startsWith("hip-") }))))
                            obj11["family"] = (if (truth((group === "Movilidad"))) "mobility" else (if (truth((group === "Cardio"))) (if (truth((kind === "jump"))) "power" else "cardio") else jsOr(parent.family) { "full" }))
                            obj11["setup"] = steps[0]
                            obj11["instructions"] = steps
                            obj11["steps"] = steps.join(" ")
                            obj11["cue"] = "Controla el recorrido y la carga; detén la serie si aparece dolor o pierdes la postura."
                            obj11["variantDescription"] = jsOr(details[key]) { "Recorrido y apoyos propios de esta variante." }
                            obj11["repRange19"] = (if (truth((group === "Cardio"))) jsonValue("[20,60]") else arrayOf<dynamic>((if (truth((tier === 2))) 5 else 8), (if (truth((tier === 2))) 12 else 16)))
                            obj11["tempoSeconds18"] = 4
                        }
                        e.animationParent30 = parentId
                        removeKey(e, "recipe23")
                        removeKey(e, "param23")
                        removeKey(e, "renderWarp23")
                        removeKey(e, "media")
                        removeKey(e, "demo")
                        global_Engine.exercises.push(e)
                        global_MotionDB[id] = jsObject { obj18 ->
                            copyFields(obj18, cp(global_MotionDB[jsOr(parent.baseId) { parent.id }]))
                            obj18["id"] = id
                            obj18["name"] = name
                        }
                        ids.push(id)
                        bases.push(id)
                        if (true) {
                            val iterator19: dynamic = iteratorOf(jsonValue("[[\"ecc30\",\"retorno de tres segundos\",5],[\"hold30\",\"pausa de dos segundos\",6]]"))
                            while (true) {
                                val next20: dynamic = iterator19.next()
                                if (truth(next20.done)) break
                                var suffix: dynamic = absent
                                var label: dynamic = absent
                                var seconds: dynamic = absent
                                val unpacked21: dynamic = fromIterable(next20.value)
                                suffix = unpacked21[0]
                                label = unpacked21[1]
                                seconds = unpacked21[2]
                                val v: dynamic = jsObject { obj22 ->
                                    copyFields(obj22, cp(e))
                                    obj22["id"] = jsAdd(jsAdd(id, "-"), suffix)
                                    obj22["name"] = jsAdd(jsAdd(name, " · "), label)
                                    obj22["variant"] = (if (truth((suffix === "ecc30"))) "controlled" else "pause")
                                    obj22["level"] = global_Math.min(2, jsAdd(tier, 1))
                                    obj22["progressionTier19"] = global_Math.min(2, jsAdd(tier, 1))
                                    obj22["progressionTier23"] = global_Math.min(2, jsAdd(tier, 1))
                                    obj22["tempoSeconds18"] = seconds
                                    obj22["renderWarp23"] = (if (truth((suffix === "ecc30"))) "slow" else "pause")
                                    obj22["variantDescription"] = jsAdd(label, " con los mismos apoyos; no añadas impulso.")
                                }
                                v.instructions.push(v.variantDescription)
                                v.steps = v.instructions.join(" ")
                                global_Engine.exercises.push(v)
                                ids.push(v.id)
                            }
                        }
                    }
                }
            }
        }
        if (truth(jsOr(jsOr((gym.length !== 42)) { (home.length !== 28) }) { (global_Engine.exercises.length !== 1000) })) {
            throw global_Error("Catálogo V30: se requieren 1000 fichas.")
        }
        root.Catalogue30 = jsObject { obj27 ->
            obj27["ids"] = ids
            obj27["bases"] = bases
            obj27["gym"] = gym
            obj27["home"] = home
            obj27["instructions"] = instructions
            obj27["version"] = 30
        }
        return absent
    }))(global_window)
}
