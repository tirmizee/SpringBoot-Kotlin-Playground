package com.tirmizee.models

import java.time.temporal.TemporalAmount

data class PaymentRequest(
    val paymentId: Int
)

data class PaymentResponse(
    val paymentId: Int,
    val paymentName: String
)