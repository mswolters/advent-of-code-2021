fun main() {
    fun part1(input: List<String>): Int {
        val measurementSize = input.first().length
        val gamma = input.map { bitValues(it) }
            .fold(List(measurementSize) { 0 }) { acc, rate -> List(measurementSize) { index -> acc[index] + rate[index] } }
            .map {
                if (it > input.size / 2) {
                    1
                } else {
                    0
                }
            }
        val epsilon = gamma.map {
            when (it) {
                1 -> 0
                else -> 1
            }
        }

        return intValue(gamma) * intValue(epsilon)
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}

fun bitValues(input: String): List<Int> {
    return input.toCharArray().map { it.digitToInt() }
}

fun intValue(binary: List<Int>): Int {
    return binary.joinToString(separator = "").toInt(2)
}