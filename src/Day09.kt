fun main() {
    fun part1(input: List<String>): Int {
        data class Grid(val grid: List<List<Int>>) {
            fun <R> map(transform: (value: Int, adjacent: List<Int>) -> R): List<List<R>> {
                return grid.mapIndexed { y, row ->
                    row.mapIndexed { x, value ->
                        val up = if (y == 0) 10 else grid[y - 1][x]
                        val left = if (x == 0) 10 else grid[y][x - 1]
                        val right = if (x == row.size - 1) 10 else grid[y][x + 1]
                        val down = if (y == grid.size - 1) 10 else grid[y + 1][x]
                        transform(value, listOf(up, left, right, down))
                    }
                }
            }
        }
        fun readGrid(input: List<String>): Grid {
            return Grid(input.map { it.toCharArray() }
                .map { it.map { char -> char.digitToInt() } })
        }

        return readGrid(input).map { value, adjacent -> (adjacent.all { value < it }) to value }
            .onEach { println(it) }
            .flatten()
            .filter { (isLowest, _) -> isLowest }
            .sumOf { (_, height) -> height + 1 }
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}