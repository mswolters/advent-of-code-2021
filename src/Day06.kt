import java.lang.IllegalArgumentException

fun main() {
    fun part1(input: List<String>): Long {
        var ageAndCounts = input.single().split(',').asInts().map { it.toLong() }
            .groupBy { it }
            .mapValues { (_, value) -> value.size.toLong() }
        for (x in 1..80) {
            ageAndCounts = stepDay(ageAndCounts)
        }
        return ageAndCounts.values.sum()
    }

    fun part2(input: List<String>): Long {
        var ageAndCounts = input.single().split(',').asInts().map { it.toLong() }
            .groupBy { it }
            .mapValues { (_, value) -> value.size.toLong() }
        for (x in 1..256) {
            ageAndCounts = stepDay(ageAndCounts)
        }
        return ageAndCounts.values.sum()
    }

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}

fun stepDay(ageAndCounts: Map<Long, Long>): Map<Long, Long> {
    val result = mutableMapOf<Long, Long>()
    ageAndCounts.forEach { (age, count) ->
        when (age) {
            in 1..8 -> result.merge(age - 1, count) { a, b -> a + b }
            0L -> {
                result.merge(6, count) { a, b -> a + b }
                result[8] = count
            }
            else -> throw IllegalArgumentException("Unknown age $age")
        }
    }
    return result
}