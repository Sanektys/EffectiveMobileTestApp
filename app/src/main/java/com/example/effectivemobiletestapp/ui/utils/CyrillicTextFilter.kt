package com.example.effectivemobiletestapp.ui.utils

import android.text.InputFilter
import android.text.Spanned


object CyrillicTextFilter : InputFilter {
    override fun filter(
        source: CharSequence?, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int
    ): CharSequence? {
        val previousText = dest?.subSequence(dstart, dend) ?: ""

        if (source == null) return previousText
        if (source.length == 1) {
            return if (cyrillicAlphabet.contains(source[0])) {
                source
            } else {
                previousText
            }
        } else {
            source.forEachIndexed { index, symbol ->
                if (!cyrillicAlphabet.contains(symbol)) {
                    return source.subSequence(start, index)
                }
            }
            return null  // Принять новое значение с буфера (source)
        }
    }
}


val cyrillicAlphabet = listOf(
    'А', 'а', 'Б', 'б', 'В', 'в', 'Г', 'г', 'Д', 'д', 'Е', 'е', 'Ё', 'ё', 'Ж', 'ж', 'З', 'з', 'И',
    'и', 'Й', 'й', 'К', 'к', 'Л', 'л', 'М', 'м', 'Н', 'н', 'О', 'о', 'П', 'п', 'Р', 'р', 'С', 'с',
    'Т', 'т', 'У', 'у', 'Ф', 'ф', 'Х', 'х', 'Ц', 'ц', 'Ч', 'ч', 'Ш', 'ш', 'Щ', 'щ', 'Ъ', 'ъ', 'Ы',
    'ы', 'Ь', 'ь', 'Э', 'э', 'Ю', 'ю', 'Я', 'я', ' ', '-')