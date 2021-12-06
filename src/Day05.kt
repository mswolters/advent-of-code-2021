import kotlin.math.*

fun main() {
    fun part1(input: List<String>): Int {
        val lines = input.map { parseLine(it) }
        val map = HashMap<Coordinate, Int>()
        lines.filter { it.start.y == it.end.y || it.start.x == it.end.x }
            .flatMap { it.allCoordinates() }
            .forEach { map.merge(it, 1) { old, new -> old + new } }

        return map.values.count { it > 1 }
    }

    fun part2(input: List<String>): Int {
        val lines = input.map { parseLine(it) }
        val map = HashMap<Coordinate, Int>()
        lines.flatMap { it.allCoordinates() }
            .forEach { map.merge(it, 1) { old, new -> old + new } }

        return map.values.count { it > 1 }
    }

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}

fun parseLine(input: String): Line {
    val (begin, end) = input.split(" -> ").map { parseCoordinate(it) }
    return Line(begin, end)
}

fun parseCoordinate(input: String): Coordinate {
    val (x, y) = input.split(",").map { it.toInt() }
    return Coordinate(x, y)
}

data class Coordinate(val x: Int, val y: Int)
data class Line(val start: Coordinate, val end: Coordinate) {
    fun allCoordinates(): List<Coordinate> {
        val coordinates = mutableListOf<Coordinate>()
        val minX = min(start.x, end.x)
        val maxX = max(start.x, end.x)
        val minY = min(start.y, end.y)
        val maxY = max(start.y, end.y)
        if (start.x == end.x) {
            for (y in minY..maxY) {
                coordinates += Coordinate(start.x, y)
            }
        } else if (start.y == end.y) {
            for (x in minX..maxX) {
                coordinates += Coordinate(x, start.y)
            }
        } else {
            val distance = maxX - minX
            for (i in 0..distance) {
                val newX = start.x + if (start.x == minX) { i } else { -i }
                val newY = start.y + if (start.y == minY) { i } else { -i }
                coordinates += Coordinate(newX, newY)
            }
        }
        return coordinates
    }
}