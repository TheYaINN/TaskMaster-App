package de.taskmaster

import de.taskmaster.auth.SecurityHelper
import org.junit.Test

/**
 * Diese Klasse sammelt alle Unit Tests der Helfer-Klassen, bei denen kein Android Context oder Application benötigt werden,
 * da diese aufzubauen ein riesden Overhead haben.
 */
class HelperUnitTests {

    @Test
    fun security() {
        val pass = "SecurePassword"
        val hashed = SecurityHelper.generateHashedPassword(pass)
        val split = hashed.split(":")
        assert(SecurityHelper.validatePassword(pass, split[2], split[1], split[0].toInt()))
    }

}