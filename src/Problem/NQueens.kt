package Problem

import java.util.*
import java.util.Map
import java.util.Set


var size1 = 0
val solutions: MutableList<List<String>> = ArrayList()
fun solveNQueens(n: Int): List<List<String>> {
    size1 = n
    val emptyBoard = Array(size1) { CharArray(size1) }
    for (i in 0 until n) {
        for (j in 0 until n) {
            emptyBoard[i][j] = '.'
        }
    }
    backtrack(0, HashSet(), HashSet(), HashSet(), emptyBoard)
    return solutions
}

// Making use of a helper function to get the
// solutions in the correct output format
fun createBoard(state: Array<CharArray>): List<String> {
    val board: MutableList<String> = ArrayList()
    for (row in 0 until size1) {
        val current_row = String(state[row])
        board.add(current_row)
    }
    return board
}

fun backtrack(
    row: Int, diagonals: MutableSet<Int>, antiDiagonals: MutableSet<Int>, cols: MutableSet<Int>, state: Array<CharArray>
) {
    // Base case - N queens have been placed
    if (row == size1) {
        solutions.add(createBoard(state))
        return
    }
    for (col in 0 until size1) {
        val currDiagonal = row - col
        val currAntiDiagonal = row + col
        // If the queen is not placeable
        if (cols.contains(col) || diagonals.contains(currDiagonal) || antiDiagonals.contains(currAntiDiagonal)) {
            continue
        }

        // "Add" the queen to the board
        cols.add(col)
        diagonals.add(currDiagonal)
        antiDiagonals.add(currAntiDiagonal)
        state[row][col] = 'Q'

        // Move on to the next row with the updated board state
        backtrack(row + 1, diagonals, antiDiagonals, cols, state)

        // "Remove" the queen from the board since we have already
        // explored all valid paths using the above function call
        cols.remove(col)
        diagonals.remove(currDiagonal)
        antiDiagonals.remove(currAntiDiagonal)
        state[row][col] = '.'
    }
}

var size = 0
fun totalNQueens(n: Int): Int {
    size = n
    return backtrack(0, HashSet(), HashSet(), HashSet())
}

private fun backtrack(
    row: Int, diagonals: MutableSet<Int>, antiDiagonals: MutableSet<Int>, cols: MutableSet<Int>
): Int {
    // Base case - N queens have been placed
    if (row == size) {
        return 1
    }
    var solutions = 0
    for (col in 0 until size) {
        val currDiagonal = row - col
        val currAntiDiagonal = row + col
        // If the queen is not placeable
        if (cols.contains(col) || diagonals.contains(currDiagonal) || antiDiagonals.contains(currAntiDiagonal)) {
            continue
        }

        // "Add" the queen to the board
        cols.add(col)
        diagonals.add(currDiagonal)
        antiDiagonals.add(currAntiDiagonal)

        // Move on to the next row with the updated board state
        solutions += backtrack(row + 1, diagonals, antiDiagonals, cols)

        // "Remove" the queen from the board since we have already
        // explored all valid paths using the above function call
        cols.remove(col)
        diagonals.remove(currDiagonal)
        antiDiagonals.remove(currAntiDiagonal)
    }
    return solutions
}

fun maxSubArray(nums: IntArray): Int {
    // Initialize our variables using the first element.
    var currentSubarray = nums[0]
    var maxSubarray = nums[0]

    // Start with the 2nd element since we already used the first one.
    for (i in 1 until nums.size) {
        val num = nums[i]
        // If current_subarray is negative, throw it away. Otherwise, keep adding to it.
        currentSubarray = Math.max(num, currentSubarray + num)
        maxSubarray = Math.max(maxSubarray, currentSubarray)
    }
    return maxSubarray
}

fun spiralOrder(matrix: Array<IntArray>): List<Int>? {
    val result: MutableList<Int> = ArrayList()
    val rows = matrix.size
    val columns = matrix[0].size
    var up = 0
    var left = 0
    var right = columns - 1
    var down = rows - 1
    while (result.size < rows * columns) {
        // Traverse from left to right.
        for (col in left..right) {
            result.add(matrix[up][col])
        }
        // Traverse downwards.
        for (row in up + 1..down) {
            result.add(matrix[row][right])
        }
        // Make sure we are now on a different row.
        if (up != down) {
            // Traverse from right to left.
            for (col in right - 1 downTo left) {
                result.add(matrix[down][col])
            }
        }
        // Make sure we are now on a different column.
        if (left != right) {
            // Traverse upwards.
            for (row in down - 1 downTo up + 1) {
                result.add(matrix[row][left])
            }
        }
        left++
        right--
        up++
        down--
    }
    return result
}

