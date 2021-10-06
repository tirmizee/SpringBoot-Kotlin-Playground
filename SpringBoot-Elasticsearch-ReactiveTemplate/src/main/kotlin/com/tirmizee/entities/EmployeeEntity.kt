package com.tirmizee.entities

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType

@Document(indexName = "employee")
data class EmployeeEntity(

    @Id
    val id: Long,

    @Field(type = FieldType.Keyword)
    val username: String,

    val name: String? = null
)