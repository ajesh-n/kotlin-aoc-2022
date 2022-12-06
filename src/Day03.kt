fun main() {
    fun Char.toPriority(): Int {
        return if (this.isUpperCase()) {
            code - 38
        } else {
            code - 96
        }
    }

    fun part1(input: List<String>): Int {
        return input.map { it.chunked(it.count() / 2) }.sumOf {
            it.first().first { itemType -> itemType in it.last() }.toPriority()
        }
    }

    fun part2(input: List<String>): Int {
        return input.windowed(3, 3).sumOf { compartment ->
            compartment[0].first { itemType ->
                itemType in compartment[1] && itemType in compartment[2]
            }.toPriority()
        }
    }

    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