fun canJump(nums: IntArray): Boolean {
    var lastPos = nums.size - 1
    for (i in nums.indices.reversed()) {
        if (i + nums[i] >= lastPos) {
            lastPos = i
        }
    }
    return lastPos == 0
}

fun merge(intervals: Array<IntArray>): Array<IntArray>? {
    Arrays.sort(
        intervals
    ) { a: IntArray, b: IntArray -> Integer.compare(a[0], b[0]) }
    val merged = LinkedList<IntArray>()
    for (interval in intervals) {
        // if the list of merged intervals is empty or if the current
        // interval does not overlap with the previous, simply append it.
        if (merged.isEmpty() || merged.last[1] < interval[0]) {
            merged.add(interval)
        } else {
            merged.last[1] = Math.max(merged.last[1], interval[1])
        }
    }
    return merged.toTypedArray()
}

fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray>? {
    // init data
    val newStart = newInterval[0]
    val newEnd = newInterval[1]
    var idx = 0
    val n = intervals.size
    val output = LinkedList<IntArray>()

    // add all intervals starting before newInterval
    while (idx < n && newStart > intervals[idx][0]) output.add(intervals[idx++])

    // add newInterval
    var interval = IntArray(2)
    // if there is no overlap, just add the interval
    if (output.isEmpty() || output.last[1] < newStart) output.add(newInterval) else {
        interval = output.removeLast()
        interval[1] = Math.max(interval[1], newEnd)
        output.add(interval)
    }

    // add next intervals, merge with newInterval if needed
    while (idx < n) {
        interval = intervals[idx++]
        val start = interval[0]
        val end = interval[1]
        // if there is no overlap, just add an interval
        if (output.last[1] < start) output.add(interval) else {
            interval = output.removeLast()
            interval[1] = Math.max(interval[1], end)
            output.add(interval)
        }
    }
    return output.toArray(Array(output.size) { IntArray(2) })
}

fun lengthOfLastWord(s: String): Int {
    var s = s
    s = s.trim { it <= ' ' } // trim the trailing spaces in the string
    return s.length - s.lastIndexOf(" ") - 1
}

fun generateMatrix(n: Int): Array<IntArray>? {
    val result = Array(n) { IntArray(n) }
    var cnt = 1
    val dir = arrayOf(intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(0, -1), intArrayOf(-1, 0))
    var d = 0
    var row = 0
    var col = 0
    while (cnt <= n * n) {
        result[row][col] = cnt++
        val r = Math.floorMod(row + dir[d][0], n)
        val c = Math.floorMod(col + dir[d][1], n)

        // change direction if next cell is non zero
        if (result[r][c] != 0) d = (d + 1) % 4
        row += dir[d][0]
        col += dir[d][1]
    }
    return result
}

fun rotateRight(head: ListNode?, k: Int): ListNode? {
    // base cases
    if (head == null) return null
    if (head.next == null) return head

    // close the linked list into the ring
    var old_tail = head
    var n: Int
    n = 1
    while (old_tail!!.next != null) {
        old_tail = old_tail.next
        n++
    }
    old_tail.next = head

    // find new tail : (n - k % n - 1)th node
    // and new head : (n - k % n)th node
    var new_tail = head
    for (i in 0 until n - k % n - 1) new_tail = new_tail!!.next
    val new_head = new_tail!!.next

    // break the ring
    new_tail.next = null
    return new_head
}

fun uniquePaths(m: Int, n: Int): Int {
    val d = Array(m) { IntArray(n) }
    for (arr in d) {
        Arrays.fill(arr, 1)
    }
    for (col in 1 until m) {
        for (row in 1 until n) {
            d[col][row] = d[col - 1][row] + d[col][row - 1]
        }
    }
    return d[m - 1][n - 1]
}

fun uniquePathsWithObstacles(obstacleGrid: Array<IntArray>): Int {
    val R = obstacleGrid.size
    val C = obstacleGrid[0].size

    // If the starting cell has an obstacle, then simply return as there would be
    // no paths to the destination.
    if (obstacleGrid[0][0] == 1) {
        return 0
    }

    // Number of ways of reaching the starting cell = 1.
    obstacleGrid[0][0] = 1

    // Filling the values for the first column
    for (i in 1 until R) {
        obstacleGrid[i][0] = if (obstacleGrid[i][0] == 0 && obstacleGrid[i - 1][0] == 1) 1 else 0
    }

    // Filling the values for the first row
    for (i in 1 until C) {
        obstacleGrid[0][i] = if (obstacleGrid[0][i] == 0 && obstacleGrid[0][i - 1] == 1) 1 else 0
    }

    // Starting from cell(1,1) fill up the values
    // No. of ways of reaching cell[i][j] = cell[i - 1][j] + cell[i][j - 1]
    // i.e. From above and left.
    for (i in 1 until R) {
        for (j in 1 until C) {
            if (obstacleGrid[i][j] == 0) {
                obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1]
            } else {
                obstacleGrid[i][j] = 0
            }
        }
    }

    // Return value stored in rightmost bottommost cell. That is the destination.
    return obstacleGrid[R - 1][C - 1]
}

