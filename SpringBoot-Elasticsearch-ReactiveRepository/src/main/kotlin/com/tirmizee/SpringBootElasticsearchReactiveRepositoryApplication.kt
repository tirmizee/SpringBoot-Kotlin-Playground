package com.tirmizee

import com.tirmizee.entities.EmployeeEntity
import com.tirmizee.repositories.EmployeeRepository
import kotlinx.coroutines.runBlocking
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootElasticsearchReactiveRepositoryApplication

fun main(args: Array<String>) {
	val application = runApplication<SpringBootElasticsearchReactiveRepositoryApplication>(*args)
	val employeeRepository = application.getBean(EmployeeRepository::class.java)
	val persist = employeeRepository.save(EmployeeEntity(10,"XXXX")).block()
	println(employeeRepository.findById(10).block())
}
