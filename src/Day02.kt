fun main() {
    val strategyShapeMap = mapOf(
        "A" to Shape.Rock,
        "X" to Shape.Rock,
        "B" to Shape.Paper,
        "Y" to Shape.Paper,
        "C" to Shape.Scissors,
        "Z" to Shape.Scissors,
    )

    val strategyResultMap = mapOf(
        "X" to Result.LOSE,
        "Y" to Result.DRAW,
        "Z" to Result.WIN
    )

    fun part1(input: List<String>): Int {
        return input.map {
            val round = it.split(" ")
            val opponent = strategyShapeMap[round[0]]!!
            val me = strategyShapeMap[round[1]]!!
            return@map me.score + me.result(opponent).score
        }.sum()
    }

    fun part2(input: List<String>): Int {
        return input.map {
            val round = it.split(" ")
            val opponent = strategyShapeMap[round[0]]!!
            val result = strategyResultMap[round[1]]!!
            return@map opponent.me(result).score + result.score
        }.sum()
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

sealed class Shape(val score: Int) {

    abstract fun result(opponent: Shape): Result

    abstract fun me(result: Result): Shape

    object Rock : Shape(score = 1) {
        override fun result(opponent: Shape): Result {
            return when (opponent) {
                is Paper -> Result.LOSE
                is Scissors -> Result.WIN
                is Rock -> Result.DRAW
            }
        }

        override fun me(result: Result): Shape {
            return when (result) {
                Result.WIN -> Paper
                Result.LOSE -> Scissors
                Result.DRAW -> Rock
            }
        }
    }

    object Paper : Shape(score = 2) {
        override fun result(opponent: Shape): Result {
            return when (opponent) {
                is Paper -> Result.DRAW
                is Scissors -> Result.LOSE
                is Rock -> Result.WIN
            }
        }

        override fun me(result: Result): Shape {
            return when (result) {
                Result.WIN -> Scissors
                Result.LOSE -> Rock
                Result.DRAW -> Paper
            }
        }
    }

    object Scissors : Shape(score = 3) {
        override fun result(opponent: Shape): Result {
            return when (opponent) {
                is Paper -> Result.WIN
                is Scissors -> Result.DRAW
                is Rock -> Result.LOSE
            }
        }

        override fun me(result: Result): Shape {
            return when (result) {
                Result.WIN -> Rock
                Result.LOSE -> Paper
                Result.DRAW -> Scissors
            }
        }
    }
}

enum class Result(val score: Int) {
    WIN(6),
    LOSE(0),
    DRAW(3)
}

