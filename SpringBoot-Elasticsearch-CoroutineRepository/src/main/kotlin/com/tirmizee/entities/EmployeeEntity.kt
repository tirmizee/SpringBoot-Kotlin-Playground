package com.tirmizee.entities

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document

@Document(indexName = "employee")
data class EmployeeEntity(
    @Id
    val id: Long,
    val name: String? = null
)