package com.tirmizee.exception.data

open class BusinessException(
    val code: String = "",
    override val message: String = ""
): RuntimeException(message)