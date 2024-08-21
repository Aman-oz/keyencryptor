package com.aman.cipherguard

import android.util.Base64
import android.util.Log
import com.cossacklabs.themis.SecureCell
import com.cossacklabs.themis.SecureCellException
import com.cossacklabs.themis.SymmetricKey
import java.nio.charset.StandardCharsets
import kotlin.random.Random

class SecurityManager {

    fun generateRandomKey(): ByteArray {
        val charPool = ('a' .. 'z') + ('A' .. 'Z') + ('0' .. '9')
        val keySize = 64
        val key = (1 .. keySize)
            .map { Random.nextInt(0, charPool.size).let { charPool[it] } }
            .joinToString("")

        return key.toByteArray(StandardCharsets.UTF_8)
    }

    fun encryptApiKey(apiKey: String, key: ByteArray): String {
        return encryptStringWithKey(key, apiKey)
    }

    fun decryptApiKey(encryptedApiKey: String, key: ByteArray): String? {
        return try {
            decryptStringWithKey(key, encryptedApiKey)
        } catch (e: SecureCellException) {
            Log.e("SecurityManager", "Decryption failed: ${e.message}")
            null
        }
    }

    private fun encryptStringWithKey(key: ByteArray, value: String): String {
        val symmetricKey = SymmetricKey(key)
        val secureCell = SecureCell.SealWithKey(symmetricKey)

        val encryptedData = secureCell.encrypt(value.toByteArray())
        return Base64.encodeToString(encryptedData, Base64.DEFAULT)
    }

    private fun decryptStringWithKey(key: ByteArray, encryptedString: String): String {
        val symmetricKey = SymmetricKey(key)
        val secureCell = SecureCell.SealWithKey(symmetricKey)

        val decodedData = Base64.decode(encryptedString, Base64.DEFAULT)
        return String(secureCell.decrypt(decodedData))
    }
}

