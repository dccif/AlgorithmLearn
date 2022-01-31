package Problem


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