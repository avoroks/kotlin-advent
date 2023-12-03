package day02

import readInput

data class Set(val red: Int, val green: Int, val blue: Int)
data class Game(val id: Int, val sets: List<Set>)

fun main() {
    println(part1())
    println(part2())
}

fun parse(input: List<String>) = input.map {
    val (game, sets) = it.split(":")
    val id = "Game (\\d+)".toRegex().find(game)!!.groupValues[1].toInt()

    sets.split(";").map {
        val red = "(\\d+) red".toRegex().find(it)?.groupValues?.get(1)?.toInt() ?: 0
        val green = "(\\d+) green".toRegex().find(it)?.groupValues?.get(1)?.toInt() ?: 0
        val blue = "(\\d+) blue".toRegex().find(it)?.groupValues?.get(1)?.toInt() ?: 0
        Set(red, green, blue)
    }.let {
        Game(id, it)
    }
}

fun part1(): Int {
    val lines = readInput("day02/Day02_part1")
    val expectedRed = 12
    val expectedGreen = 13
    val expectedBlue = 14
    return parse(lines).filter { it.sets.all { set ->  set.red <= expectedRed && set.green <= expectedGreen && set.blue <= expectedBlue} }.sumOf { it.id }
}

fun part2(): Int {
    val lines = readInput("day02/Day02_part2")
    return parse(lines).map { game ->
            val maxGreen = game.sets.maxOfOrNull { it.green } ?: 0
            val maxRed = game.sets.maxOfOrNull { it.red } ?: 0
            val maxBlue = game.sets.maxOfOrNull { it.blue } ?: 0
            maxGreen * maxRed * maxBlue
    }.sumOf { it }
}