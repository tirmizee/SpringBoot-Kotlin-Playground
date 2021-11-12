package com.tirmizee.repositories

import com.tirmizee.repositories.entities.BranchEntity
import org.springframework.data.repository.kotlin.CoroutineSortingRepository

interface BranchRepository: CoroutineSortingRepository<BranchEntity, Long> {

}