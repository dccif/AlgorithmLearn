package Problem

class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val hashmap = HashMap<Int, Int>()
        for ((i, num) in nums.withIndex()) {
            val n = target - num
            if (hashmap.containsKey(n)) {
                return intArrayOf(i, hashmap[n]!!)
            }
            hashmap[num] = i
        }

        return intArrayOf()
    }
}


fun twoSumMinMemory(nums: IntArray, target: Int): IntArray {
    var arr: IntArray = intArrayOf(0, 0)
    for (a in 0 until nums.size) {
        for (b in a + 1 until nums.size) {
            if ((nums[a] + nums[b]) == target) {
                arr[0] = a
                arr[1] = b
            }
        }
    }
    return arr
}


fun twoSumHash(nums: IntArray, target: Int): IntArray {
    val result = IntArray(2)
    val map = mutableMapOf<Int, Int>()
    for (i in 0 until nums.size) {
        if (map.containsKey(nums[i])) {
            result[0] = map[nums[i]]!!
            result[1] = i
            return result
        } else {
            map[target - nums[i]] = i
        }
    }

    return result
}

fun TwoSumBrute(nums: Array<Int>, target: Int): IntArray {
    var index1 = 0
    var index2 = 0
    loop@ for ((i, item) in nums.withIndex()) {
//        if (item > target) continue
        val need = target - item
        for ((i2, item2) in nums.withIndex()) {
            if (item2 == need) {
                index1 = i
                index2 = i2
                if (index1 != index2) {
                    break@loop
                }
            }
        }
    }
    return intArrayOf(index1, index2)
}

fun main() {
    var arraIn = intArrayOf(-1, -2, -3, -4, -5)
    var te = twoSumHash(arraIn, -8)
    println(te.toString())
}