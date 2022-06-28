package com.tirmizee

import com.tirmizee.services.RedisHashService
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext

@SpringBootApplication
class SpringBootRedisDataCoroutinesApplication: CommandLineRunner {

	@Autowired
	lateinit var applicationContext: ApplicationContext

	override fun run(vararg args: String?) {
		val redisHashService = applicationContext.getBean(RedisHashService::class.java)
		runBlocking {
			println(redisHashService.getKey("1"))
		}

	}

}

fun main(args: Array<String>) {
	runApplication<SpringBootRedisDataCoroutinesApplication>(*args)
}
