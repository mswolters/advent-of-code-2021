fun main() {
    fun part1(input: List<String>): Int {
        return input.asInts().asSequence()
            .windowed(2)
            .count { elements -> elements.last() > elements.first() }
    }

    fun part2(input: List<String>): Int {
        return input.asInts().asSequence()
            .windowed(size = 3, partialWindows = true)
            .map { it.sum() }
            .windowed(2)
            .count { elements -> elements.last() > elements.first() }
    }

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}

