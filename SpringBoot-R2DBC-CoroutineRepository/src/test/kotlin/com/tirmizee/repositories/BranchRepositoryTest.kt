package com.tirmizee.repositories

import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BranchRepositoryTest  @Autowired constructor(
    val branchRepository: BranchRepository
){

    @Test
    fun `test update branch_name`(): Unit = runBlocking {

        // given
        val branchCode = "00600"
        val branchName = "New Branch"

        // when
        val updatedCount = branchRepository.updateBranchName(branchCode, branchName)

        // then
        assertThat(updatedCount).isGreaterThan(0)
    }

    @Test
    fun `test find by bank_code`(): Unit = runBlocking {

        // given
        val bankCode = "006"

        // when
        val branch = branchRepository.findByBankCode(bankCode)

        // then
        assertThat(branch?.branchCode).isNotNull
        assertThat(branch?.createdOn).isNull()
    }

}