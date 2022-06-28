package com.tirmizee.services

import org.springframework.data.redis.core.ReactiveStringRedisTemplate
import org.springframework.data.redis.core.deleteAndAwait
import org.springframework.data.redis.core.getAndAwait
import org.springframework.data.redis.core.setAndAwait
import org.springframework.stereotype.Service

@Service
class RedisValueService(
    var reactiveStringRedisTemplate: ReactiveStringRedisTemplate
) {

    suspend fun setKey(key: String, value: String): Boolean {
        return reactiveStringRedisTemplate.opsForValue().setAndAwait(key, value)
    }

    suspend fun getKey(key: String): String? {
        return reactiveStringRedisTemplate.opsForValue().getAndAwait(key)
    }

    suspend fun delKey(key: String): Boolean {
        return reactiveStringRedisTemplate.opsForValue().deleteAndAwait(key)
    }

}