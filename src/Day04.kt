fun main() {
    fun part1(input: List<String>): Int {
        val sections: List<List<List<Int>>> = input.map { sectionPairs ->
            sectionPairs.split(",").map { it.split("-").map { section -> section.toInt() } }
                .map { (it.first()..it.last()).toList() }
        }
        return sections.count {
            it.first().containsAll(it.last()) || it.last().containsAll(it.first())
        }
    }

    fun part2(input: List<String>): Int {
        val sections: List<List<List<Int>>> = input.map { sectionPairs ->
            sectionPairs.split(",").map { it.split("-").map { section -> section.toInt() } }
                .map { (it.first()..it.last()).toList() }
        }
        return sections.count {
            it.first().intersect(it.last().toSet()).isNotEmpty()
        }
    }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
