package Problem


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