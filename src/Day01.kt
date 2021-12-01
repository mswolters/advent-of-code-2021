fun main() {
    fun part1(input: List<String>): Int {
        return input.asSequence()
            .windowed(2)
            .count { elements -> isLarger(elements.first(), elements.last()) }
    }

    fun part2(input: List<String>): Int {
        return input.asSequence()
            .windowed(size = 3, partialWindows = true)
            .map { it.asInts() }
            .map { it.sum() }
            .windowed(2)
            .count { elements -> elements.last() > elements.first() }
    }

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}

fun isLarger(previous: String, current: String): Boolean {
    return current.toInt() > previous.toInt()
}

