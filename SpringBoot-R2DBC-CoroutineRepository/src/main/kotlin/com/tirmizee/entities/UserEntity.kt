package com.tirmizee.entities

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.sql.Timestamp

@Table("users")
data class UserEntity(
    @Id
    var userId: Long?,
    var email: String,
    var username: String,
    var password: String,
    var createdOn: Timestamp?
)