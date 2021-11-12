package com.tirmizee.repositories

import com.tirmizee.repositories.custom.CustomBranchRepository
import com.tirmizee.repositories.entities.BranchEntity
import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineSortingRepository

interface BranchRepository: CoroutineSortingRepository<BranchEntity, Long>, CustomBranchRepository {

    @Query(""" 
        SELECT 
            bank_code,
            branch_code,
            branch_name
        FROM branch 
        WHERE bank_code = :bankCode
    """)
    suspend fun findByBankCode(bankCode: String): BranchEntity?

    @Modifying
    @Query("UPDATE branch SET branch_name = :branchName where branch_code = :branchCode")
    suspend fun updateBranchName(branchCode: String, branchName: String?): Int?

}