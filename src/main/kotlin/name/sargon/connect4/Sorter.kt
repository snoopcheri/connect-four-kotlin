package name.sargon.connect4

// http://pages.ripco.net/~jgamble/nw.html
fun sort(numbers: IntArray) {

    when (numbers.size) {
        2 -> sort2(numbers)
        3 -> sort3(numbers)
        4 -> sort4(numbers)
        5 -> sort5(numbers)
        6 -> sort6(numbers)
        7 -> sort7(numbers)
    }
}

fun sort2(numbers: IntArray) {
    swap(numbers, 0, 1)
}

fun sort3(numbers: IntArray) {
    swap(numbers, 1, 2)
    swap(numbers, 0, 2)
    swap(numbers, 0, 1)
}

fun sort4(numbers: IntArray) {
    swap(numbers, 0, 1)
    swap(numbers, 2, 3)
    swap(numbers, 0, 2)
    swap(numbers, 1, 3)
    swap(numbers, 1, 2)
}

fun sort5(numbers: IntArray) {
    swap(numbers, 0, 1)
    swap(numbers, 3, 4)
    swap(numbers, 2, 4)
    swap(numbers, 2, 3)
    swap(numbers, 1, 4)
    swap(numbers, 0, 3)
    swap(numbers, 0, 2)
    swap(numbers, 1, 3)
    swap(numbers, 1, 2)
}

fun sort6(numbers: IntArray) {
    swap(numbers, 1, 2)
    swap(numbers, 4, 5)
    swap(numbers, 0, 2)
    swap(numbers, 3, 5)
    swap(numbers, 0, 1)
    swap(numbers, 3, 4)
    swap(numbers, 2, 5)
    swap(numbers, 0, 3)
    swap(numbers, 1, 4)
    swap(numbers, 2, 4)
    swap(numbers, 1, 3)
    swap(numbers, 2, 3)

}

fun sort7(numbers: IntArray) {
    swap(numbers, 1, 2)
    swap(numbers, 3, 4)
    swap(numbers, 5, 6)
    swap(numbers, 0, 2)
    swap(numbers, 3, 5)
    swap(numbers, 4, 6)
    swap(numbers, 0, 1)
    swap(numbers, 4, 5)
    swap(numbers, 2, 6)
    swap(numbers, 0, 4)
    swap(numbers, 1, 5)
    swap(numbers, 0, 3)
    swap(numbers, 2, 5)
    swap(numbers, 1, 3)
    swap(numbers, 2, 4)
    swap(numbers, 2, 3)

}

fun swap(numbers: IntArray, x: Int, y: Int) {
    if (weight[numbers[y]] > weight[numbers[x]]) {
        val t = numbers[y]
        numbers[y] = numbers[x]
        numbers[x] = t
    }
}


val weight: IntArray = intArrayOf(
    3, 4, 5, 5, 4, 3, 0, 0,
    4, 6, 8, 8, 6, 4, 0, 0,
    5, 8, 11, 11, 8, 5, 0, 0,
    7, 10, 13, 13, 10, 7, 0, 0,
    5, 8, 11, 11, 8, 5, 0, 0,
    4, 6, 8, 8, 6, 4, 0, 0,
    3, 4, 5, 5, 4, 3, 0, 0
)
