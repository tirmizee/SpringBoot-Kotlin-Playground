package com.tirmizee.controllers

import com.tirmizee.models.PaymentRequest
import com.tirmizee.models.PaymentResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.util.*

@RestController
class ApiController {

    @GetMapping("/ping")
    fun hello(): Mono<String> {
        return Mono.just("Hello SSL")
    }

    @PostMapping("/payment")
    fun payment(@RequestBody paymentRequest: PaymentRequest): Mono<PaymentResponse> {
        return Mono.just(
            PaymentResponse(
                paymentRequest.paymentId,
                UUID.randomUUID().toString()
            )
        )
    }

}