package Problem

fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
    var l = 0
    var r = 0
    val allLong = nums1.size + nums2.size
    var toStop = (allLong / 2) + 1
    val newArray = IntArray(allLong)
    var idx = 0
    while (l < nums1.size && r < nums2.size && toStop > 0) {
        if (nums1[l] <= nums2[r]) {
            newArray[idx++] = nums1[l++]
        } else {
            newArray[idx++] = nums2[r++]
        }
        toStop--
    }
    while (toStop > 0 && l < nums1.size) {
        newArray[idx++] = nums1[l++]
        toStop--
    }
    while (toStop > 0 && r < nums2.size) {
        newArray[idx++] = nums2[r++]
        toStop--
    }
    return if (allLong % 2 != 0) {
        newArray[allLong / 2].toDouble()
    } else {
        (newArray[allLong / 2] + newArray[allLong / 2 - 1]) / 2.0
    }
}

fun main() {
    val num1 = intArrayOf(0, 0, 0, 0, 0)
    val num2 = intArrayOf(-1, 0, 0, 0, 0, 0, 1)
    println(findMedianSortedArrays(num1, num2))
}