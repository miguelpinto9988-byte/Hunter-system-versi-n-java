private val root: dynamic = js("typeof window === 'undefined' ? globalThis : window")
private val jsArray: dynamic = js("Array")
private val jsNumber: dynamic = js("Number")
private val jsString: dynamic = js("String")
private val jsObject: dynamic = js("Object")
private val truth: dynamic = js("Boolean")
private val equipment = setOf("body", "db", "gym")
private val tiers = listOf("basic", "intermediate", "advanced")

private fun eligible(exercise: dynamic, profile: dynamic): Boolean {
    if (!(truth(exercise) as Boolean) || !(truth(profile) as Boolean) || jsTypeOf(exercise.id) != "string") return false
    val gear = jsString(if (truth(profile.equipment) as Boolean) profile.equipment else "body") as String
    val uses = jsString(if (truth(exercise.equipment) as Boolean) exercise.equipment else "") as String
    val supportsDumbbells = gear == "db" || gear == "gym"
    if (uses !in equipment || uses == "db" && !supportsDumbbells) return false
    if ((uses == "gym" || exercise.gymOnly23 === true) && gear != "gym") return false
    val requirements = exercise.requirements
    if (jsArray.isArray(requirements) as Boolean) {
        if ((requirements.includes("db") as Boolean) && !supportsDumbbells) return false
        if ((requirements.includes("gym") as Boolean) && gear != "gym") return false
    }
    val tier = if (jsTypeOf(profile.difficulty) == "string") tiers.indexOf(profile.difficulty as String) else -1
    val maxLevel = if (tier >= 0) tier else if (profile.experience === "regular") 1 else 0
    val level = exercise.level
    if (!(jsNumber.isFinite(level) as Boolean) || level < 0) return false
    val groupedLevel = root.HunterGroups49?.level(exercise) ?: level
    return !(groupedLevel > maxLevel)
}

private fun weekSegment(index: dynamic): Double? {
    if (!(jsNumber.isSafeInteger(index) as Boolean) || index < 0) return null
    return kotlin.math.floor((index as Double) / 7)
}

private class Week {
    var days = 0
    var active = 0
    var assignments = 0
    val families: dynamic = js("new Set()")

    fun summary(): dynamic {
        val result = js("({})")
        result.days = days
        result.active = active
        result.assignments = assignments
        result.distinct = families.size
        return result
    }
}

private fun report(cycle: dynamic): dynamic {
    val weeks = Array(3) { Week() }
    val days = cycle?.days
    if (jsArray.isArray(days) as Boolean) {
        for (index in 0 until minOf(days.length as Int, 21)) {
            val day = days[index]
            val week = weeks[index / 7]
            week.days++
            val choice = day?.scheduleChoice
            if ((truth(choice) as Boolean) && choice !== "rest") week.active++
            val items = day?.items
            if (jsArray.isArray(items) as Boolean) {
                for (itemIndex in 0 until (items.length as Int)) {
                    val id = items[itemIndex]?.id
                    if (truth(id) as Boolean) {
                        week.assignments++
                        week.families.add(id)
                    }
                }
            }
        }
    }
    val result = js("({})")
    result.weeks = js("[]")
    for (week in weeks) result.weeks.push(week.summary())
    result.note = "Inventario interno: no valida posturas ni recomendaciones médicas."
    return result
}

fun main() {
    if (truth(root.HunterSchedule3813) as Boolean) return
    val schedule = js("({})")
    schedule.version = "38.13"
    schedule.eligible = ::eligible
    schedule.weekSegment = ::weekSegment
    schedule.report = ::report
    root.HunterSchedule3813 = jsObject.freeze(schedule)
}
