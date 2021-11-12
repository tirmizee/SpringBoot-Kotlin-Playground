package com.tirmizee.repositories.custom

interface CustomUserRepository {

    suspend fun getVersion(): String

    suspend fun updatePasswordByUsername(username: String, password: String): Int?

}