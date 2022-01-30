package Problem

import java.util.*


fun backtrack1(
    n: Int, nums: ArrayList<Int?>?, output: MutableList<List<Int?>?>, first: Int
) {
    // if all integers are used up
    if (first == n) output.add(ArrayList(nums))
    for (i in first until n) {
        // place i-th integer first
        // in the current permutation
        Collections.swap(nums, first, i)
        // use next integers to complete the permutations
        backtrack1(n, nums, output, first + 1)
        // backtrack
        Collections.swap(nums, first, i)
    }
}

fun permute(nums: IntArray): List<List<Int?>?>? {
    // init output list
    val output: MutableList<List<Int?>?> = LinkedList<List<Int?>?>()

    // convert nums into list since the output is a list of lists
    val nums_lst = ArrayList<Int?>()
    for (num in nums) nums_lst.add(num)
    val n = nums.size
    backtrack1(n, nums_lst, output, 0)
    return output
}


fun permuteUnique(nums: IntArray): List<List<Int>?>? {
    val results: MutableList<List<Int>?> = ArrayList()

    // count the occurrence of each number
    val counter = HashMap<Int, Int>()
    for (num in nums) {
        if (!counter.containsKey(num)) counter[num] = 0
        counter[num] = counter[num]!! + 1
    }
    val comb = LinkedList<Int>()
    backtrack(comb, nums.size, counter, results)
    return results
}

fun backtrack(
    comb: LinkedList<Int>, N: Int, counter: HashMap<Int, Int>, results: MutableList<List<Int>?>
) {
    if (comb.size == N) {
        // make a deep copy of the resulting permutation,
        // since the permutation would be backtracked later.
        results.add(ArrayList(comb))
        return
    }
    for ((num, count) in counter) {
        if (count == 0) continue
        // add this number into the current combination
        comb.addLast(num)
        counter[num] = count - 1

        // continue the exploration
        backtrack(comb, N, counter, results)

        // revert the choice for the next exploration
        comb.removeLast()
        counter[num] = count
    }
}

fun rotate1(matrix: Array<IntArray>) {
    val n = matrix.size
    for (i in 0 until (n + 1) / 2) {
        for (j in 0 until n / 2) {
            val temp = matrix[n - 1 - j][i]
            matrix[n - 1 - j][i] = matrix[n - 1 - i][n - j - 1]
            matrix[n - 1 - i][n - j - 1] = matrix[j][n - 1 - i]
            matrix[j][n - 1 - i] = matrix[i][j]
            matrix[i][j] = temp
        }
    }
}

fun rotate(matrix: Array<IntArray>) {
    transpose(matrix)
    reflect(matrix)
}

fun transpose(matrix: Array<IntArray>) {
    val n = matrix.size
    for (i in 0 until n) {
        for (j in i + 1 until n) {
            val tmp = matrix[j][i]
            matrix[j][i] = matrix[i][j]
            matrix[i][j] = tmp
        }
    }
}

fun reflect(matrix: Array<IntArray>) {
    val n = matrix.size
    for (i in 0 until n) {
        for (j in 0 until n / 2) {
            val tmp = matrix[i][j]
            matrix[i][j] = matrix[i][n - j - 1]
            matrix[i][n - j - 1] = tmp
        }
    }
}

fun fastPow(x: Double, n: Long): Double {
    if (n == 0L) {
        return 1.0
    }
    val half = fastPow(x, n / 2)
    return if (n % 2 == 0L) {
        half * half
    } else {
        half * half * x
    }
}

fun myPow1(x: Double, n: Int): Double {
    var x = x
    var N = n.toLong()
    if (N < 0) {
        x = 1 / x
        N = -N
    }
    return fastPow(x, N)
}

fun myPow(x: Double, n: Int): Double {
    var x = x
    var N = n.toLong()
    if (N < 0) {
        x = 1 / x
        N = -N
    }
    var ans = 1.0
    var current_product = x
    var i = N
    while (i > 0) {
        if (i % 2 == 1L) {
            ans = ans * current_product
        }
        current_product = current_product * current_product
        i /= 2
    }
    return ans
}