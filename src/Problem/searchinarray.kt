package Problem

import java.util.*


class searchinarray {
    lateinit var nums: IntArray
    var target = 0

    fun find_rotate_index(left: Int, right: Int): Int {
        var left = left
        var right = right
        if (nums[left] < nums[right]) return 0
        while (left <= right) {
            val pivot = (left + right) / 2
            if (nums[pivot] > nums[pivot + 1]) return pivot + 1 else {
                if (nums[pivot] < nums[left]) right = pivot - 1 else left = pivot + 1
            }
        }
        return 0
    }

    fun search(left: Int, right: Int): Int {
        /*
        Binary search
        */
        var left = left
        var right = right
        while (left <= right) {
            val pivot = (left + right) / 2
            if (nums[pivot] == target) return pivot else {
                if (target < nums[pivot]) right = pivot - 1 else left = pivot + 1
            }
        }
        return -1
    }

    fun search(nums: IntArray, target: Int): Int {
        this.nums = nums
        this.target = target
        val n = nums.size
        if (n == 1) return if (this.nums.get(0) == target) 0 else -1
        val rotate_index = find_rotate_index(0, n - 1)

        // if target is the smallest element
        if (nums[rotate_index] == target) return rotate_index
        // if array is not rotated, search in the entire array
        if (rotate_index == 0) return search(0, n - 1)
        return if (target < nums[0]) search(rotate_index, n - 1) else search(0, rotate_index)
        // search in the left side
    }

    internal class Solution {
        // box size
        var n1 = 3

        // row size
        var N = n1 * n1
        var rows = Array(N) { IntArray(N + 1) }
        var columns = Array(N) { IntArray(N + 1) }
        var boxes = Array(N) { IntArray(N + 1) }
        lateinit var board: Array<CharArray>
        var sudokuSolved = false
        fun couldPlace(d: Int, row: Int, col: Int): Boolean {
            /*
        Check if one could place a number d in (row, col) cell
        */
            val idx = row / n1 * n1 + col / n1
            return rows[row][d] + columns[col][d] + boxes[idx][d] == 0
        }

        fun placeNumber(d: Int, row: Int, col: Int) {
            /*
        Place a number d in (row, col) cell
        */
            val idx = row / n1 * n1 + col / n1
            rows[row][d]++
            columns[col][d]++
            boxes[idx][d]++
            board[row][col] = (d + '0'.code).toChar()
        }

        fun removeNumber(d: Int, row: Int, col: Int) {
            /*
        Remove a number which didn't lead to a solution
        */
            val idx = row / n1 * n1 + col / n1
            rows[row][d]--
            columns[col][d]--
            boxes[idx][d]--
            board[row][col] = '.'
        }

        fun placeNextNumbers(row: Int, col: Int) {
            /*
        Call backtrack function in recursion
        to continue to place numbers
        till the moment we have a solution
        */
            // if we're in the last cell
            // that means we have the solution
            if (col == N - 1 && row == N - 1) {
                sudokuSolved = true
            } else {
                // if we're in the end of the row
                // go to the next row
                if (col == N - 1) backtrack(row + 1, 0) else backtrack(row, col + 1)
            }
        }

        fun backtrack(row: Int, col: Int) {
            /*
        Backtracking
        */
            // if the cell is empty
            if (board[row][col] == '.') {
                // iterate over all numbers from 1 to 9
                for (d in 1..9) {
                    if (couldPlace(d, row, col)) {
                        placeNumber(d, row, col)
                        placeNextNumbers(row, col)
                        // if sudoku is solved, there is no need to backtrack
                        // since the single unique solution is promised
                        if (!sudokuSolved) removeNumber(d, row, col)
                    }
                }
            } else placeNextNumbers(row, col)
        }

        fun solveSudoku(board: Array<CharArray>) {
            this.board = board

            // init rows, columns and boxes
            for (i in 0 until N) {
                for (j in 0 until N) {
                    val num = board[i][j]
                    if (num != '.') {
                        val d = Character.getNumericValue(num)
                        placeNumber(d, i, j)
                    }
                }
            }
            backtrack(0, 0)
        }
    }


}

fun search(nums: IntArray, target: Int): Int {
    var start = 0
    var end = nums.size - 1
    while (start <= end) {
        val mid = start + (end - start) / 2
        if (nums[mid] == target) return mid else if (nums[mid] >= nums[start]) {
            if (target >= nums[start] && target < nums[mid]) end = mid - 1 else start = mid + 1
        } else {
            if (target <= nums[end] && target > nums[mid]) start = mid + 1 else end = mid - 1
        }
    }
    return -1
}

