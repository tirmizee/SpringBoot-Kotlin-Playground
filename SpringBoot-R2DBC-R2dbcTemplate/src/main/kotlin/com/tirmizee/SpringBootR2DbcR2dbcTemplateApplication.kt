package com.tirmizee

import com.tirmizee.repository.BranchRepository
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext

@SpringBootApplication
@ConfigurationPropertiesScan
class SpringBootR2DbcR2dbcTemplateApplication: CommandLineRunner {

	@Autowired
	lateinit var applicationContext: ApplicationContext

	override fun run(vararg args: String?) {
		val branchRepository = applicationContext.getBean(BranchRepository::class.java)
		runBlocking {
			println(branchRepository.findByBranchId(1))
			println(branchRepository.getFirst())
		}
	}

}

fun main(args: Array<String>) {
	runApplication<SpringBootR2DbcR2dbcTemplateApplication>(*args)
}
