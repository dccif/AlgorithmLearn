package Problem

import java.util.*


fun longestCommonPrefix1(strs: Array<String>): String? {
    if (strs.size == 0) return ""
    var prefix = strs[0]
    for (i in 1 until strs.size) while (strs[i].indexOf(prefix) != 0) {
        prefix = prefix.substring(0, prefix.length - 1)
        if (prefix.isEmpty()) return ""
    }
    return prefix
}

fun longestCommonPrefix(strs: Array<String>?): String? {
    if (strs == null || strs.size == 0) return ""
    for (i in 0 until strs[0].length) {
        val c = strs[0][i]
        for (j in 1 until strs.size) {
            if (i == strs[j].length || strs[j][i] != c) return strs[0].substring(0, i)
        }
    }
    return strs[0]
}

fun threeSum(nums: IntArray): List<List<Int>> {
    val res: MutableSet<List<Int>?> = HashSet()
    val dups: MutableSet<Int> = HashSet()
    val seen: MutableMap<Int, Int> = HashMap()
    for (i in nums.indices) if (dups.add(nums[i])) {
        for (j in i + 1 until nums.size) {
            val complement = -nums[i] - nums[j]
            if (seen.containsKey(complement) && seen[complement] == i) {
                val triplet = Arrays.asList(nums[i], nums[j], complement)
                Collections.sort(triplet)
                res.add(triplet)
            }
            seen[nums[j]] = i
        }
    }
    return ArrayList<List<Int>>(res)
}

fun threeSumClosest(nums: IntArray, target: Int): Int {
    var diff = Int.MAX_VALUE
    val sz = nums.size
    Arrays.sort(nums)
    var i = 0
    while (i < sz && diff != 0) {
        var lo = i + 1
        var hi = sz - 1
        while (lo < hi) {
            val sum = nums[i] + nums[lo] + nums[hi]
            if (Math.abs(target - sum) < Math.abs(diff)) {
                diff = target - sum
            }
            if (sum < target) {
                ++lo
            } else {
                --hi
            }
        }
        ++i
    }
    return target - diff
}