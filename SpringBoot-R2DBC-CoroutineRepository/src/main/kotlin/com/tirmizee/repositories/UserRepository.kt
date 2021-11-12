package com.tirmizee.repositories

import com.tirmizee.entities.UserEntity
import com.tirmizee.repositories.custom.CustomUserRepository
import org.springframework.data.repository.kotlin.CoroutineSortingRepository

interface UserRepository : CoroutineSortingRepository<UserEntity, Long>, CustomUserRepository {

    suspend fun findByUsername(username: String) : UserEntity?

    suspend fun findByEmail(username: String) : UserEntity?

}