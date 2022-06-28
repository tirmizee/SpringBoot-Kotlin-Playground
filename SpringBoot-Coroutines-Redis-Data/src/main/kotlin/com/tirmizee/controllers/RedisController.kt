package com.tirmizee.controllers

import com.tirmizee.services.RedisValueService
import org.springframework.web.bind.annotation.*

@RestController
class RedisController(
    val redisService: RedisValueService
) {

    @PostMapping("/redis/set/{key}")
    suspend fun redisSet(@PathVariable key: String, @RequestBody value: String) =
        redisService.setKey(key, value)

    @GetMapping("/redis/get/{key}")
    suspend fun redisGet(@PathVariable key: String) =
        redisService.getKey(key)

    @GetMapping("/redis/del/{key}")
    suspend fun redisDel(@PathVariable key: String) =
        redisService.delKey(key)

}