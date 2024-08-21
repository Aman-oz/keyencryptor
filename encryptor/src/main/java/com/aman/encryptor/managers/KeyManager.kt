package com.aman.encryptor.managers

import com.aman.encryptor.exceptions.EncryptionException
import java.nio.charset.StandardCharsets
import kotlin.random.Random

class KeyManager {
    @Throws(EncryptionException::class)
    fun generateRandomKey(): ByteArray {
        return try {
            val charPool = ('a'..'z') + ('A'..'Z') + ('0'..'9')
            val keySize = 64
            val key = (1..keySize)
                .map { Random.nextInt(0, charPool.size).let { charPool[it] } }
                .joinToString("")

            key.toByteArray(StandardCharsets.UTF_8)
        } catch (e: Exception) {
            throw EncryptionException("Key generation failed: ${e.message}")
        }
    }
}