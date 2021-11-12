package com.tirmizee.repositories.custom

interface CustomUserRepository {

    suspend fun getVersion(): String

}