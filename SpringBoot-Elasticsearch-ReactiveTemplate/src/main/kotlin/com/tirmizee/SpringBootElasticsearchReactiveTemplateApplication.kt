package com.tirmizee

import com.tirmizee.entities.EmployeeEntity
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.runBlocking
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchTemplate

@SpringBootApplication
class SpringBootElasticsearchReactiveTemplateApplication

fun main(args: Array<String>) {
	val application = runApplication<SpringBootElasticsearchReactiveTemplateApplication>(*args)
	val reactiveElasticsearchTemplate = application.getBean(ReactiveElasticsearchTemplate::class.java)
	val persist = reactiveElasticsearchTemplate.save(EmployeeEntity(10, "tirmizee","XXXX")).block()
	println(reactiveElasticsearchTemplate.get("10", EmployeeEntity::class.java).block())
}
