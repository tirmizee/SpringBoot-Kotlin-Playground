package com.tirmizee.repository

import com.tirmizee.repositories.entities.BranchEntity
import org.springframework.data.r2dbc.convert.MappingR2dbcConverter
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.r2dbc.core.awaitOneOrNull
import org.springframework.stereotype.Repository

@Repository
class BranchRepository(
    private val converter: MappingR2dbcConverter,
    private val r2dbcEntityTemplate: R2dbcEntityTemplate
) {

    suspend fun findByBranchId(branchId: Int): BranchEntity? {
        return r2dbcEntityTemplate.databaseClient
            .sql("SELECT * FROM branch WHERE branch_id = :branchId")
            .bind("branchId", branchId)
            .map { row, metadata -> converter.read(BranchEntity::class.java, row, metadata) }
            .awaitOneOrNull()
    }

    suspend fun getFirst(): BranchEntity? {
        return r2dbcEntityTemplate.databaseClient
            .sql("SELECT * FROM branch")
            .map { row, metadata -> converter.read(BranchEntity::class.java, row, metadata) }
            .awaitOneOrNull()
    }

}