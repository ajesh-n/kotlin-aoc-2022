fun main() {
    fun stakes(stakesString: String): MutableList<MutableList<String>> {
        val stakes = mutableListOf<MutableList<String>>()
        val crates = stakesString.lines().map {
            it.windowed(3, 4).map { line -> line.filter { char -> char.isLetter() } }
        }.dropLast(1).reversed()
        for (line in crates) {
            for ((j, char) in line.withIndex()) {
                if (char.isNotBlank()) {
                    stakes.getOrNull(j)?.add(char) ?: run {
                        stakes.add(j, mutableListOf())
                        stakes[j].add(char)
                    }
                }
            }
        }
        return stakes
    }

    fun part1(input: String): String {
        val (stakesString, instructions) = input.split("\n\n")
        val stakes = stakes(stakesString)
        instructions.split("\n").forEach {
            val stakesToMove = it.split("from")[0].split(" ")[1].toInt()
            val (from, to) = it.split("from")[1].split("to").map { n -> n.trim().toInt() }
            repeat(stakesToMove) {
                stakes[to - 1].add(stakes[from - 1].last())
                stakes[from - 1].removeLast()
            }
        }
        return stakes.joinToString("") { it.last() }
    }

    fun part2(input: String): String {
        val (stakesString, instructions) = input.split("\n\n")
        val stakes = stakes(stakesString)
        instructions.split("\n").forEach {
            val stakesToMove = it.split("from")[0].split(" ")[1].toInt()
            val (from, to) = it.split("from")[1].split("to").map { n -> n.trim().toInt() }
            stakes[from - 1].takeLast(stakesToMove).let { toMove ->
                stakes[to - 1].addAll(toMove)
            }
            repeat(stakesToMove) {
                stakes[from - 1].removeLast()
            }
        }
        return stakes.joinToString("") { it.last() }
    }

    val testInput = readText("Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readText("Day05")
    println(part1(input))
    println(part2(input))
}
