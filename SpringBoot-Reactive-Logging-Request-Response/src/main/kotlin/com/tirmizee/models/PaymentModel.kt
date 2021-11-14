package com.tirmizee.models

data class PaymentRequest(val paymentId: String, val paymentAmount: Int)
data class PaymentResponse(val statusCode: String, val statusDesc: String)