fun searchRange(nums: IntArray, target: Int): IntArray? {
    val firstOccurrence: Int = findBound(nums, target, true)
    if (firstOccurrence == -1) {
        return intArrayOf(-1, -1)
    }
    val lastOccurrence: Int = findBound(nums, target, false)
    return intArrayOf(firstOccurrence, lastOccurrence)
}

private fun findBound(nums: IntArray, target: Int, isFirst: Boolean): Int {
    val N = nums.size
    var begin = 0
    var end = N - 1
    while (begin <= end) {
        val mid = (begin + end) / 2
        if (nums[mid] == target) {
            if (isFirst) {

                // This means we found our lower bound.
                if (mid == begin || nums[mid - 1] != target) {
                    return mid
                }

                // Search on the left side for the bound.
                end = mid - 1
            } else {

                // This means we found our upper bound.
                if (mid == end || nums[mid + 1] != target) {
                    return mid
                }

                // Search on the right side for the bound.
                begin = mid + 1
            }
        } else if (nums[mid] > target) {
            end = mid - 1
        } else {
            begin = mid + 1
        }
    }
    return -1
}

fun searchInsert(nums: IntArray, target: Int): Int {
    var pivot: Int
    var left = 0
    var right = nums.size - 1
    while (left <= right) {
        pivot = left + (right - left) / 2
        if (nums[pivot] == target) return pivot
        if (target < nums[pivot]) right = pivot - 1 else left = pivot + 1
    }
    return left
}

fun isValidSudoku1(board: Array<CharArray>): Boolean {
    val N = 9

    // Use hash set to record the status
    val rows: Array<HashSet<Char>> = Array(N) { HashSet<Char>(N) }
    val cols: Array<HashSet<Char>> = Array(N) { HashSet<Char>(N) }
    val boxes: Array<HashSet<Char>> = Array(N) { HashSet<Char>(N) }
//    for (r in 0 until N) {
//        rows[r] = HashSet()
//        cols[r] = HashSet()
//        boxes[r] = HashSet()
//    }
    for (r in 0 until N) {
        for (c in 0 until N) {
            val `val` = board[r][c]

            // Check if the position is filled with number
            if (`val` == '.') {
                continue
            }

            // Check the row
            if (rows[r].contains(`val`)) {
                return false
            }
            rows[r].add(`val`)

            // Check the column
            if (cols[c].contains(`val`)) {
                return false
            }
            cols[c].add(`val`)

            // Check the box
            val idx = r / 3 * 3 + c / 3
            if (boxes[idx].contains(`val`)) {
                return false
            }
            boxes[idx].add(`val`)
        }
    }
    return true
}

fun isValidSudoku2(board: Array<CharArray>): Boolean {
    val N = 9

    // Use an array to record the status
    val rows = Array(N) { IntArray(N) }
    val cols = Array(N) { IntArray(N) }
    val boxes = Array(N) { IntArray(N) }
    for (r in 0 until N) {
        for (c in 0 until N) {
            // Check if the position is filled with number
            if (board[r][c] == '.') {
                continue
            }
            val pos = board[r][c] - '1'

            // Check the row
            if (rows[r][pos] == 1) {
                return false
            }
            rows[r][pos] = 1

            // Check the column
            if (cols[c][pos] == 1) {
                return false
            }
            cols[c][pos] = 1

            // Check the box
            val idx = r / 3 * 3 + c / 3
            if (boxes[idx][pos] == 1) {
                return false
            }
            boxes[idx][pos] = 1
        }
    }
    return true
}

fun isValidSudoku3(board: Array<CharArray>): Boolean {
    val N = 9

    // Use a binary number to record previous occurrence
    val rows = IntArray(N)
    val cols = IntArray(N)
    val boxes = IntArray(N)
    for (r in 0 until N) {
        for (c in 0 until N) {
            // Check if the position is filled with number
            if (board[r][c] == '.') {
                continue
            }
            val `val` = board[r][c] - '0'
            val pos = 1 shl `val` - 1

            // Check the row
            if (rows[r] and pos > 0) {
                return false
            }
            rows[r] = rows[r] or pos

            // Check the column
            if (cols[c] and pos > 0) {
                return false
            }
            cols[c] = cols[c] or pos

            // Check the box
            val idx = r / 3 * 3 + c / 3
            if (boxes[idx] and pos > 0) {
                return false
            }
            boxes[idx] = boxes[idx] or pos
        }
    }
    return true
}

