package de.taskmaster.model.data

import androidx.databinding.InverseMethod

object Converter {

    @InverseMethod("stringToRepeat")
    @JvmStatic
    fun repeatToString(value: Repeat?): String? {
        return value?.text
    }

    @JvmStatic
    fun stringToRepeat(value: String): Repeat {
        return Repeat.values().first { it.text == value }
    }
}