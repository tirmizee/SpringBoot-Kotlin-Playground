package com.tirmizee

import com.tirmizee.repositories.models.EmployeeDocument
import com.tirmizee.repositories.EmployeeRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchTemplate

@SpringBootApplication
class SpringBootElasticsearchReactiveTemplateApplication

fun main(args: Array<String>) {
	val application = runApplication<SpringBootElasticsearchReactiveTemplateApplication>(*args)
	val reactiveElasticsearchTemplate = application.getBean(ReactiveElasticsearchTemplate::class.java)
	val employeeRepository = application.getBean(EmployeeRepository::class.java)
//	val persist = reactiveElasticsearchTemplate.save(EmployeeDocument(1, "tirmizee","XXXX",27)).block()
//	println(reactiveElasticsearchTemplate.get("10", EmployeeEntity::class.java).block())
	val emp = employeeRepository.findById(1)
	employeeRepository.updateAge(emp?.id.toString(),30)

}
