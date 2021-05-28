package de.taskmaster

import de.taskmaster.db.DBConverter
import de.taskmaster.model.data.impl.Status
import org.junit.Test

class ConverterUnitTests {

    @Test
    fun from_status() {
        val converter = DBConverter()
        val status = Status.OPEN
        assert(converter.fromStatus(status) == "OPEN")
    }

    @Test
    fun to_status() {
        val converter = DBConverter()
        val status = Status.OPEN
        assert(converter.fromStatus(status) == "OPEN")
    }


}