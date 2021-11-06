package com.tirmizee.providers.models

data class GetProductResponse(
    val id: Int,
    val name: String?,
    val isActive: Boolean?
)