fun countAndSay(n: Int): String? {
    if (n == 1) return "1"
    if (n == 2) return "11"
    val sb = StringBuilder("11")
    var c = 1
    for (i in 3..n) {
        val t = StringBuilder()
        sb.append("$")
        for (j in 1 until sb.length) {
            if (sb[j - 1] == sb[j]) {
                c++
            } else {
                t.append(c)
                t.append(sb[j - 1])
                c = 1
            }
        }
        sb.replace(0, sb.length, t.toString())
    }
    return sb.toString()
}

fun backtrack(
    remain: Int, comb: LinkedList<Int>, start: Int, candidates: IntArray, results: MutableList<List<Int>>
) {
    if (remain == 0) {
        // make a deep copy of the current combination
        results.add(ArrayList(comb))
        return
    } else if (remain < 0) {
        // exceed the scope, stop exploration.
        return
    }
    for (i in start until candidates.size) {
        // add the number into the combination
        comb.add(candidates[i])
        backtrack(remain - candidates[i], comb, i, candidates, results)
        // backtrack, remove the number from the combination
        comb.removeLast()
    }
}

fun combinationSum1(candidates: IntArray, target: Int): List<List<Int>>? {
    val results: MutableList<List<Int>> = ArrayList()
    val comb = LinkedList<Int>()
    backtrack(target, comb, 0, candidates, results)
    return results
}

var ml: MutableList<List<Int>>? = null
fun combinationSum(candidates: IntArray, target: Int): List<List<Int>>? {
    //main list to store combinations.
    ml = ArrayList()
    //Starting index is 0, then we will make two calls, one call will be when we pick that element,
    //other will be when we don't pick the element.
    findCombinations(candidates, 0, target, ArrayList())
    return ml
}

fun findCombinations1(arr: IntArray, idx: Int, target: Int, cl: MutableList<Int>) {
    //If target becomes 0, then we will add the combination in the main list and return.
    //If index becomes equal to the length of the array, and target is not equal to 0, then simply return.
    if (target == 0 || idx == arr.size) {
        if (target == 0) {
            ml!!.add(ArrayList(cl))
        }
        return
    }

    //First Call : We pick the element. But we can only pick the element if it is less than the target.
    if (arr[idx] <= target) {
        //Add the element in the child list.
        cl.add(arr[idx])
        //After adding the element in child list we pick the element, so we have to decrease target by it's value.
        findCombinations1(arr, idx, target - arr[idx], cl)
        //Backtracking : Remove the recently added element.
        cl.removeAt(cl.size - 1)
    }
    //Second Call : We don't pick the element and move on to the next one (idx + 1).
    findCombinations1(arr, idx + 1, target, cl)
}

var ml1: MutableList<List<Int>>? = null
fun combinationSum2(candidates: IntArray, target: Int): List<List<Int?>?>? {
    //main list to store combinations.
    ml1 = ArrayList<List<Int>>()
    //Sort the array as we want combinations in lexographical order.
    Arrays.sort(candidates)
    findCombinations(candidates, 0, target, ArrayList<Int>())
    return ml1
}

fun findCombinations(arr: IntArray, idx: Int, target: Int, cl: MutableList<Int>) {
    //If target becomes 0, then we will add the combination in the main list and return.
    if (target == 0) {
        ml1!!.add(ArrayList(cl))
        return
    }

    //Looping from idx till n - 1.
    for (i in idx until arr.size) {
        //If a duplicate is found then continue.
        if (i > idx && arr[i] == arr[i - 1]) {
            continue
        }
        //If the element that we want to pick up becomes greater than target, then break from the
        //loop as we cannot pick it. Also the elements to it's right will be greater than or equal
        //to it as the array is sorted.
        if (arr[i] > target) {
            break
        }

        //Add the element in the child list.
        cl.add(arr[i])
        //After adding the element in child list, we have to decrease target by it's value and then we will
        //pick the next element (i + 1).
        findCombinations(arr, i + 1, target - arr[i], cl)
        //Backtracking : Remove the recently added element.
        cl.removeAt(cl.size - 1)
    }
}

