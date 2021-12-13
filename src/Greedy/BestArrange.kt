package Greedy

class Program(val start: Int, val end: Int)

class ProgramComparator : Comparator<Program> {
    override fun compare(o1: Program?, o2: Program?): Int {
        return o1!!.end - o2!!.end
    }
}

fun bestArrange(programs: Array<Program>, timePoint: Int): Int {
    var timePoint = timePoint
    programs.sortWith(ProgramComparator())
    var result = 0
    for (i in programs.indices) {
        if (timePoint <= programs[i].start) {
            result++
            timePoint = programs[i].end
        }
    }
    return result
}