fun main() {
    fun parseSegmentData(input: String): SegmentData {
        val (segments, outputs) = input.split(" | ")
        return SegmentData(segments.split(" "), outputs.split(" "))
    }

    fun part1(input: List<String>): Int {
        val data = input.flatMap { parseSegmentData(it).output }
        return data.count { it.length in listOf(7, 4, 2, 3) }
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}

data class SegmentData(val uniqueSegments: List<String>, val output: List<String>)