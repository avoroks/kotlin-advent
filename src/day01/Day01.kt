package day01

import readInput

private val words = mapOf(
    "one" to 1,
    "two" to 2,
    "three" to 3,
    "four" to 4,
    "five" to 5,
    "six" to 6,
    "seven" to 7,
    "eight" to 8,
    "nine" to 9
)

fun main() {
    println(part1())
    println(part2())
}

fun part1(): Int {
    val lines = readInput("day01/Day01_part1")
    return lines.sumOf { it.getIntValue() }
}

fun part2(): Int {
    val lines = readInput("day01/Day01_part2")
    return lines.sumOf { row ->
        row.mapIndexedNotNull { index, char -> // mapIndexedNotNull - перебираем посимвольно
            if (char.isDigit()) char
            else row.possibleWordsAt(index).firstNotNullOfOrNull { candidate -> // firstNotNullOfOrNull - первое не null ИЛИ null
                words[candidate]
            }
        }.joinToString().getIntValue()
    }
}

private fun String.getIntValue() = "${this.first { it.isDigit() }}${this.last { it.isDigit() }}".toInt()

private fun String.possibleWordsAt(startAt: Int): List<String> =
    (3..5).map { len ->
        substring(startAt, (startAt + len).coerceAtMost(length)) // Int.coerceAtMost(length) - либо int, либо если достигнут length, то length. Т.е. значение меньше length или length
    }


