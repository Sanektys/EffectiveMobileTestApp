package com.example.effectivemobiletestapp.ui.utils


fun arrangeDigits(price: Int): String {
    val original = price.toString()
    val arranged = StringBuilder(original)

    for (i in original.indices) {
        if (i % 3 == 0) {
            arranged.insert(original.length - i, ' ')
        }
    }
    return arranged.toString()
}