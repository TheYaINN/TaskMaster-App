package de.taskmaster.model.data

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DeadLine {

    private val pattern: String = ""
    var date: LocalDate? = null

    override fun toString(): String {
        return date?.format(DateTimeFormatter.ISO_OFFSET_DATE) ?: ""
    }
}