fun firstMissingPositive(nums: IntArray): Int {
    val hs = HashSet<Int>()
    var max = Int.MIN_VALUE
    for (i in nums.indices) {
        if (nums[i] > max) {
            max = nums[i]
        }
        hs.add(nums[i])
    }
    var i: Int
    i = 1
    while (i <= max) {
        if (!hs.contains(i)) {
            return i
        }
        i++
    }
    return i
}

fun firstMissingPositive1(nums: IntArray): Int {
    val n = nums.size

    // Base case.
    var contains = 0
    for (i in 0 until n) if (nums[i] == 1) {
        contains++
        break
    }
    if (contains == 0) return 1

    // Replace negative numbers, zeros,
    // and numbers larger than n by 1s.
    // After this convertion nums will contain
    // only positive numbers.
    for (i in 0 until n) if (nums[i] <= 0 || nums[i] > n) nums[i] = 1

    // Use index as a hash key and number sign as a presence detector.
    // For example, if nums[1] is negative that means that number `1`
    // is present in the array.
    // If nums[2] is positive - number 2 is missing.
    for (i in 0 until n) {
        val a = Math.abs(nums[i])
        // If you meet number a in the array - change the sign of a-th element.
        // Be careful with duplicates : do it only once.
        if (a == n) nums[0] = -Math.abs(nums[0]) else nums[a] = -Math.abs(nums[a])
    }

    // Now the index of the first positive number
    // is equal to first missing positive.
    for (i in 1 until n) {
        if (nums[i] > 0) return i
    }
    return if (nums[0] > 0) n else n + 1
}

fun trap(height: IntArray): Int {
    var result = 0
    var start = 0
    var end = height.size - 1
    while (start < end) {
        if (height[start] <= height[end]) {
            val current = height[start]
            while (height[++start] < current) {
                result += current - height[start]
            }
        } else {
            val current = height[end]
            while (height[--end] < current) {
                result += current - height[end]
            }
        }
    }
    return result
}

fun multiply(num1: String, num2: String): String? {
    if (num1 == "0" || num2 == "0") {
        return "0"
    }
    val firstNumber = StringBuilder(num1)
    val secondNumber = StringBuilder(num2)

    // Reverse both the numbers.
    firstNumber.reverse()
    secondNumber.reverse()

    // To store the multiplication result of each digit of secondNumber with firstNumber.
    val N = firstNumber.length + secondNumber.length
    val answer = StringBuilder()
    for (i in 0 until N) {
        answer.append(0)
    }
    for (place2 in 0 until secondNumber.length) {
        val digit2 = secondNumber[place2] - '0'

        // For each digit in secondNumber multiply the digit by all digits in firstNumber.
        for (place1 in 0 until firstNumber.length) {
            val digit1 = firstNumber[place1] - '0'

            // The number of zeros from multiplying to digits depends on the
            // place of digit2 in secondNumber and the place of the digit1 in firstNumber.
            val currentPos = place1 + place2

            // The digit currently at position currentPos in the answer string
            // is carried over and summed with the current result.
            val carry = answer[currentPos] - '0'
            val multiplication = digit1 * digit2 + carry

            // Set the ones place of the multiplication result.
            answer.setCharAt(currentPos, (multiplication % 10 + '0'.code).toChar())

            // Carry the tens place of the multiplication result by
            // adding it to the next position in the answer array.
            val value = answer[currentPos + 1] - '0' + multiplication / 10
            answer.setCharAt(currentPos + 1, (value + '0'.code).toChar())
        }
    }

    // Pop excess 0 from the rear of answer.
    if (answer[answer.length - 1] == '0') {
        answer.deleteCharAt(answer.length - 1)
    }
    answer.reverse()
    return answer.toString()
}

fun jump(nums: IntArray): Int {
    var jumps = 0
    var currentJumpEnd = 0
    var farthest = 0
    for (i in 0 until nums.size - 1) {
        // we continuously find the how far we can reach in the current jump
        farthest = Math.max(farthest, i + nums[i])
        // if we have come to the end of the current jump,
        // we need to make another jump
        if (i == currentJumpEnd) {
            jumps++
            currentJumpEnd = farthest
        }
    }
    return jumps
}

fun main() {
//    println(combinationSum(intArrayOf(3, 4, 5), 8))
    println((jump(intArrayOf(2,3,1,1,4))))
}