// This is the DFA we have designed above
private val dfa = listOf<MutableMap<String,Int>>(
    Map.of("digit", 1, "sign", 2, "dot", 3),
    Map.of("digit", 1, "dot", 4, "exponent", 5),
    Map.of("digit", 1, "dot", 3),
    Map.of("digit", 4),
    Map.of("digit", 4, "exponent", 5),
    Map.of("sign", 6, "digit", 7),
    Map.of("digit", 7),
    Map.of("digit", 7)
)

// These are all of the valid finishing states for our DFA.
private val validFinalStates = Set.of(1, 4, 7)

fun isNumber(s: String): Boolean {
    var currentState = 0
    var group = ""
    for (i in 0 until s.length) {
        val curr = s[i]
        group = if (Character.isDigit(curr)) {
            "digit"
        } else if (curr == '+' || curr == '-') {
            "sign"
        } else if (curr == 'e' || curr == 'E') {
            "exponent"
        } else if (curr == '.') {
            "dot"
        } else {
            return false
        }
        if (!dfa[currentState].containsKey(group)) {
            return false
        }
        currentState = dfa[currentState][group]!!
    }
    return validFinalStates.contains(currentState)
}

fun isNumber2(s: String): Boolean {
    var seenDigit = false
    var seenExponent = false
    var seenDot = false
    for (i in 0 until s.length) {
        val curr = s[i]
        if (Character.isDigit(curr)) {
            seenDigit = true
        } else if (curr == '+' || curr == '-') {
            if (i > 0 && s[i - 1] != 'e' && s[i - 1] != 'E') {
                return false
            }
        } else if (curr == 'e' || curr == 'E') {
            if (seenExponent || !seenDigit) {
                return false
            }
            seenExponent = true
            seenDigit = false
        } else if (curr == '.') {
            if (seenDot || seenExponent) {
                return false
            }
            seenDot = true
        } else {
            return false
        }
    }
    return seenDigit
}

fun plusOne(digits: IntArray): IntArray? {
    var digits = digits
    val n = digits.size

    // move along the input array starting from the end
    for (idx in n - 1 downTo 0) {
        // set all the nines at the end of array to zeros
        if (digits[idx] == 9) {
            digits[idx] = 0
        } else {
            // increase this rightmost not-nine by 1
            digits[idx]++
            // and the job is done
            return digits
        }
    }
    // we're here because all the digits are nines
    digits = IntArray(n + 1)
    digits[0] = 1
    return digits
}

fun addBinary(a: String, b: String): String? {
    val n = a.length
    val m = b.length
    if (n < m) return addBinary(b, a)
    val L = Math.max(n, m)
    val sb = StringBuilder()
    var carry = 0
    var j = m - 1
    for (i in L - 1 downTo -1 + 1) {
        if (a[i] == '1') ++carry
        if (j > -1 && b[j--] == '1') ++carry
        if (carry % 2 == 1) sb.append('1') else sb.append('0')
        carry /= 2
    }
    if (carry == 1) sb.append('1')
    sb.reverse()
    return sb.toString()
}

fun mySqrt(x: Int): Int {
    if (x < 2) return x
    val left = Math.pow(Math.E, 0.5 * Math.log(x.toDouble())).toInt()
    val right = left + 1
    return if (right.toLong() * right > x) left else right
}

fun simplifyPath(path: String): String? {

    // Initialize a stack
    val stack = Stack<String>()
    val components = path.split("/".toRegex()).toTypedArray()

    // Split the input string on "/" as the delimiter
    // and process each portion one by one
    for (directory in components) {

        // A no-op for a "." or an empty string
        if (directory == "." || directory.isEmpty()) {
            continue
        } else if (directory == "..") {

            // If the current component is a "..", then
            // we pop an entry from the stack if it's non-empty
            if (!stack.isEmpty()) {
                stack.pop()
            }
        } else {

            // Finally, a legitimate directory name, so we add it
            // to our stack
            stack.add(directory)
        }
    }

    // Stich together all the directory names together
    val result = StringBuilder()
    for (dir in stack) {
        result.append("/")
        result.append(dir)
    }
    return if (result.length > 0) result.toString() else "/"
}