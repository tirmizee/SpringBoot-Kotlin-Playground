package com.tirmizee.services

import com.tirmizee.models.Member
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.redis.core.*
import org.springframework.stereotype.Service

@Service
class RedisHashService(
    var redisMemberTemplate: ReactiveRedisTemplate<String, Member>
)  {

    suspend fun setKey(key: String, value: String): Boolean {
        var redisReactiveHashOperations = redisMemberTemplate.opsForHash<String, Member>();
        return redisReactiveHashOperations.putAndAwait("USERS", key, Member("22", "33", true))
    }

    suspend fun getKey(key: String): Member? {
        var redisReactiveHashOperations = redisMemberTemplate.opsForHash<String, Member>();
        return redisReactiveHashOperations.getAndAwait("USERS", key)
    }

}