package com.tirmizee.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping("/")
    suspend fun main(): String? {
        return "path /"
    }

    @GetMapping("/members")
    suspend fun members(): String? {
        return "path /members"
    }

    @GetMapping("/members/{member}")
    suspend fun membersBy(@PathVariable member: String): String? {
        return "path /members/$member"
    }

}