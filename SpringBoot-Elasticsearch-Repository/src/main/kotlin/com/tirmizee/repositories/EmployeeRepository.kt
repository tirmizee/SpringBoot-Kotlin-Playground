package com.tirmizee.repositories

import com.tirmizee.entities.EmployeeEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.data.repository.reactive.ReactiveSortingRepository

interface EmployeeRepository : ReactiveCrudRepository<EmployeeEntity, Long> {

}