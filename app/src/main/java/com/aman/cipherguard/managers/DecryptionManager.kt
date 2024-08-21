package com.aman.cipherguard.managers

import android.util.Base64
import android.util.Log
import com.aman.cipherguard.exceptions.DecryptionException
import com.cossacklabs.themis.SecureCell
import com.cossacklabs.themis.SecureCellException
import com.cossacklabs.themis.SymmetricKey

class DecryptionManager {

    fun runDecryption(encryptedApiKey: String, base64Key: String): String? {

        return try {
            val key = Base64.decode(base64Key, Base64.DEFAULT)
            val decryptedApiKey = decrypt(encryptedApiKey, key)
            println("Decrypted API Key: $decryptedApiKey")
            decryptedApiKey

        } catch (e: DecryptionException) {
            Log.e("DecryptionProject", "Decryption error: ${e.message}")
            null
        }
    }

    @Throws(DecryptionException::class)
    fun decrypt(encryptedApiKey: String, key: ByteArray): String? {
        return try {
            val symmetricKey = SymmetricKey(key)
            val secureCell = SecureCell.SealWithKey(symmetricKey)

            val decodedData = Base64.decode(encryptedApiKey, Base64.DEFAULT)
            String(secureCell.decrypt(decodedData))
        } catch (e: SecureCellException) {
            throw DecryptionException("Decryption failed: ${e.message}")
        } catch (e: Exception) {
            throw DecryptionException("Unexpected error during decryption: ${e.message}")
        }
    }

}