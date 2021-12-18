import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

fun List<String>.asInts(): List<Int> = map { it.toInt() }

/**
 * Like Iterable<T>.chunked, but by columns. Every list will contain every nth element of the starting element.
 */
fun <T> Iterable<T>.byNth(n: Int): List<List<T>> {
    val result = List(n) { mutableListOf<T>() }
    forEachIndexed { i: Int, t: T ->
        result[i.mod(n)].add(t)
    }
    return result
}

fun Int.pow(exponent: Int): Int = toBigInteger().pow(exponent).toInt()
