fun main() {
    fun part1(input: List<String>): Int {

        val gamma = onesPerPosition(input.map { bitValues(it) })
            .map {
                if (it > input.size / 2) {
                    1
                } else {
                    0
                }
            }
        val epsilon = gamma.map {
            when (it) {
                1 -> 0
                else -> 1
            }
        }

        return intValue(gamma) * intValue(epsilon)
    }

    fun part2(input: List<String>): Int {
        var possibleOxygen = input.map { bitValues(it) }
        var possibleCO2 = possibleOxygen
        for (i in input.first().indices) {
            if (possibleOxygen.isNotEmpty()) {
                val onesPerPositionOxygen = onesPerPosition(possibleOxygen)
                possibleOxygen = if (onesPerPositionOxygen[i] >= possibleOxygen.size / 2.0) {
                    possibleOxygen.filter { it[i] == 1 }
                } else {
                    possibleOxygen.filter { it[i] == 0 }
                }
            }
            if (possibleCO2.isNotEmpty()) {
                val onesPerPositionCO2 = onesPerPosition(possibleCO2)
                if (onesPerPositionCO2[i] != possibleCO2.size && onesPerPositionCO2[i] != 0) {
                    possibleCO2 = if (onesPerPositionCO2[i] >= possibleCO2.size / 2.0) {
                        possibleCO2.filter { it[i] == 0 }
                    } else {
                        possibleCO2.filter { it[i] == 1 }
                    }
                }
            }
        }

        return intValue(possibleOxygen.first()) * intValue(possibleCO2.first())
    }

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}

fun bitValues(input: String): List<Int> {
    return input.toCharArray().map { it.digitToInt() }
}

fun onesPerPosition(input: List<List<Int>>): List<Int> {
    val measurementSize = input.first().size
    return input.fold(List(measurementSize) { 0 }) { acc, rate -> List(measurementSize) { index -> acc[index] + rate[index] } }
}

fun intValue(binary: List<Int>): Int {
    return binary.joinToString(separator = "").toInt(2)
}