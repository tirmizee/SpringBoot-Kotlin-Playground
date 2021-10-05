package com.tirmizee

import com.tirmizee.entities.EmployeeEntity
import com.tirmizee.repositories.EmployeeRepository
import kotlinx.coroutines.runBlocking
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.repository.kotlin.CoroutineSortingRepository

@SpringBootApplication
class SpringBootElasticsearchRepositoryApplication

fun main(args: Array<String>) {
	val application = runApplication<SpringBootElasticsearchRepositoryApplication>(*args)
	val employeeRepository = application.getBean(EmployeeRepository::class.java)
	val persist = employeeRepository.save(EmployeeEntity(4,"Smith")).block()
	println(employeeRepository.findById(4).block())
}
