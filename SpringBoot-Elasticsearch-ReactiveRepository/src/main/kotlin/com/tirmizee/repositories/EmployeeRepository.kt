package com.tirmizee.repositories

import com.tirmizee.entities.EmployeeEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface EmployeeRepository : ReactiveCrudRepository<EmployeeEntity, Long> {

}