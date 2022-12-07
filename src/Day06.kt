fun main() {
    fun part1(input: List<String>): Int {
        val markerStart = input[0].windowed(4, 1).find {
            it.toCharArray().distinct().count() == 4
        }
        return input[0].indexOf(markerStart!!) + 4
    }

    fun part2(input: List<String>): Int {
        val markerStart = input[0].windowed(14, 1).find {
            it.toCharArray().distinct().count() == 14
        }
        return input[0].indexOf(markerStart!!) + 14
    }

    val testInput = readInput("Day06_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 19)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
