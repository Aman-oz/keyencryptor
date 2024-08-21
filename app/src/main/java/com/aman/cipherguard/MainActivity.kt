package com.aman.cipherguard

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aman.cipherguard.managers.DecryptionManager
import com.aman.encryptor.managers.EncryptionManager

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val apiKey = "xrderxyx"

        val encryptionManager = EncryptionManager()
        val decryptionManager = DecryptionManager()


        val (encryptedApiKey, key) = encryptionManager.runEncryption(apiKey)

        Log.d(TAG, "**********************")
        Log.d(TAG, "onCreate: Encrypted API key: $encryptedApiKey")
        Log.d(TAG, "onCreate: Key: $key")
        Log.d(TAG, "**********************")

        val decryptedApiKey = decryptionManager.runDecryption(encryptedApiKey, key)

        Log.d(TAG, "onCreate: Encrypted API key: $encryptedApiKey")
        Log.d(TAG, "onCreate: Decrypted API key: $decryptedApiKey")


        /*val keyManager = KeyManager()
        val encryptionManager = EncryptionManager()
        val decryptionManager = DecryptionManager()

        try {
            val apiKey = "xrderxyx"*//*"ttcHNDEIHmybFKMPCCg6GE0-SHHtx5Il000TufyXlDWyi71Mc4-zW-CBl96Uxs99RV8X0GbwqTWubcNU6UEfi6-ttcHNDEIHmybFKMPCCg6GE0-SHHtx5Il000TufyXlDWyi71Mc4-zW-CBl96Uxs99RV8X0GbwqTWubcNU6UEfi6"*//*
            val key = keyManager.generateRandomKey()

            // Encrypt the API key
            val encryptedApiKey = encryptionManager.encrypt(apiKey, key)
            println("Encrypted API Key: $encryptedApiKey")
            Log.d(TAG, "onCreate: Encrypted API key: $encryptedApiKey")

            // Decrypt the API key using the same key
            val decryptedApiKey = decryptionManager.decrypt(encryptedApiKey, key)
            println("Decrypted API Key: $decryptedApiKey")
            Log.d(TAG, "onCreate: Decrypted API key: $decryptedApiKey")

        } catch (e: EncryptionException) {
            Log.e("MainActivity", "Encryption error: ${e.message}")
        } catch (e: DecryptionException) {
            Log.e("MainActivity", "Decryption error: ${e.message}")
        }*/


        /*val securityManager = SecurityManager()
        val apiKey = "ttcHNDEIHmybFKMPCCg6GE0-SHHtx5Il000TufyXlDWyi71Mc4-zW-CBl96Uxs99RV8X0GbwqTWubcNU6UEfi6"

        // Generate a random key and store it securely
        val key = securityManager.generateRandomKey()

        // Encrypt the API key
        val encryptedApiKey = securityManager.encryptApiKey(apiKey, key)
        println("Encrypted API Key: $encryptedApiKey")
        Log.d(TAG, "onCreate: Encrypted API key: $encryptedApiKey")

        // Decrypt the API key using the same key
        val decryptedApiKey = securityManager.decryptApiKey(encryptedApiKey, key)
        println("Decrypted API Key: $decryptedApiKey")
        Log.d(TAG, "onCreate: Decrypted API key: $decryptedApiKey")*/
    }
}