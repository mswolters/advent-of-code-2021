fun main() {
    // Find the first board that wins
    fun part1(input: List<String>): Int {
        val numbers = input.first().split(',').asInts()
        var boards = parseBoards(input.drop(2))

        for (number in numbers) {
            boards = boards.map { it.withCrossed(number) }
            val finishedBoards = boards.filter { complete(it) }
            if (finishedBoards.any()) {
                return finishedBoards.single().numbers.filter { !it.crossed }.sumOf { it.number } * number
            }
        }

        throw IllegalStateException("No result?")
    }

    // Find the last board that wins
    fun part2(input: List<String>): Int {
        val numbers = input.first().split(',').asInts()
        var boards = parseBoards(input.drop(2))

        for (number in numbers) {
            val incompleteBoards = boards.map { it.withCrossed(number) }.filterNot { complete(it) }
            if (boards.size == 1 && incompleteBoards.isEmpty()) {
                return boards.single().withCrossed(number).numbers.filter { !it.crossed }.sumOf { it.number } * number
            }
            boards = incompleteBoards
        }

        throw IllegalStateException("No result?")
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

fun complete(board: Board): Boolean {
    return anyFinished(board.columns) || anyFinished(board.rows)
}

data class BingoNumber(val number: Int, val crossed: Boolean = false) {
    override fun toString(): String {
        return if (crossed) {
            "x$number"
        } else {
            number.toString()
        }
    }
}