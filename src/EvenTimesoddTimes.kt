fun printOddTimesNum1(arr: Array<Int>) {
    var eor = 0
    for (cur in arr) {
        eor = eor xor cur
    }
    println(eor)
}

fun printOddTimesNum2(arr: Array<Int>) {
    var eor = 0
    var onlyOne = 0
    for (curNum in arr) {
        eor = eor xor curNum
    }
    // eor = a xor b
    // eor != 0
    // eor must have a bit is 1
    val rightOne = eor and (eor.inv() + 1)
    for (cur in arr) {
        if ((cur and rightOne) == 0) {
            onlyOne = onlyOne xor cur
        }
    }
    println("onlyOne ${eor xor onlyOne}")
}