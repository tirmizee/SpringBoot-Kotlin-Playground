package com.tirmizee.providers.models

data class CreateProductRequest(
    val id: Int,
    val name: String?,
    val isActive: Boolean?
)

data class CreateProductResponse(
    val code: String?,
    val message: String?
)