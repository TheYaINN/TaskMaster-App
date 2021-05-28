package de.taskmaster

import de.taskmaster.model.data.impl.Address
import de.taskmaster.model.handler.AddressValidator
import org.junit.Test

class ValidatorUnitTest {

    @Test
    fun invalid() {
        val invalid = Address()
        assert(AddressValidator.validate(invalid))
    }

    @Test
    fun valid() {
        val valid = Address().apply {
            city = "Overath"
            street = "Birkenhang"
            number = 27
            name = "Zuhause"
        }
        assert(AddressValidator.validate(valid))
    }
}