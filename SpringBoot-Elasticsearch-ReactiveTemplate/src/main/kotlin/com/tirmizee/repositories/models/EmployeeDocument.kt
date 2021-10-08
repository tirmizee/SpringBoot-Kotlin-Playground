package com.tirmizee.repositories.models

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType

@Document(indexName = EmployeeDocument.index)
data class EmployeeDocument(

    @Id
    val id: Long,

    @Field(type = FieldType.Keyword)
    val username: String,

    @Field(type = FieldType.Text)
    val name: String? = null,

    @Field(type = FieldType.Integer)
    val age: Int?

) {
    companion object {
        const val index = "employee"
    }
}