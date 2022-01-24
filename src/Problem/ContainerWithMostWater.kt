package Problem


// Way 1
fun maxArea1(height: IntArray): Int {
    var maxAreaVa = 0
    for (curIndex in height.indices) {

        for (nex in curIndex until height.size) {
            val he = Math.min(height[curIndex], height[nex])
            val curArea = he * (nex - curIndex)
            maxAreaVa = Math.max(maxAreaVa, curArea)
        }
    }
    return maxAreaVa
}

fun maxArea(height: IntArray): Int {
    var maxAreaVa = 0
    var left = 0
    var right = height.size - 1
    while (left < right) {
        val currArea = Math.min(height[left], height[right]) * (right - left)
        maxAreaVa = Math.max(maxAreaVa, currArea)
        if (height[left] < height[right]) {
            left++
        } else {
            right--
        }
    }
    return maxAreaVa
}

private val values = intArrayOf(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)
private val symbols = arrayOf("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I")

fun intToRoman(num: Int): String? {
    var num = num
    val sb = StringBuilder()
    // Loop through each symbol, stopping if num becomes 0.
    var i = 0
    while (i < values.size && num > 0) {

        // Repeat while the current symbol still fits into num.
        while (values[i] <= num) {
            num -= values[i]
            sb.append(symbols[i])
        }
        i++
    }
    return sb.toString()
}

private val thousands = arrayOf("", "M", "MM", "MMM")
private val hundreds = arrayOf("", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM")
private val tens = arrayOf("", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC")
private val ones = arrayOf("", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX")

fun intToRoman1(num: Int): String? {
    return thousands[num / 1000] + hundreds[num % 1000 / 100] + tens[num % 100 / 10] + ones[num % 10]
}

fun main() {
    var height = intArrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7)
    var height1 = intArrayOf(1, 1)
    println(maxArea1(height1))
}