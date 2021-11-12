package com.tirmizee.repositories.custom

import org.springframework.stereotype.Repository

@Repository
class CustomUserRepositoryImpl: CustomUserRepository {

    override suspend fun getVersion() = "1"

}