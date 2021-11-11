package com.tirmizee

import com.tirmizee.entities.UserEntity
import com.tirmizee.repositories.UserRepository
import kotlinx.coroutines.runBlocking
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import java.sql.Timestamp

@SpringBootApplication
@ConfigurationPropertiesScan
class SpringBootR2DbcCoroutineRepositoryApplication

fun main(args: Array<String>) {
	val application = runApplication<SpringBootR2DbcCoroutineRepositoryApplication>(*args)
	val userRepository = application.getBean(UserRepository::class.java)
	runBlocking {
		userRepository.save(UserEntity(null,"zee_pratya@hotmail.com","tirmizee","password", Timestamp(System.currentTimeMillis())))
	}

}
