package com.tirmizee.entities

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.sql.Timestamp

@Table("users")
class UserEntity(
    @Id
    var userId: Long?,
    val email: String,
    val username: String,
    val password: String,
    val createdOn: Timestamp?
)