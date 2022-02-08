package Problem

import java.util.*


fun combine(n: Int, k: Int): List<List<Int?>>? {
    // init first combination
    val nums = LinkedList<Int?>()
    for (i in 1 until k + 1) nums.add(i)
    nums.add(n + 1)
    val output: MutableList<List<Int?>> = ArrayList()
    var j = 0
    while (j < k) {
        // add current combination
        output.add(LinkedList<Int?>(nums.subList(0, k)))
        // increase first nums[j] by one
        // if nums[j] + 1 != nums[j + 1]
        j = 0
        while (j < k && nums[j + 1] == nums[j]!! + 1) nums[j] = j++ + 1
        nums[j] = nums[j]!! + 1
    }
    return output
}

fun subsets(nums: IntArray): List<List<Int>?>? {
    val output: MutableList<List<Int>?> = arrayListOf<List<Int>?>()
    output.add(ArrayList())
    for (num in nums) {
        val newSubsets: MutableList<List<Int>?> = arrayListOf()
        for (curr in output) {
            newSubsets.add(object : ArrayList<Int>(curr) {
                init {
                    add(num)
                }
            })
        }
        for (curr in newSubsets) {
            output.add(curr)
        }
    }
    return output
}