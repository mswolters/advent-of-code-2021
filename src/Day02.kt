import java.lang.IllegalArgumentException

fun main() {
    fun part1(input: List<String>): Int {
        val result = input.map { parsePosition(it) }
            .fold(Position(0, 0)) { left, right -> left + right}
        return result.depth * result.forward
    }

    fun part2(input: List<String>): Int {
        val result = input.map { parsePosition(it) }
            .fold(AimedPosition(0, 0 ,0)) { aimed, position -> aimed + position }
        return result.depth * result.forward
    }

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

data class Position(val depth: Int, val forward: Int) {
    operator fun plus(other: Position): Position {
        return Position(depth + other.depth, forward + other.forward)
    }
}

data class AimedPosition(val depth: Int, val forward: Int, val aim: Int) {
    operator fun plus(position: Position): AimedPosition {
        val newAim = aim + position.depth
        return AimedPosition(depth + position.forward * newAim, forward + position.forward, newAim)
    }
}

fun parsePosition(text: String): Position {
    val (direction, numberText) = text.split(' ')
    val number = numberText.toInt()
    return when (direction) {
        "forward" -> Position(0, number)
        "down" -> Position(number, 0)
        "up" -> Position(-number, 0)
        else -> throw IllegalArgumentException()
    }
}
