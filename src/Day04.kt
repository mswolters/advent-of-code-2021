fun main() {
    // Find the first board that wins
    fun part1(input: List<String>): Int {
        val numbers = input.first().split(',').asInts()
        var boards = parseBoards(input.drop(2))

        for (number in numbers) {
            boards = boards.map { it.withCrossed(number) }
            val finishedBoards = boards.filter { anyFinished(it.columns) || anyFinished(it.rows) }
            if (finishedBoards.any()) {
                return finishedBoards.single().numbers.filter { !it.crossed }.sumOf { it.number } * number
            }
        }

        throw IllegalStateException("No result?")
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}

data class Board(val numbers: List<BingoNumber>) {
    val columns: List<List<BingoNumber>> get() = numbers.byNth(5)
    val rows: List<List<BingoNumber>> get() = numbers.chunked(5)

    fun withCrossed(number: Int): Board {
        return Board(numbers.map {
            when (it.number) {
                number -> it.copy(crossed = true)
                else -> it
            }
        })
    }

    override fun toString(): String {
        return rows.joinToString(separator = "\n")
    }
}

fun parseBoards(input: List<String>): List<Board> {
    return input.chunked(6)
        .filter { it.isNotEmpty() }
        .map { it.flatMap { row -> row.split("\\s+".toRegex()).filter { potentialNumber -> potentialNumber.isNotEmpty() }.asInts() } }
        .map { Board(it.map { number -> BingoNumber(number) }) }
}

fun anyFinished(numbers: List<List<BingoNumber>>): Boolean = numbers.any { it.all { number -> number.crossed } }

data class BingoNumber(val number: Int, val crossed: Boolean = false) {
    override fun toString(): String {
        return if (crossed) {
            "x$number"
        } else {
            number.toString()
        }
    }
}