package com.example.effectivemobiletestapp.ui.utils


fun arrangeDigits(price: Int): String {
    val original = price.toString()
    val arranged = StringBuilder(original)

    for (i in original.indices) {
        if (i % 3 == 0 && i + 1 < original.length) {
            arranged.insert(i + 1, ' ')
        }
    }
    return arranged.toString()
}