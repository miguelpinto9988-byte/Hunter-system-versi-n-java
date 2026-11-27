@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installcatalogv3

import hunter.browser.*

@JsName("Set") private external var global_Set: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("globalThis") private external var global_globalThis: dynamic

internal fun installCatalogV3() {
    dynamicValue((fun(root: dynamic): dynamic {
        var E: dynamic = absent
        var duration: dynamic = absent
        var decorate: dynamic = absent
        duration = fun(items: dynamic): dynamic {
            return jsAdd(global_Math.ceil(jsDivide(items.reduce((fun(n: dynamic, x: dynamic): dynamic {
                return jsAdd(n, jsMultiply(x.sets, jsAdd(jsMultiply(jsMultiply(x.target, (if (truth((E.unitOf(x) === "sec"))) 1 else 4)), (if (truth(E.sidesOf(x))) 2 else 1)), jsOr(x.rest) { 60 })))
            }), 0), 60)), 3)
        }
        decorate = fun(r: dynamic): dynamic {
            return jsObject { obj52 ->
                copyFields(obj52, r)
                obj52["minutes"] = duration(r.items)
                obj52["requirements"] = jsArray { array53 ->
                    appendAll(array53, jsConstruct(global_Set, arrayOf<dynamic>(r.items.flatMap((fun(x: dynamic): dynamic {
                        return E.byId(x.id).requirements
                    })))))
                }
                obj52["level"] = run { val target55: dynamic = global_Math; invokeWith(target55.max, target55, jsArray { array54 ->
                    appendAll(array54, r.items.map((fun(x: dynamic): dynamic {
                        return E.byId(x.id).level
                    })))
                }) }
                obj52["impact"] = (if (truth(r.items.some((fun(x: dynamic): dynamic {
                    return (E.byId(x.id).impact === "Con saltos")
                })))) "Con saltos" else "Sin saltos")
            }
        }
        E = root.Engine
        val C: dynamic = root.Catalog
        val extra: dynamic = jsonValue("[[\"seatedpunch\",\"Boxeo al aire sentado\",\"Cardio\",\"\",0,\"sec\",false,\"Siéntate en una silla firme con la espalda cómoda y los pies apoyados.|Alterna golpes cortos al frente sin bloquear los codos.|Mantén el torso estable y vuelve las manos hacia el pecho.\",\"No uses cargas ni golpes explosivos.\",\"shoulder,elbow,wrist,back\"],[\"seatedmarch\",\"Marcha alterna sentado\",\"Cardio\",\"\",0,\"sec\",false,\"Siéntate hacia el centro de una silla estable y apoya los pies.|Eleva un pie unos centímetros y vuelve a apoyarlo.|Alterna lentamente sin inclinarte hacia atrás.\",\"Evita una elevación que cause molestias en la cadera.\",\"hip,knee,back\"],[\"seatedstep\",\"Pasos laterales sentado\",\"Cardio\",\"\",0,\"sec\",false,\"Sentado en una silla fija, apoya ambos pies.|Mueve un pie hacia un lado y devuélvelo al centro.|Alterna pies sin girar sobre la rodilla apoyada.\",\"Haz pasos cortos; no deslices sobre un suelo resbaladizo.\",\"hip,knee,ankle\"],[\"heeltoe\",\"Apoyo alterno de talón y punta sentado\",\"Movilidad\",\"\",0,\"reps\",false,\"Siéntate con ambos pies apoyados.|Eleva las puntas dejando los talones en el suelo.|Apoya las puntas y eleva suavemente los talones.\",\"Una secuencia completa cuenta una repetición.\",\"ankle\"],[\"legextend\",\"Extensión de rodilla sentado\",\"Piernas\",\"\",0,\"reps\",true,\"Siéntate con el muslo apoyado y una rodilla flexionada.|Extiende lentamente la rodilla sin añadir peso.|Regresa y repite con la otra pierna.\",\"No bloquees bruscamente la articulación ni fuerces el recorrido.\",\"knee,hip\"],[\"seatedhip\",\"Separación de rodillas sentado\",\"Cadera\",\"\",0,\"reps\",false,\"Siéntate erguido con pies apoyados y rodillas separadas a una distancia cómoda.|Separa ligeramente las rodillas manteniendo los pies apoyados.|Regresa al centro con control.\",\"Sin bandas ni presión externa; no fuerces la apertura.\",\"hip,knee\"],[\"toelift\",\"Elevación de puntas sentado\",\"Piernas\",\"\",0,\"reps\",false,\"Siéntate con los talones apoyados.|Eleva lentamente las puntas de los pies.|Bájalas con control.\",\"Mantén las rodillas quietas y un recorrido cómodo.\",\"ankle\"],[\"seatedheel\",\"Elevación alterna de talones sentado\",\"Piernas\",\"\",0,\"reps\",false,\"Sentado, deja las puntas de ambos pies apoyadas.|Eleva un talón y bájalo.|Alterna lados sin rebotes.\",\"Cada elevación cuenta una repetición.\",\"ankle\"],[\"seatedside\",\"Inclinación lateral suave sentado\",\"Movilidad\",\"\",0,\"reps\",false,\"Siéntate erguido con las manos sobre los muslos.|Inclina ligeramente el torso hacia un lado sin girarlo.|Vuelve al centro y alterna.\",\"Usa un recorrido pequeño y sin dolor.\",\"back,hip\"],[\"seatedturn\",\"Giro suave de torso sentado\",\"Movilidad\",\"\",0,\"reps\",false,\"Siéntate con la pelvis estable y los pies apoyados.|Gira suavemente el pecho hacia un lado.|Regresa al centro y alterna sin tirar de la silla.\",\"No fuerces la columna ni el cuello.\",\"back,neck\"],[\"seatedreach\",\"Alcance frontal alterno sentado\",\"Movilidad\",\"\",0,\"reps\",false,\"Siéntate con el torso erguido.|Extiende un brazo al frente sin elevar el hombro.|Recógelo y alterna, sin inclinar el cuerpo.\",\"Cada alcance cuenta una repetición.\",\"shoulder,elbow,back\"],[\"elbowmob\",\"Flexión de codos sin carga\",\"Movilidad\",\"\",0,\"reps\",false,\"Siéntate con los brazos al costado y las manos relajadas.|Flexiona los codos acercando las manos a los hombros.|Extiende de nuevo lentamente.\",\"No fuerces el final del movimiento.\",\"elbow\"],[\"forearmmob\",\"Rotación de antebrazos sentado\",\"Movilidad\",\"\",0,\"reps\",false,\"Siéntate con los codos flexionados y apoyados junto al cuerpo.|Gira las palmas hacia arriba y después hacia abajo.|Alterna de forma suave.\",\"No añadas carga ni gires desde el hombro.\",\"elbow,wrist\"],[\"wristmob\",\"Flexión y extensión de muñecas sentado\",\"Movilidad\",\"\",0,\"reps\",false,\"Apoya los antebrazos sobre los muslos dejando las manos libres.|Mueve lentamente las manos arriba y abajo.|Regresa a una posición neutra.\",\"Mantén los dedos relajados y no fuerces el recorrido.\",\"wrist\"],[\"fingers\",\"Apertura y cierre suave de manos\",\"Movilidad\",\"\",0,\"reps\",false,\"Sentado, apoya los antebrazos.|Abre suavemente los dedos.|Cierra las manos sin apretar con fuerza y repite.\",\"No uses pelotas ni resistencia adicional.\",\"wrist\"],[\"wallcalf\",\"Elevación de talones con apoyo en pared\",\"Piernas\",\"\",0,\"reps\",false,\"De pie frente a una pared, apoya las manos con suavidad.|Eleva los talones sin perder el equilibrio.|Baja lentamente manteniendo ambos pies paralelos.\",\"La pared ayuda al equilibrio; no empujes con los brazos.\",\"ankle,knee,hip,wrist,shoulder\"],[\"standingabduct\",\"Abducción de cadera de pie con apoyo\",\"Cadera\",\"\",0,\"reps\",true,\"Colócate junto a una pared y apoya una mano.|Separa una pierna ligeramente hacia un lado sin inclinar el torso.|Vuelve y completa el otro lado.\",\"Evita girar la pelvis o balancearte.\",\"hip,knee,ankle,back,shoulder,wrist\"],[\"standingextend\",\"Extensión de cadera de pie con apoyo\",\"Cadera\",\"\",0,\"reps\",true,\"Sujeta un apoyo fijo y mantén el torso erguido.|Lleva una pierna ligeramente hacia atrás.|Regresa despacio y cambia de lado.\",\"No arquees la espalda para ampliar el recorrido.\",\"hip,knee,ankle,back,shoulder,wrist\"],[\"standingcurl\",\"Flexión de rodilla de pie con apoyo\",\"Piernas\",\"\",0,\"reps\",true,\"Colócate junto a una pared o apoyo fijo.|Flexiona una rodilla llevando el talón hacia atrás.|Baja con control y cambia de pierna.\",\"Mantén los muslos alineados y evita balancear el cuerpo.\",\"knee,hip,ankle,shoulder,wrist\"],[\"tandem\",\"Equilibrio con pies en línea y apoyo\",\"Movilidad\",\"\",0,\"sec\",false,\"Colócate junto a una pared y pon un pie delante del otro con una separación cómoda.|Mantén una mano disponible sobre el apoyo.|Sostén la postura sin cerrar los ojos y cambia el pie delantero.\",\"Evita hacerlo sin apoyo si te sientes inestable.\",\"ankle,knee,hip,back,shoulder,wrist\"],[\"balance\",\"Equilibrio a una pierna con apoyo\",\"Movilidad\",\"\",1,\"sec\",true,\"Sujeta un apoyo fijo y levanta un pie unos centímetros.|Mantén la postura mirando al frente.|Vuelve a apoyar y cambia de pierna.\",\"No retires el apoyo ni cierres los ojos.\",\"ankle,knee,hip,back,shoulder,wrist\"],[\"linewalk\",\"Caminata de pasos cortos junto a pared\",\"Movilidad\",\"\",0,\"sec\",false,\"Sitúate al lado de una pared con el recorrido despejado.|Avanza con pasos cortos y una mano cerca del apoyo.|Gira con varios pasos y vuelve.\",\"No cruces los pies ni camines hacia atrás.\",\"ankle,knee,hip,back\"],[\"doublefront\",\"Sentadilla con dos mancuernas al pecho\",\"Piernas\",\"db\",2,\"reps\",false,\"Sujeta dos mancuernas ligeras junto al pecho, con muñecas cómodas.|Flexiona caderas y rodillas manteniendo los pies apoyados.|Sube sin rebotes y sin inclinarte hacia atrás.\",\"Domina primero la sentadilla sin carga.\",\"ankle,knee,hip,back,shoulder,elbow,wrist\"],[\"suitdead\",\"Peso muerto con mancuernas a los lados\",\"Cadera\",\"db\",1,\"reps\",false,\"Sujeta una mancuerna a cada lado con los pies estables.|Lleva la cadera atrás y flexiona un poco las rodillas.|Sube manteniendo las cargas junto al cuerpo.\",\"No redondees la espalda ni busques tocar el suelo.\",\"ankle,knee,hip,back,shoulder,wrist\"],[\"dbsplit\",\"Sentadilla dividida con mancuernas\",\"Piernas\",\"db\",2,\"reps\",true,\"Sujeta cargas a los lados y separa los pies adelante y atrás.|Flexiona ambas rodillas sin mover los pies.|Sube con control y cambia la pierna delantera.\",\"Usa primero la variante sin cargas si pierdes estabilidad.\",\"ankle,knee,hip,back,shoulder,wrist\"],[\"seatedpress\",\"Press de hombros sentado\",\"Hombros\",\"db\",1,\"reps\",false,\"Siéntate con apoyo firme y mancuernas ligeras junto a los hombros.|Empuja hacia arriba hasta un rango cómodo.|Baja lentamente manteniendo la espalda sin arquear.\",\"No uses una silla inestable ni subas con impulso.\",\"shoulder,elbow,wrist,back\"],[\"seatedlateral\",\"Elevación lateral sentado\",\"Hombros\",\"db\",1,\"reps\",false,\"Siéntate erguido con cargas ligeras a los lados.|Eleva los brazos lateralmente hasta una altura cómoda.|Baja lentamente sin encoger los hombros.\",\"Evita balancear el torso.\",\"shoulder,elbow,wrist,back\"],[\"seatedfly\",\"Apertura posterior sentado\",\"Espalda\",\"db\",1,\"reps\",false,\"Siéntate y realiza una pequeña inclinación desde las caderas.|Abre los brazos con mancuernas ligeras.|Baja con control manteniendo estable la espalda.\",\"No redondees la columna ni eleves las cargas con impulso.\",\"shoulder,elbow,wrist,back,hip\"],[\"curlhold\",\"Sujeción de bíceps a medio recorrido\",\"Brazos\",\"db\",1,\"sec\",false,\"Sujeta cargas ligeras con los codos junto al torso.|Flexiona los codos hasta una posición intermedia cómoda.|Mantén respirando y baja al terminar.\",\"No aprietes con fuerza excesiva ni sostengas hasta perder el control.\",\"elbow,wrist,shoulder,back\"],[\"farmerhold\",\"Sujeción bilateral de mancuernas\",\"Core\",\"db\",1,\"sec\",false,\"De pie, sostiene una mancuerna a cada lado.|Mantén el torso erguido y los hombros relajados.|Respira y deja las cargas en el suelo con control.\",\"No eleves los hombros ni arquees la espalda.\",\"ankle,knee,hip,back,shoulder,wrist\"],[\"wallplank\",\"Plancha de pie en pared\",\"Core\",\"\",0,\"sec\",false,\"Apoya los antebrazos en una pared y aleja ligeramente los pies.|Mantén el cuerpo alineado con el abdomen suavemente activo.|Respira y termina antes de perder la postura.\",\"No dejes que la espalda se hunda.\",\"shoulder,elbow,back,hip,ankle\"],[\"supinearms\",\"Elevación de brazos tumbado\",\"Movilidad\",\"\",0,\"reps\",false,\"Túmbate boca arriba con rodillas en una posición cómoda.|Lleva los brazos hacia arriba y un poco atrás dentro de un rango cómodo.|Regresa despacio sin despegar las costillas.\",\"No fuerces los brazos hasta el suelo ni arquees la espalda.\",\"shoulder,elbow,back\"]]")
        if (true) {
            val iterator3: dynamic = iteratorOf(extra)
            while (true) {
                val next4: dynamic = iterator3.next()
                if (truth(next4.done)) break
                var id: dynamic = absent
                var name: dynamic = absent
                var group: dynamic = absent
                var eq: dynamic = absent
                var level: dynamic = absent
                var unit: dynamic = absent
                var sides: dynamic = absent
                var steps: dynamic = absent
                var cue: dynamic = absent
                var z: dynamic = absent
                val unpacked5: dynamic = fromIterable(next4.value)
                id = unpacked5[0]
                name = unpacked5[1]
                group = unpacked5[2]
                eq = unpacked5[3]
                level = unpacked5[4]
                unit = unpacked5[5]
                sides = unpacked5[6]
                steps = unpacked5[7]
                cue = unpacked5[8]
                z = unpacked5[9]
                E.exercises.push(jsObject { obj6 ->
                    obj6["id"] = id
                    obj6["name"] = name
                    obj6["group"] = group
                    obj6["equipment"] = (if (truth(eq)) "db" else "body")
                    obj6["level"] = level
                    obj6["unit"] = unit
                    obj6["sides"] = sides
                    obj6["steps"] = steps.replaceAll("|", " ")
                    obj6["instructions"] = steps.split("|")
                    obj6["cue"] = cue
                    obj6["category"] = (if (truth((group === "Cardio"))) "Cardio" else (if (truth((group === "Movilidad"))) "Movilidad" else "Fuerza"))
                    obj6["requirements"] = (if (truth(eq)) jsonValue("[\"db\"]") else js("[]"))
                    obj6["impact"] = "Bajo"
                    obj6["setup"] = "Despeja el espacio y comprueba que los apoyos sean firmes."
                    obj6["control"] = "Usa un recorrido que puedas controlar y detente si aparece dolor."
                    obj6["breath"] = "Respira de forma continua; no retengas el aire."
                    obj6["tempo"] = (if (truth((unit === "sec"))) "Mantén un ritmo cómodo durante el intervalo." else "Realiza cada repetición despacio y sin rebotes.")
                    obj6["zones"] = z.split(",")
                })
            }
        }
        val floor: dynamic = jsConstruct(global_Set, arrayOf<dynamic>(jsonValue("[\"press\",\"neutral\",\"bridge\",\"prone\",\"reverse\",\"deadbug\",\"bird\",\"plank\",\"heel\",\"sideleg\",\"cat\",\"kneepush\",\"pushup\",\"closepush\",\"squeezepress\",\"singlebridge\",\"dbbridge\",\"kickback\",\"clam\",\"pullover\",\"shouldertap\",\"lyingtricep\",\"sideplank\",\"kneelingplank\",\"reversecrunch\",\"curlup\",\"mountain\",\"thoracic\",\"hipflex\",\"supinearms\"]")))
        val seated: dynamic = jsConstruct(global_Set, arrayOf<dynamic>(jsonValue("[\"seatedpunch\",\"seatedmarch\",\"seatedstep\",\"heeltoe\",\"legextend\",\"seatedhip\",\"toelift\",\"seatedheel\",\"seatedside\",\"seatedturn\",\"seatedreach\",\"elbowmob\",\"forearmmob\",\"wristmob\",\"fingers\",\"seatedpress\",\"seatedlateral\",\"seatedfly\",\"seatedcurl\",\"concentration\",\"seatedcalf\",\"ankle\",\"hamstretch\"]")))
        val upperZones: dynamic = jsonValue("[\"shoulder\",\"elbow\",\"wrist\",\"back\"]")
        if (true) {
            val iterator7: dynamic = iteratorOf(E.exercises)
            while (true) {
                val next8: dynamic = iterator7.next()
                if (truth(next8.done)) break
                var e: dynamic = absent
                e = next8.value
                e.baseId = e.id
                e.variant = "base"
                e.floor = floor.has(e.id)
                e.seated = seated.has(e.id)
                e.overhead = dynamicValue(jsonValue("[\"overheadpress\",\"seatedpress\",\"inclinebench\",\"wallslide\",\"pullover\",\"jumpjack\",\"stepjack\",\"supinearms\"]")).includes(e.id)
                e.wristLoad = dynamicValue(jsonValue("[\"wall\",\"incline\",\"pushup\",\"closepush\",\"kneepush\",\"bird\",\"cat\",\"shouldertap\",\"mountain\",\"kickback\"]")).includes(e.id)
                e.kneeBend = dynamicValue(jsonValue("[\"chair\",\"squat\",\"goblet\",\"sumo\",\"dbsumo\",\"reverselunge\",\"dblunge\",\"split\",\"dbsplit\",\"lateralunge\",\"stepup\",\"wallsit\",\"squatstep\",\"doublefront\",\"seatedmarch\",\"legextend\",\"seatedhip\",\"standingcurl\",\"heelback\",\"kneeliftcardio\"]")).includes(e.id)
                if (truth((!truth(e.zones)))) {
                    e.zones = jsObject { obj26 ->
                        obj26["Piernas"] = jsonValue("[\"ankle\",\"knee\",\"hip\",\"back\"]")
                        obj26["Cadera"] = jsonValue("[\"hip\",\"knee\",\"back\"]")
                        obj26["Pecho"] = upperZones
                        obj26["Espalda"] = jsonValue("[\"back\",\"shoulder\",\"elbow\",\"wrist\"]")
                        obj26["Brazos"] = upperZones
                        obj26["Hombros"] = upperZones
                        obj26["Core"] = jsonValue("[\"back\",\"hip\",\"shoulder\",\"wrist\"]")
                        obj26["Cardio"] = jsonValue("[\"ankle\",\"knee\",\"hip\",\"back\",\"shoulder\",\"elbow\",\"wrist\"]")
                        obj26["Movilidad"] = jsonValue("[\"back\",\"neck\",\"shoulder\",\"elbow\",\"wrist\",\"hip\",\"knee\",\"ankle\"]")
                    }[e.group].slice()
                }
                if (truth((e.id === "ankle"))) {
                    e.zones = jsonValue("[\"ankle\"]")
                }
                if (truth((e.id === "wrist"))) {
                    e.zones = jsonValue("[\"wrist\"]")
                }
                if (truth((e.id === "neck"))) {
                    e.zones = jsonValue("[\"neck\"]")
                }
                if (truth((e.id === "shoulder"))) {
                    e.zones = jsonValue("[\"shoulder\",\"neck\"]")
                }
                if (truth((e.id === "breathing"))) {
                    e.zones = jsonValue("[\"back\"]")
                }
                if (truth((e.id === "seatedcurl"))) {
                    e.zones = upperZones.slice()
                }
                e.spineMove = dynamicValue(jsonValue("[\"hinge\",\"rdl\",\"bentrow\",\"reversefly\",\"seatedfly\",\"cat\",\"thoracic\",\"seatedside\",\"seatedturn\",\"curlup\",\"reversecrunch\",\"singlehinge\",\"sumodead\",\"suitdead\"]")).includes(e.id)
            }
        }
        val bases: dynamic = E.exercises.slice()
        if (true) {
            val iterator41: dynamic = iteratorOf(bases)
            while (true) {
                val next42: dynamic = iterator41.next()
                if (truth(next42.done)) break
                var e: dynamic = absent
                e = next42.value
                val control: dynamic = (e.unit === "reps")
                if (true) {
                    val iterator43: dynamic = iteratorOf((if (truth(control)) jsonValue("[[\"slow\",\"retorno lento\",\"En cada repetición, dedica unos tres segundos a la fase de retorno. Reduce las repeticiones o la carga si no puedes mantener ese control.\",\"Retorno de aproximadamente tres segundos; esfuerzo controlado.\"],[\"pause\",\"pausa de control\",\"Añade una pausa breve de aproximadamente un segundo en una posición intermedia cómoda, sin bloquear las articulaciones. No aumentes la carga para esta variante.\",\"Pausa breve en un punto estable; movimiento sin rebotes.\"]]") else jsonValue("[[\"short\",\"intervalos breves\",\"Divide el trabajo en intervalos breves. Empieza con 15 a 20 segundos y descansa antes de repetir; registra únicamente los segundos activos.\",\"Intervalos cortos con descanso entre ellos.\"],[\"easy\",\"ritmo suave\",\"Realiza el movimiento a un ritmo deliberadamente suave, con un recorrido cómodo. Reduce la duración cuando lo necesites.\",\"Cadencia suave y respiración continua, sin buscar esfuerzo máximo.\"]]")))
                    while (true) {
                        val next44: dynamic = iterator43.next()
                        if (truth(next44.done)) break
                        var suffix: dynamic = absent
                        var label: dynamic = absent
                        var instruction: dynamic = absent
                        var tempo: dynamic = absent
                        val unpacked45: dynamic = fromIterable(next44.value)
                        suffix = unpacked45[0]
                        label = unpacked45[1]
                        instruction = unpacked45[2]
                        tempo = unpacked45[3]
                        E.exercises.push(jsObject { obj46 ->
                            copyFields(obj46, e)
                            obj46["id"] = jsAdd(jsAdd(e.id, "_"), suffix)
                            obj46["name"] = jsAdd(jsAdd(e.name, " · "), label)
                            obj46["variant"] = suffix
                            obj46["variantDescription"] = instruction
                            obj46["instructions"] = jsArray { array47 ->
                                appendAll(array47, e.instructions)
                                array47.push(instruction)
                            }
                            obj46["tempo"] = tempo
                            obj46["baseId"] = e.id
                            obj46["alternative"] = e.id
                        })
                    }
                }
            }
        }
        val newSpecs: dynamic = jsonValue("[[\"seatcardio\",\"Cardio sentado · brazos\",\"Cardio\",[\"seatedpunch\",\"seatedreach\",\"elbowmob\"],1,30],[\"seatmix\",\"Cardio sentado · coordinación\",\"Cardio\",[\"seatedmarch\",\"seatedpunch\",\"seatedstep\"],2,30],[\"seatmob\",\"Movilidad sentado\",\"Movilidad\",[\"elbowmob\",\"forearmmob\",\"wristmob\",\"heeltoe\"],1,8],[\"seatupper\",\"Tren superior sentado con mancuernas\",\"Fuerza\",[\"seatedcurl\",\"seatedpress\",\"seatedlateral\"],2,8],[\"seatarm\",\"Brazos sentado · básico\",\"Fuerza\",[\"seatedcurl\",\"elbowmob\",\"forearmmob\"],2,8],[\"nofloor\",\"Cuerpo completo sin suelo\",\"Fuerza\",[\"squat\",\"wall\",\"calf\",\"scapular\"],2,8],[\"nofloordb\",\"Mancuernas sin suelo\",\"Fuerza\",[\"goblet\",\"row\",\"hammer\",\"dbcalf\"],2,8],[\"balancebase\",\"Equilibrio junto a un apoyo\",\"Movilidad\",[\"tandem\",\"linewalk\",\"ankle\"],1,20],[\"hipstanding\",\"Cadera de pie con apoyo\",\"Fuerza\",[\"standingabduct\",\"standingextend\",\"standingcurl\"],2,8],[\"anklemob\",\"Movilidad de tobillos sentado\",\"Movilidad\",[\"heeltoe\",\"toelift\",\"seatedheel\",\"ankle\"],1,8],[\"handmob\",\"Movilidad de manos y antebrazos\",\"Movilidad\",[\"fingers\",\"wristmob\",\"forearmmob\",\"elbowmob\"],1,8],[\"benchfull\",\"Cuerpo completo con banco\",\"Fuerza\",[\"goblet\",\"benchpress\",\"row\",\"rdl\"],2,8],[\"minimal\",\"Sesión básica de tres movimientos\",\"Fuerza\",[\"squat\",\"wall\",\"bridge\"],1,8],[\"seatedbreak\",\"Pausa sentada sin mancuernas\",\"Movilidad\",[\"seatedreach\",\"elbowmob\",\"fingers\"],1,8],[\"standingcore\",\"Control del tronco sin suelo\",\"Fuerza\",[\"wallplank\",\"standingcore\",\"scapular\"],2,8],[\"carriagedb\",\"Agarre y brazos con mancuernas\",\"Fuerza\",[\"farmerhold\",\"hammer\",\"curlhold\"],2,8]]")
        if (true) {
            val iterator56: dynamic = iteratorOf(newSpecs)
            while (true) {
                val next57: dynamic = iterator56.next()
                if (truth(next57.done)) break
                var id: dynamic = absent
                var name: dynamic = absent
                var category: dynamic = absent
                var ids: dynamic = absent
                var sets: dynamic = absent
                var target: dynamic = absent
                val unpacked58: dynamic = fromIterable(next57.value)
                id = unpacked58[0]
                name = unpacked58[1]
                category = unpacked58[2]
                ids = unpacked58[3]
                sets = unpacked58[4]
                target = unpacked58[5]
                C.routines.push(decorate(jsObject { obj59 ->
                    obj59["id"] = id
                    obj59["name"] = name
                    obj59["category"] = category
                    obj59["items"] = ids.map((fun(id: dynamic): dynamic {
                        return jsObject { obj60 ->
                            obj60["id"] = id
                            obj60["sets"] = sets
                            obj60["target"] = (if (truth((E.byId(id).unit === "sec"))) 30 else target)
                            obj60["rest"] = (if (truth((category === "Fuerza"))) 90 else 30)
                        }
                    }))
                    obj59["description"] = "Plantilla general para revisar y ajustar a tu experiencia y restricciones."
                }))
            }
        }
        val rBase: dynamic = C.routines.slice()
        C.baseRoutines = rBase.length
        if (true) {
            val iterator63: dynamic = iteratorOf(rBase)
            while (true) {
                val next64: dynamic = iterator63.next()
                if (truth(next64.done)) break
                var r: dynamic = absent
                r = next64.value
                r.baseRoutineId = r.id
                r.variant = "base"
                if (true) {
                    val iterator69: dynamic = iteratorOf(jsonValue("[\"brief\",\"practice\"]"))
                    while (true) {
                        val next70: dynamic = iterator69.next()
                        if (truth(next70.done)) break
                        var v: dynamic = absent
                        v = next70.value
                        val brief: dynamic = (v === "brief")
                        val items: dynamic = r.items.map((fun(x: dynamic): dynamic {
                            return jsObject { obj71 ->
                                copyFields(obj71, x)
                                obj71["id"] = (if (truth(jsAnd((!truth(brief))) { E.byId(jsAdd(x.id, "_slow")) })) jsAdd(x.id, "_slow") else x.id)
                                obj71["sets"] = (if (truth(brief)) 1 else global_Math.min(2, x.sets))
                                obj71["target"] = (if (truth((E.unitOf(x) === "sec"))) (if (truth(brief)) global_Math.min(25, x.target) else global_Math.min(40, x.target)) else (if (truth(brief)) global_Math.min(6, x.target) else global_Math.min(8, x.target)))
                            }
                        }))
                        C.routines.push(decorate(jsObject { obj72 ->
                            copyFields(obj72, r)
                            obj72["id"] = jsAdd(jsAdd(r.id, "_"), v)
                            obj72["baseRoutineId"] = r.id
                            obj72["variant"] = v
                            obj72["name"] = jsAdd(r.name, (if (truth(brief)) " · breve" else " · práctica controlada"))
                            obj72["description"] = (if (truth(brief)) "Una serie por movimiento y objetivos más cortos. Variante de menor volumen de la plantilla base." else "Hasta dos series por movimiento. Se usa retorno lento cuando esa variante está disponible.")
                            obj72["items"] = items
                        }))
                    }
                }
            }
        }
        C.baseExercises = bases.length
        C.duration = duration
        return absent
    }))((if (truth((js("typeof window") !== "undefined"))) global_window else global_globalThis))
}
