import java.lang.IllegalArgumentException

fun main() {
    fun part1(input: List<String>): Int {
        var ageAndCounts = input.single().split(',').asInts()
            .groupBy { it }
            .mapValues { (_, value) -> value.size }
        for (x in 1..80) {
            ageAndCounts = stepDay(ageAndCounts)
        }
        return ageAndCounts.values.sum()
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}

fun stepDay(ageAndCounts: Map<Int, Int>): Map<Int, Int> {
    val result = mutableMapOf<Int, Int>()
    ageAndCounts.forEach { (age, count) ->
        when (age) {
            in 1..8 -> result.merge(age - 1, count) { a, b -> a + b }
            0 -> {
                result.merge(6, count) { a, b -> a + b }
                result[8] = count
            }
            else -> throw IllegalArgumentException("Unknown age $age")
        }
    }
    return result
}