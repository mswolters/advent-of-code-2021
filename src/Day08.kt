import kotlin.math.pow

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
        fun parseSegments(input: String): Set<Char> {
            return input.toCharArray().toSet()
        }

        fun buildSegmentMapping(uniqueSegments: List<String>): Map<Set<Char>, Int> {
            val rawSegments = uniqueSegments.map { parseSegments(it) }
            val one = rawSegments.single { it.size == 2 }
            val seven = rawSegments.single { it.size == 3 }
            val four = rawSegments.single { it.size == 4 }
            val eight = rawSegments.single { it.size == 7 }
            val nine = rawSegments.filter { it.size == 6 }.single { (four - it).isEmpty() }
            val three = rawSegments.filter { it.size == 5 }.single { (it - one).size == 3 }
            val two = rawSegments.filter { it.size == 5 }.single { (it - four).size == 3 }
            val five = rawSegments.filter { it.size == 5 }.single { it != three && it != two }
            val zero = rawSegments.filter { it.size == 6 }.single { it != nine && (one - it).isEmpty() }
            val six = rawSegments.filter { it.size == 6 }.single { it != nine && it != zero }
            return mapOf(
                zero to 0,
                one to 1,
                two to 2,
                three to 3,
                four to 4,
                five to 5,
                six to 6,
                seven to 7,
                eight to 8,
                nine to 9
            )
        }

        fun toNumber(input: List<Int>): Int {
            return input.mapIndexed { index, number -> number * 10.pow(3 - index) }.sum()
        }
        return input.map { parseSegmentData(it) }
            .map { buildSegmentMapping(it.uniqueSegments) to it }
            .map { (segments, data) -> data.output.map { parseSegments(it) }.map { segments[it]!! } }
            .sumOf { toNumber(it) }
    }

    val input = readInput("Day08")
    println(part1(input))
    println(part2(readInput("Day08")))
}

data class SegmentData(val uniqueSegments: List<String>, val output: List<String>)