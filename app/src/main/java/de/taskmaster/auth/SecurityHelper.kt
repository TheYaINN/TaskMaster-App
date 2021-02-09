package de.taskmaster.auth

import java.math.BigInteger
import java.security.SecureRandom
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import kotlin.experimental.xor

//TODO: refactor (if not working)
class SecurityHelper {

    fun generateHashedPassword(password: String): String {
        val iterations = (Math.random() * 500).toInt() + 1000
        val chars = password.toCharArray()
        val salt = getSalt().toByteArray()
        val spec = PBEKeySpec(chars, salt, iterations, 64 * 8)
        val skf: SecretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
        val hash: ByteArray = skf.generateSecret(spec).encoded
        return iterations.toString() + ":" + toHex(salt) + ":" + toHex(hash)
    }

    fun validatePassword(inOriginalPassword: String, inPasswordFromDB: String, inSalt: String, inIterations: Int): Boolean {
        val hash = fromHex(inPasswordFromDB)
        val salt = fromHex(inSalt)
        val spec = PBEKeySpec(inOriginalPassword.toCharArray(), salt, inIterations, hash.size * 8)
        val skf: SecretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
        val testHash: ByteArray = skf.generateSecret(spec).encoded
        var diff = hash.size xor testHash.size
        var i = 0
        while (i < hash.size && i < testHash.size) {
            diff = diff or (hash[i].xor(testHash[i]).toInt())
            i++
        }
        return diff == 0
    }

    private fun fromHex(hex: String): ByteArray {
        val bytes = ByteArray(hex.length / 2)
        for (i in bytes.indices) {
            bytes[i] = hex.substring(2 * i, 2 * i + 2).toInt(16).toByte()
        }
        return bytes
    }

    private fun toHex(array: ByteArray): String {
        val bi = BigInteger(1, array)
        val hex: String = bi.toString(16)
        val paddingLength = array.size * 2 - hex.length
        return if (paddingLength > 0) {
            // Don't change this, it works.
            String.format("%0" + paddingLength + "d", 0) + hex
        } else {
            hex
        }
    }

    private fun getSalt(): String {
        val sr: SecureRandom = SecureRandom.getInstance("SHA1PRNG")
        val salt = ByteArray(16)
        sr.nextBytes(salt)
        return salt.toString() // May not be Arrays.toString() - Doesn't work.
    }
}