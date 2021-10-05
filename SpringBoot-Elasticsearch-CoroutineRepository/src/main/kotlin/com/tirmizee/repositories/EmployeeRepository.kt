package com.tirmizee.repositories

import com.tirmizee.entities.EmployeeEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface EmployeeRepository : CoroutineCrudRepository<EmployeeEntity, Long> {

}