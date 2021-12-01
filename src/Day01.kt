fun main() {
    fun part1(input: List<String>): Int {
        return input.asInts()
            .zipWithNext()
            .count { (first, last) -> last > first }
    }

    fun part2(input: List<String>): Int {
        return input.asInts()
            .windowed(size = 3) { it.sum() }
            .zipWithNext()
            .count { (first, last) -> last > first }
    }

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}

