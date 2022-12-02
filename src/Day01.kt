fun main() {
    fun part1(input: String): Int {
        val elvesTotalFood =
            input.split("\n\n").map { it.split("\n").sumOf { calories -> calories.toInt() } }
        return elvesTotalFood.max()
    }

    fun part2(input: String): Int {
        return input.split("\n\n").map { it.split("\n").sumOf { calories -> calories.toInt() } }
            .sortedDescending().take(3).sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readText("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readText("Day01")
    println(part1(input))
    println(part2(input))
}
