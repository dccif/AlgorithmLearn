package Greedy

fun process(str: CharArray, i: Int): Int {
    if (i == str.size) return 1
    if (str[i] == '0') return 0
    if (str[i] == '1') {
        var res = process(str, i + 1)
        if (i + 1 < str.size) {
            res += process(str, i + 2)
        }
        return res
    }
    if (str[i] == '2') {
        var res = process(str, i + 1)
        if (i + 1 < str.size && (str[i + 1] >= '0' && str[i + 1] <= '6')) {
            res += process(str, i + 2)
        }
        return res
    }
    return process(str, i + 1)
}