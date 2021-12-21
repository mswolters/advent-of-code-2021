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
            .flatten()
            .filter { (isLowest, _) -> isLowest }
            .sumOf { (_, height) -> height + 1 }
    }

    fun part2(input: List<String>): Int {
        data class Element(val value: Int, val x: Int, val y: Int, var basin: Int) {
            fun neighbours(grid: List<List<Element>>): List<Element> {
                val up = if (y == 0) null else grid[y - 1][x]
                val left = if (x == 0) null else grid[y][x - 1]
                val right = if (x == grid[y].size - 1) null else grid[y][x + 1]
                val down = if (y == grid.size - 1) null else grid[y + 1][x]
                return listOfNotNull(up, left, right, down)
            }

            override fun toString(): String {
                return "($value, #$basin)"
            }
        }
        fun readGrid(input: List<String>): List<List<Element>> {
            return input.map { it.toCharArray()}
                .mapIndexed { y, line -> line.mapIndexed { x, char -> Element(char.digitToInt(), x, y, 0) } }
        }

        fun flow(grid: List<List<Element>>, element: Element, basin: Int) {
            if (element.value == 9) return
            if (element.basin != 0) return
            element.basin = basin
            element.neighbours(grid).forEach { flow(grid, it, basin) }
        }

        val grid = readGrid(input)
        var basin = 0
        return grid.flatten()
            .onEach { basin++; flow(grid, it, basin) }
            .groupBy { it.basin }
            .filterKeys { it != 0 }
            .values.map { it.size }.sorted()
            .takeLast(3)
            .fold(1) { acc, size -> acc * size }
    }

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}