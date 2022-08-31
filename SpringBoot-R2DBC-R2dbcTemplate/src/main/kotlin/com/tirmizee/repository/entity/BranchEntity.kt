package com.tirmizee.repositories.entities

import org.springframework.data.relational.core.mapping.Table
import java.sql.Timestamp

@Table("branch")
data class BranchEntity (
    var branchId: Int?,
    var bankCode: String?,
    var branchName: String?,
    var branchCode: String?,
    var createdOn: Timestamp?
)