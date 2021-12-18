import kotlin.math.abs
import kotlin.math.min

fun main() {
    fun part1(input: List<String>): Int {
        fun cost(a: Int, b: Int) = abs(a - b)

        val positions = input.single().split(',').asInts()
        var min = Int.MAX_VALUE
        for (x in positions.minOf { it } .. positions.maxOf { it }) {
            val totalFuel = positions.sumOf { cost(it, x) }
            min = min(totalFuel, min)
        }
        return min
    }

    fun part2(input: List<String>): Int {
        fun cost(a: Int, b: Int): Int {
            val distance = abs(a - b)
            return distance * (distance + 1) / 2
        }

        val positions = input.single().split(',').asInts()
        var min = Int.MAX_VALUE
        for (x in positions.minOf { it } .. positions.maxOf { it }) {
            val totalFuel = positions.sumOf { cost(it, x)  }
            min = min(totalFuel, min)
        }
        return min
    }

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}