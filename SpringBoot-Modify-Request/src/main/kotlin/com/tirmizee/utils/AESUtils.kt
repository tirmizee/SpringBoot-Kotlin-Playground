package com.tirmizee.utils

import java.io.UnsupportedEncodingException
import java.nio.charset.Charset
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

class AESUtils {



    companion object {

        private const val rawKey = "sssss"
        private lateinit var secretKey: SecretKeySpec
        private lateinit var key: ByteArray
        private lateinit var sha :MessageDigest

        init {

            try {
                key = rawKey.toByteArray(Charsets.UTF_8)
                sha = MessageDigest.getInstance("SHA-1")
                key = sha.digest(key)
                key = key.copyOf(16)
                secretKey = SecretKeySpec( key, "AES")
            } catch (e : NoSuchAlgorithmException) {
                e.printStackTrace()
            } catch (e : UnsupportedEncodingException) {
                e.printStackTrace()
            }
        }

        fun decrypt(strToDecrypt: String?): ByteArray? {
            try {
                val cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING")
                cipher.init(Cipher.DECRYPT_MODE, secretKey)
                return cipher.doFinal(Base64.getDecoder().decode(strToDecrypt))
            } catch (e: Exception) {
                println("Error while decrypting: $e")
            }
            return null
        }

        fun encrypt(strToEncrypt: String): String? {
            try {
                val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
                cipher.init(Cipher.ENCRYPT_MODE, secretKey)
                return Base64.getEncoder()
                    .encodeToString(cipher.doFinal(strToEncrypt.toByteArray(charset("UTF-8"))))
            } catch (e: Exception) {
                println("Error while encrypting: $e")
            }
            return null
        }

        fun encrypt(raw: ByteArray?): String? {
            try {
                val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
                cipher.init(Cipher.ENCRYPT_MODE, secretKey)
                return Base64.getEncoder()
                    .encodeToString(cipher.doFinal(raw))
            } catch (e: Exception) {
                println("Error while encrypting: $e")
            }
            return null
        }

    }

}