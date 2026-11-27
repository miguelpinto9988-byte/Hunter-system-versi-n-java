@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installknowledge

import hunter.browser.*

@JsName("TrainingSources") private external var global_TrainingSources: dynamic
@JsName("TrainingGuides") private external var global_TrainingGuides: dynamic
@JsName("Engine") private external var global_Engine: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("Catalog") private external var global_Catalog: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("Math") private external var global_Math: dynamic

internal fun installKnowledge() {
    global_TrainingSources = jsonValue("{\"nhs\":{\"name\":\"NHS · Fuerza en casa\",\"url\":\"https://www.nhs.uk/live-well/exercise/strength-exercises/\"},\"flex\":{\"name\":\"NHS · Flexibilidad\",\"url\":\"https://www.nhs.uk/live-well/exercise/flexibility-exercises/\"},\"nhsvideo\":{\"name\":\"NHS · Demostraciones de fuerza y flexibilidad\",\"url\":\"https://www.nhs.uk/live-well/exercise/strength-and-flex-exercise-plan-how-to-videos/\"},\"mayo\":{\"name\":\"Mayo Clinic · Técnica de fuerza\",\"url\":\"https://www.mayoclinic.org/healthy-lifestyle/fitness/in-depth/strength-training/art-20046031\"},\"cdc\":{\"name\":\"CDC · Actividad física en adultos\",\"url\":\"https://www.cdc.gov/physical-activity-basics/guidelines/adults.html\"},\"ace\":{\"name\":\"ACE · Biblioteca de ejercicios\",\"url\":\"https://www.acefitness.org/resources/everyone/exercise-library/\"},\"side\":{\"name\":\"ACE · Zancada lateral\",\"url\":\"https://www.acefitness.org/resources/everyone/exercise-library/50/side-lunge/\"},\"squat\":{\"name\":\"ACE · Sentadilla\",\"url\":\"https://www.acefitness.org/resources/everyone/exercise-library/135/bodyweight-squat/\"},\"bear\":{\"name\":\"ACE · Caminata de oso\",\"url\":\"https://www.acefitness.org/resources/everyone/exercise-library/150/bear-crawl/\"},\"child\":{\"name\":\"ACE · Postura del niño\",\"url\":\"https://www.acefitness.org/resources/everyone/exercise-library/227/childs-pose/\"},\"contra\":{\"name\":\"ACE · Elevación contralateral\",\"url\":\"https://www.acefitness.org/resources/everyone/exercise-library/53/contralateral-limb-raises/\"},\"lat\":{\"name\":\"ACE · Estiramiento de dorsales\",\"url\":\"https://www.acefitness.org/resources/everyone/exercise-library/198/90-lat-stretch/\"},\"bulgarian\":{\"name\":\"ACE · Sentadilla búlgara\",\"url\":\"https://www.acefitness.org/resources/everyone/exercise-library/366/bulgarian-split-squat/\"},\"diagonal\":{\"name\":\"ACE · Elevación diagonal\",\"url\":\"https://www.acefitness.org/resources/everyone/exercise-library/371/diagonal-raise/\"},\"hipthrust\":{\"name\":\"ACE · Puente elevado\",\"url\":\"https://www.acefitness.org/resources/everyone/exercise-library/367/elevated-glute-bridge/\"},\"carry\":{\"name\":\"ACE · Caminata del granjero\",\"url\":\"https://www.acefitness.org/resources/everyone/exercise-library/359/farmer-s-carry/\"},\"inclinefly\":{\"name\":\"ACE · Apertura posterior inclinada\",\"url\":\"https://www.acefitness.org/resources/everyone/exercise-library/34/incline-reverse-fly/\"},\"dbside\":{\"name\":\"ACE · Zancada lateral con carga\",\"url\":\"https://www.acefitness.org/resources/everyone/exercise-library/364/lateral-lunge/\"}}")
    global_TrainingGuides = jsonValue("[{\"title\":\"01 · Organizar una semana sostenible\",\"group\":\"General\",\"body\":\"Empieza por los días que realmente puedes reservar. En Perfil puedes combinar entrenamiento, descanso y movilidad. La app convierte esas elecciones en fechas reales durante 21 días; el ciclo no tiene que comenzar un lunes.\",\"points\":[\"Entrenamiento asigna ejercicios según tu objetivo. Descanso deja una misión de recuperación sin ejercicios. Movilidad utiliza fichas de movimiento suave compatibles.\",\"Un cambio de calendario actualiza solamente los días que aún no has iniciado. Si editaste una misión manualmente, revísala tú mismo después de cambiar el perfil.\",\"El calendario de recordatorios tiene sus propios controles. Comprueba en Recordatorios qué días y horas activaste.\"],\"sources\":[\"cdc\"]},{\"title\":\"02 · Preparar tu espacio\",\"group\":\"General\",\"body\":\"Antes de abrir la misión, despeja el recorrido, coloca el material a tu alcance y prueba la estabilidad de los apoyos. Una demostración no puede comprobar cómo es tu habitación.\",\"points\":[\"Usa un suelo donde los pies y la esterilla no se deslicen. Evita cargar peso sobre muebles que se mueven.\",\"Las fichas que indican banco requieren un banco de entrenamiento adecuado; no sustituyas ese requisito con una silla ligera.\",\"Comprueba que los cierres de las mancuernas ajustables estén colocados y que no haya objetos dentro del recorrido.\"],\"sources\":[\"nhs\"]},{\"title\":\"03 · Leer una ficha de ejercicio\",\"group\":\"General\",\"body\":\"Cada ficha separa preparación, ejecución, respiración, control y registro. Lee el movimiento completo antes de empezar. La demostración visual es esquemática y muestra un recorrido de referencia.\",\"points\":[\"Las fichas con dos lados piden la cantidad por lado. Comprueba esa indicación antes de anotar tus repeticiones.\",\"En ejercicios por tiempo registra segundos de trabajo, no el descanso.\",\"Las variantes de retorno lento o pausa comparten el movimiento base, pero cambian su ejecución. Sus instrucciones explican esa diferencia.\"],\"sources\":[\"mayo\"]},{\"title\":\"04 · Sentadillas y zancadas\",\"group\":\"Piernas\",\"body\":\"Las sentadillas combinan movimiento de cadera y rodilla. En una zancada el apoyo se reparte entre pies separados. Elige un recorrido que puedas repetir sin perder estabilidad.\",\"points\":[\"Mantén los pies apoyados y orienta las rodillas de acuerdo con los pies.\",\"En la zancada lateral, un paso excesivamente amplio dificulta el control. Acorta la separación cuando sea necesario.\",\"La carga es una opción posterior a dominar el movimiento; no una obligación para completar la misión.\"],\"sources\":[\"squat\",\"side\"]},{\"title\":\"05 · Bisagra y trabajo de cadera\",\"group\":\"Cadera\",\"body\":\"En una bisagra, la pelvis se desplaza hacia atrás mientras el torso se inclina desde la cadera. En el puente, la pelvis sube desde el suelo. Son patrones diferentes: mira la ficha correspondiente.\",\"points\":[\"No conviertas cada repetición de peso muerto en una búsqueda del suelo. El recorrido depende de mantener el control.\",\"Al finalizar un puente, vuelve hacia el suelo de forma gradual.\",\"Antes de probar el puente elevado, comprueba el requisito de banco y revisa su ficha.\"],\"sources\":[\"mayo\",\"hipthrust\"]},{\"title\":\"06 · Empujes: pared, suelo y banco\",\"group\":\"Pecho\",\"body\":\"La flexión en pared, la flexión de suelo y el press con mancuernas son alternativas distintas de empuje. La posición del cuerpo y el material cambian la dificultad.\",\"points\":[\"En pared, alinea el cuerpo y flexiona los codos sin dejar caer la pelvis.\",\"En el press de suelo, evita golpear los brazos contra el piso al descender.\",\"En el banco, prepara cómo tomar y dejar las mancuernas antes de empezar.\"],\"sources\":[\"nhs\",\"mayo\"]},{\"title\":\"07 · Tirones y control de espalda\",\"group\":\"Espalda\",\"body\":\"El remo desplaza la carga hacia el cuerpo. Una apertura posterior abre los brazos con cargas más pequeñas. Evita confundir sus recorridos y consulta la demostración o los pasos escritos.\",\"points\":[\"Usa el apoyo indicado si la ficha de remo lo requiere.\",\"No compenses la falta de control girando el torso.\",\"Una pausa breve puede servir para revisar la postura; no debe convertirse en una contracción máxima sostenida.\"],\"sources\":[\"mayo\",\"inclinefly\"]},{\"title\":\"08 · Brazos y hombros\",\"group\":\"Brazos\",\"body\":\"Los ejercicios de brazos pueden parecer pequeños, pero el balanceo del cuerpo cambia lo que estás haciendo. Escoge una carga que permita empezar y terminar cada repetición con control.\",\"points\":[\"En el curl, conserva los brazos cerca del torso y deja que los codos se flexionen.\",\"En elevaciones, una mancuerna ligera puede ser suficiente para practicar el recorrido.\",\"La app registra kg; no determina automáticamente cuánto debes levantar.\"],\"sources\":[\"nhs\",\"diagonal\"]},{\"title\":\"09 · Zona media y estabilidad\",\"group\":\"Core\",\"body\":\"El trabajo de zona media incluye mantener posiciones y mover extremidades mientras el tronco permanece controlado. Una plancha no necesita durar hasta que pierdas la posición.\",\"points\":[\"La app cuenta el tiempo real que introduces. Detenerte antes del objetivo no invalida el trabajo que ya hiciste.\",\"En ejercicios alternos, termina una repetición con control antes de cambiar de lado.\",\"Revisa los filtros de suelo, muñecas y hombros: muchos ejercicios de zona media cargan esas zonas.\"],\"sources\":[\"bear\",\"contra\"]},{\"title\":\"10 · Cardio en poco espacio\",\"group\":\"Cardio\",\"body\":\"Caminar en casa, hacer pasos laterales y alternar rodillas son opciones de movimiento continuo. Los ejercicios sin salto también pueden formar parte del cardio.\",\"points\":[\"Los CDC describen como referencia general para adultos 150 minutos semanales de actividad moderada, además de dos días de fortalecimiento. No es una dosis inicial individual.\",\"Puedes repartir la actividad en períodos pequeños. La duración programada en la app es aproximada y no implica que ya hayas alcanzado esa referencia.\",\"No añadas mancuernas a golpes de boxeo al aire de estas fichas.\"],\"sources\":[\"cdc\"]},{\"title\":\"11 · Movilidad y pausas\",\"group\":\"Movilidad\",\"body\":\"La movilidad puede ocupar un día elegido o una pausa breve. Haz los movimientos dentro de un recorrido cómodo y evita tratar una molestia como si fuera una meta de estiramiento.\",\"points\":[\"Las fichas de flexibilidad señalan una postura; no todas necesitan rebotes ni movimiento continuo.\",\"Un descanso completo sigue siendo una opción disponible en tu calendario.\",\"La sección de lesiones agrupa tus restricciones; no se transforma en una evaluación médica por seleccionar una zona.\"],\"sources\":[\"flex\"]},{\"title\":\"12 · Respiración durante las repeticiones\",\"group\":\"Hombros\",\"body\":\"Mantén la respiración durante el movimiento. Como orientación general, puedes expulsar el aire en la fase de mayor esfuerzo y tomarlo durante la fase más fácil.\",\"points\":[\"No uses aguantar el aire como objetivo del entrenamiento de esta app.\",\"El ritmo de la animación es una referencia visual; puedes necesitar ir más despacio.\",\"Si el movimiento te impide controlar la respiración, revisa duración, descanso y carga antes de continuar.\"],\"sources\":[\"mayo\"]},{\"title\":\"13 · Registrar esfuerzo y fatiga\",\"group\":\"Seguimiento\",\"body\":\"Igris dispone de un seguimiento al terminar la misión. Introduce tu esfuerzo de 1 a 10 según cómo resultó la sesión, y marca si hubo fatiga alta o dolor.\",\"points\":[\"Una casilla de misión confirma que terminaste, pero no inventa repeticiones ni carga. Para eso utiliza Registro detallado.\",\"La ausencia de datos de esfuerzo impide el aumento automático semanal.\",\"No cambies los valores para obtener una subida de nivel: los XP describen actividad registrada, no capacidad física medida.\"],\"sources\":[]},{\"title\":\"14 · Cómo funciona la revisión semanal\",\"group\":\"Seguimiento\",\"body\":\"El motor de reglas revisa los siete días anteriores al llegar a cada semana del ciclo. La revisión ocurre al abrir la app. Es una decisión programada y queda guardada con su motivo.\",\"points\":[\"Con suficientes sesiones completas y esfuerzo compatible puede sumar una repetición o cinco segundos dentro de sus límites.\",\"Sin registros suficientes mantiene el volumen. Con esfuerzo alto o fatiga lo reduce. El dolor activa el seguimiento y la revisión de restricciones.\",\"Estos umbrales son reglas del producto, no un protocolo clínico validado ni una valoración de tu técnica.\"],\"sources\":[]},{\"title\":\"15 · Sustituir y personalizar\",\"group\":\"Seguimiento\",\"body\":\"La ventana de misiones permite modificar títulos, cantidades y ejercicios pendientes. También puedes guardar plantillas personales desde Rutinas.\",\"points\":[\"Al añadir un ejercicio revisa equipo, dificultad y restricciones.\",\"Si una misión ya tiene cantidades registradas, el sistema conserva ese registro.\",\"Cambiar el perfil no sobrescribe una misión personalizada. Eso también significa que debes revisar su compatibilidad si aparecen nuevas restricciones.\"],\"sources\":[]},{\"title\":\"16 · Qué ofrece Igris\",\"group\":\"Seguimiento\",\"body\":\"Igris es una interfaz animada con respuestas locales y búsqueda del catálogo. Puede explicar cómo se configuró tu plan y mostrar las decisiones semanales.\",\"points\":[\"No es una IA generativa conectada a un servicio de conversación.\",\"No utiliza la cámara, no observa tus movimientos y no diagnostica lesiones.\",\"La animación del compañero puede desactivarse; las demostraciones de ejercicios se reproducen solo cuando pulsas el botón.\"],\"sources\":[]}]")
    dynamicValue((fun(): dynamic {
        val E: dynamic = global_Engine
        val rows: dynamic = jsonValue("[[\"bearcrawl\",\"Caminata de oso\",\"Core\",1,\"sec\",\"\",\"shoulder,elbow,wrist,back,hip,knee,ankle\",\"floor,wristLoad,kneeBend\",\"bear\",\"Apoya manos y puntas de los pies con rodillas flexionadas.|Avanza con pasos cortos de mano y pierna contrarias.|Mantén el tronco estable y detente antes de perder la posición.\",\"Evita elevar la cadera o avanzar rápido.\"],[\"childpose\",\"Postura del niño\",\"Movilidad\",0,\"sec\",\"\",\"shoulder,back,hip,knee,ankle\",\"floor,kneeBend,overhead,spineMove\",\"child\",\"Arrodíllate sobre una esterilla y apoya las manos delante.|Lleva la pelvis hacia los talones dentro de un recorrido cómodo.|Apoya el torso entre los muslos y respira sin forzar.\",\"No presiones las rodillas ni busques el suelo con dolor.\"],[\"contralateral\",\"Elevación contralateral boca abajo\",\"Espalda\",1,\"reps\",\"\",\"shoulder,back,hip\",\"floor,overhead\",\"contra\",\"Túmbate boca abajo con los brazos al frente.|Eleva ligeramente un brazo y la pierna contraria.|Baja suavemente y cambia de lado.\",\"Mantén el cuello alineado sin arquear la espalda.\"],[\"latstretch\",\"Estiramiento dorsal con apoyo\",\"Movilidad\",0,\"sec\",\"\",\"shoulder,wrist,back,hip,knee,ankle\",\"overhead,spineMove\",\"lat\",\"Coloca las manos sobre un apoyo fijo y separa los pies.|Lleva la cadera atrás dejando los brazos prolongar el torso.|Sostén una posición cómoda con rodillas sin bloqueo.\",\"El apoyo no debe moverse.\"],[\"bulgarian\",\"Sentadilla búlgara con mancuerna\",\"Piernas\",2,\"reps\",\"db,bench\",\"shoulder,wrist,back,hip,knee,ankle\",\"kneeBend\",\"bulgarian\",\"Sujeta una mancuerna junto al pecho y coloca el empeine de atrás sobre un banco estable.|Flexiona la pierna delantera descendiendo con control.|Vuelve a subir y completa después el otro lado.\",\"Practica primero la sentadilla dividida sin carga.\"],[\"diagonalraise\",\"Elevación diagonal con mancuerna\",\"Hombros\",1,\"reps\",\"db\",\"shoulder,elbow,wrist,back,hip,knee,ankle\",\"overhead\",\"diagonal\",\"Sujeta una mancuerna ligera frente a la cadera contraria.|Eleva el brazo en diagonal hasta una altura cómoda, como máximo a la altura del hombro.|Regresa despacio sin girar el cuerpo.\",\"Usa una carga pequeña y evita encoger el hombro.\"],[\"hipthrust\",\"Elevación de cadera con espalda en banco\",\"Cadera\",1,\"reps\",\"db,bench\",\"shoulder,back,hip,knee,ankle\",\"kneeBend\",\"hipthrust\",\"Apoya la parte alta de la espalda en un banco fijo y ambos pies en el suelo.|Sujeta una mancuerna protegida sobre la pelvis y eleva la cadera.|Desciende sin perder el apoyo del banco.\",\"No sobreextiendas la espalda al terminar la subida.\"],[\"farmerwalk\",\"Caminata del granjero con mancuernas\",\"Core\",1,\"sec\",\"db\",\"shoulder,elbow,wrist,back,hip,knee,ankle\",\"\",\"carry\",\"Sujeta una mancuerna a cada lado y despeja un trayecto.|Camina con pasos cortos manteniendo el torso erguido.|Gira con varios pasos y deja las cargas con control.\",\"No inclines el cuerpo ni eleves los hombros.\"],[\"inclinefly\",\"Apertura posterior con apoyo en banco\",\"Espalda\",1,\"reps\",\"db,bench\",\"shoulder,elbow,wrist,back,hip\",\"\",\"inclinefly\",\"Apoya el pecho en un banco inclinado y deja los brazos hacia abajo.|Abre ambos brazos con los codos ligeramente flexionados.|Baja las mancuernas lentamente.\",\"No rebotes contra el banco.\"],[\"dbsidelunge\",\"Zancada lateral con mancuernas\",\"Piernas\",2,\"reps\",\"db\",\"shoulder,wrist,back,hip,knee,ankle\",\"kneeBend\",\"dbside\",\"Sujeta mancuernas a los lados y da un paso lateral.|Lleva la cadera atrás y flexiona la pierna que recibe el peso.|Empuja el suelo para regresar y alterna.\",\"Acorta el paso si no controlas la alineación de la rodilla.\"],[\"vstep\",\"Paso en V sin salto\",\"Cardio\",0,\"sec\",\"\",\"hip,knee,ankle,back\",\"\",\"cdc\",\"Parte con los pies próximos y el recorrido libre.|Avanza un pie en diagonal y luego el otro para abrir la postura.|Retrocede un pie y después el otro; cambia el pie que inicia.\",\"Haz pasos pequeños y no cruces las piernas.\"],[\"heeldig\",\"Talón al frente alterno\",\"Cardio\",0,\"sec\",\"\",\"hip,knee,ankle,back\",\"\",\"cdc\",\"De pie, adelanta un pie apoyando suavemente el talón.|Vuelve al centro y cambia de lado.|Acompaña con un balanceo pequeño de brazos.\",\"No golpees el suelo ni estires la rodilla con brusquedad.\"],[\"doubletouch\",\"Doble paso lateral\",\"Cardio\",0,\"sec\",\"\",\"hip,knee,ankle,back\",\"\",\"cdc\",\"Da dos pasos cortos hacia un lado sin cruzar los pies.|Junta los pies de forma cómoda.|Repite hacia el otro lado con el espacio despejado.\",\"Mantén un ritmo que puedas controlar.\"],[\"frontbackstep\",\"Paso adelante y atrás\",\"Cardio\",0,\"sec\",\"\",\"hip,knee,ankle,back\",\"\",\"cdc\",\"Da un paso corto al frente y acerca el otro pie.|Vuelve a la posición inicial con dos pasos.|Alterna el pie que inicia el patrón.\",\"Mira el recorrido y evita retroceder cerca de muebles.\"],[\"boxupper\",\"Golpes ascendentes al aire sin carga\",\"Cardio\",1,\"sec\",\"\",\"shoulder,elbow,wrist,back,hip,knee,ankle\",\"\",\"ace\",\"Coloca las manos frente al pecho con codos flexionados.|Alterna pequeños golpes ascendentes sin estirar por completo el brazo.|Vuelve a la guardia con el torso controlado.\",\"Sin mancuernas ni movimientos explosivos.\"],[\"stepreach\",\"Paso lateral con alcance frontal\",\"Cardio\",0,\"sec\",\"\",\"shoulder,elbow,back,hip,knee,ankle\",\"\",\"cdc\",\"Da un paso lateral mientras extiendes un brazo al frente.|Recoge el brazo y vuelve al centro.|Alterna lados sin saltar.\",\"No eleves los hombros ni gires sobre un pie fijo.\"],[\"sidebendstand\",\"Inclinación lateral de pie\",\"Movilidad\",0,\"reps\",\"\",\"back,hip,knee,ankle\",\"spineMove\",\"flex\",\"De pie, deja los brazos a los lados.|Desliza una mano por el costado inclinando suavemente el torso.|Vuelve al centro y alterna.\",\"No añadas mancuernas ni fuerces el recorrido.\"],[\"bearhold\",\"Cuadrupedia con rodillas suspendidas\",\"Core\",1,\"sec\",\"\",\"shoulder,elbow,wrist,back,hip,knee,ankle\",\"floor,wristLoad,kneeBend\",\"bear\",\"Apoya las manos debajo de los hombros y las puntas de los pies.|Separa ligeramente las rodillas del suelo.|Mantén unos segundos y apóyalas con control.\",\"Variante estática de la preparación para la caminata de oso.\"]]")
        if (true) {
            val iterator3: dynamic = iteratorOf(rows)
            while (true) {
                val next4: dynamic = iterator3.next()
                if (truth(next4.done)) break
                var id: dynamic = absent
                var name: dynamic = absent
                var group: dynamic = absent
                var level: dynamic = absent
                var unit: dynamic = absent
                var req: dynamic = absent
                var zones: dynamic = absent
                var tags: dynamic = absent
                var source: dynamic = absent
                var steps: dynamic = absent
                var cue: dynamic = absent
                val unpacked5: dynamic = fromIterable(next4.value)
                id = unpacked5[0]
                name = unpacked5[1]
                group = unpacked5[2]
                level = unpacked5[3]
                unit = unpacked5[4]
                req = unpacked5[5]
                zones = unpacked5[6]
                tags = unpacked5[7]
                source = unpacked5[8]
                steps = unpacked5[9]
                cue = unpacked5[10]
                val requirements: dynamic = (if (truth(req)) req.split(",") else js("[]"))
                val flags: dynamic = global_Object.fromEntries(tags.split(",").filter(global_Boolean).map((fun(t: dynamic): dynamic {
                    return arrayOf<dynamic>(t, true)
                })))
                val e: dynamic = jsObject { obj6 ->
                    obj6["id"] = id
                    obj6["name"] = name
                    obj6["group"] = group
                    obj6["level"] = level
                    obj6["unit"] = unit
                    obj6["requirements"] = requirements
                    obj6["equipment"] = (if (truth(requirements.includes("db"))) "db" else "body")
                    obj6["zones"] = zones.split(",")
                    copyFields(obj6, flags)
                    obj6["seated"] = false
                    obj6["baseId"] = id
                    obj6["variant"] = "base"
                    obj6["source"] = source
                    obj6["sourceScope"] = (if (truth(dynamicValue(jsonValue("[\"cdc\",\"ace\"]")).includes(source))) "general" else "technical")
                    obj6["sides"] = dynamicValue(jsonValue("[\"contralateral\",\"bulgarian\",\"diagonalraise\",\"dbsidelunge\"]")).includes(id)
                    obj6["category"] = (if (truth(dynamicValue(jsonValue("[\"Cardio\",\"Movilidad\"]")).includes(group))) group else "Fuerza")
                    obj6["impact"] = "Bajo"
                    obj6["instructions"] = steps.split("|")
                    obj6["steps"] = steps.replaceAll("|", " ")
                    obj6["cue"] = cue
                    obj6["setup"] = "Prepara el espacio y comprueba los apoyos indicados."
                    obj6["breath"] = "Respira durante todo el movimiento."
                    obj6["tempo"] = (if (truth((unit === "sec"))) "Usa un ritmo cómodo durante el intervalo." else "Movimiento controlado, sin rebotes.")
                    obj6["control"] = "Interrumpe el ejercicio si aparece dolor o pierdes estabilidad."
                }
                E.exercises.push(e)
            }
        }
        run {
            val target7: dynamic = global_Catalog
            target7.baseExercises = jsAdd(target7.baseExercises, rows.length)
        }
        if (true) {
            val iterator9: dynamic = iteratorOf(global_Object.entries(jsonValue("{\"squat\":\"squat\",\"goblet\":\"squat\",\"wall\":\"nhs\",\"curl\":\"nhs\",\"calf\":\"nhs\",\"lateralunge\":\"side\",\"standingabduct\":\"nhs\",\"standingextend\":\"nhs\",\"calfstretch\":\"flex\"}")))
            while (true) {
                val next10: dynamic = iterator9.next()
                if (truth(next10.done)) break
                var id: dynamic = absent
                var source: dynamic = absent
                val unpacked11: dynamic = fromIterable(next10.value)
                id = unpacked11[0]
                source = unpacked11[1]
                if (true) {
                    val iterator12: dynamic = iteratorOf(E.exercises.filter((fun(x: dynamic): dynamic {
                        return (x.baseId === id)
                    })))
                    while (true) {
                        val next13: dynamic = iterator12.next()
                        if (truth(next13.done)) break
                        var e: dynamic = absent
                        e = next13.value
                        e.source = source
                    }
                }
            }
        }
        val routineRows: dynamic = jsonValue("[[\"Semana activa A\",\"Mixta\",\"squat wall bridge march\"],[\"Semana activa B\",\"Mixta\",\"goblet row curl vstep\"],[\"Cardio de pasos en V\",\"Cardio\",\"vstep heeldig doubletouch frontbackstep\"],[\"Coordinación sin saltos\",\"Cardio\",\"stepreach sidestep heeldig vstep\"],[\"Boxeo y desplazamientos\",\"Cardio\",\"box boxupper doubletouch shadowcombo\"],[\"Cardio de bajo impacto\",\"Cardio\",\"march vstep stepreach heeldig\"],[\"Sesión de fuerza inferior\",\"Fuerza\",\"goblet rdl calf bridge\"],[\"Tren superior con banco\",\"Fuerza\",\"benchpress inclinefly seatedcurl lateral\"],[\"Fuerza de agarre en casa\",\"Fuerza\",\"farmerwalk hammer farmerhold\"],[\"Cadera con banco\",\"Fuerza\",\"hipthrust rdl standingabduct\"],[\"Piernas con apoyo posterior\",\"Fuerza\",\"bulgarian dbcalf dbbridge\"],[\"Control de zona media\",\"Fuerza\",\"bearhold bird deadbug\"],[\"Coordinación en el suelo\",\"Fuerza\",\"bearcrawl contralateral kneelingplank\"],[\"Movilidad de espalda\",\"Movilidad\",\"childpose latstretch cat\"],[\"Pausa sin esterilla\",\"Movilidad\",\"sidebendstand shoulder ankle\"],[\"Preparación para sentadillas\",\"Movilidad\",\"ankle hipcircle squat\"],[\"Empujes y pasos\",\"Mixta\",\"wall press heeldig\"],[\"Tirones y cardio\",\"Mixta\",\"row inclinefly doubletouch\"],[\"Sesión breve de tres patrones\",\"Fuerza\",\"squat wall hinge\"],[\"Mancuernas y control unilateral\",\"Fuerza\",\"dbsidelunge row diagonalraise\"],[\"Domingo de movilidad\",\"Movilidad\",\"childpose calfstretch breathing\"],[\"Activación entre sesiones\",\"Cardio\",\"walk frontbackstep stepjack\"],[\"Práctica de espalda con apoyo\",\"Fuerza\",\"row inclinefly scapular\"],[\"Cadena posterior en casa\",\"Fuerza\",\"rdl bridge contralateral\"]]")
        routineRows.forEach((fun(arg16: dynamic, n: dynamic): dynamic {
            var name: dynamic = absent
            var category: dynamic = absent
            var ids: dynamic = absent
            val unpacked17: dynamic = fromIterable(arg16)
            name = unpacked17[0]
            category = unpacked17[1]
            ids = unpacked17[2]
            val items: dynamic = ids.split(" ").map((fun(id: dynamic): dynamic {
                return jsObject { obj18 ->
                    obj18["id"] = id
                    obj18["sets"] = (if (truth((category === "Movilidad"))) 1 else 2)
                    obj18["target"] = (if (truth((E.byId(id).unit === "sec"))) 25 else 8)
                    obj18["rest"] = (if (truth((category === "Fuerza"))) 90 else 40)
                }
            }))
            global_Catalog.routines.push(jsObject { obj19 ->
                obj19["id"] = jsAdd("v5routine", n)
                obj19["baseRoutineId"] = jsAdd("v5routine", n)
                obj19["variant"] = "base"
                obj19["name"] = name
                obj19["category"] = category
                obj19["items"] = items
                obj19["description"] = "Combinación editorial para revisar con tus filtros; puedes modificar sus ejercicios y cantidades."
                obj19["requirements"] = jsArray { array20 ->
                    appendAll(array20, jsConstruct(global_Set, arrayOf<dynamic>(items.flatMap((fun(x: dynamic): dynamic {
                        return E.byId(x.id).requirements
                    })))))
                }
                obj19["level"] = run { val target22: dynamic = global_Math; invokeWith(target22.max, target22, jsArray { array21 ->
                    appendAll(array21, items.map((fun(x: dynamic): dynamic {
                        return E.byId(x.id).level
                    })))
                }) }
                obj19["impact"] = "Sin saltos"
                obj19["minutes"] = global_Catalog.duration(items)
            })
            return absent
        }))
        run {
            val target23: dynamic = global_Catalog
            target23.baseRoutines = jsAdd(target23.baseRoutines, routineRows.length)
        }
        return absent
    }))()
}
