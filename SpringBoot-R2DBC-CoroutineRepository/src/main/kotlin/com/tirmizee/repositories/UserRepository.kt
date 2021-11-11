package com.tirmizee.repositories

import com.tirmizee.entities.UserEntity
import org.springframework.data.repository.kotlin.CoroutineSortingRepository

interface UserRepository : CoroutineSortingRepository<UserEntity, Long> {

    suspend fun findByUsername(username: String)

    suspend fun findByEmail(username: String)

}