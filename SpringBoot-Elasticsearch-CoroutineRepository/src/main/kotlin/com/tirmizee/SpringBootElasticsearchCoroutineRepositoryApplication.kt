package com.tirmizee

import com.tirmizee.entities.EmployeeEntity
import com.tirmizee.repositories.EmployeeRepository
import kotlinx.coroutines.runBlocking
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootElasticsearchCoroutineRepositoryApplication

fun main(args: Array<String>) {
	val application = runApplication<SpringBootElasticsearchCoroutineRepositoryApplication>(*args)
	val employeeRepository = application.getBean(EmployeeRepository::class.java)
	runBlocking {
		val persist = employeeRepository.save(EmployeeEntity(7,"John"))
		println(employeeRepository.findById(7))
	}

}
