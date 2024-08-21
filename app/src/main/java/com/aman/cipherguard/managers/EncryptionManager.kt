package com.aman.cipherguard.managers

import android.util.Base64
import com.aman.cipherguard.exceptions.EncryptionException
import com.cossacklabs.themis.SecureCell
import com.cossacklabs.themis.SymmetricKey

class EncryptionManager {
    @Throws(EncryptionException::class)
    fun encrypt(apiKey: String, key: ByteArray): String {
        return try {
            val symmetricKey = SymmetricKey(key)
            val secureCell = SecureCell.SealWithKey(symmetricKey)

            val encryptedData = secureCell.encrypt(apiKey.toByteArray())
            Base64.encodeToString(encryptedData, Base64.DEFAULT)
        } catch (e: Exception) {
            throw EncryptionException("Encryption failed: ${e.message}")
        }
    }


    fun runEncryption(apiKey: String): Pair<String, String> {
        val keyManager = KeyManager()
        val encryptionManager = EncryptionManager()
        val key = keyManager.generateRandomKey()

        val encryptedApiKey = encryptionManager.encrypt(apiKey, key)
        val base64Key = Base64.encodeToString(key, Base64.DEFAULT)

        println("Encrypted API Key: $encryptedApiKey")
        println("Key (Base64): $base64Key")

        return Pair(encryptedApiKey, base64Key)
    }

}