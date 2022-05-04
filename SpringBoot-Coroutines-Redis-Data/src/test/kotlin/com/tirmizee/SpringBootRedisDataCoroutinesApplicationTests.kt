package com.tirmizee

import com.tirmizee.models.Member
import com.tirmizee.services.RedisService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.core.ReactiveStringRedisTemplate

@SpringBootTest
class SpringBootRedisDataCoroutinesApplicationTests {

	@Autowired
	lateinit var application: ApplicationContext

	@Autowired
	lateinit var redisService: RedisService

	@Autowired
	lateinit var reactiveRedisTemplate: ReactiveRedisTemplate<String, String>

	@Autowired
	lateinit var redisMemberTemplate: ReactiveRedisTemplate<String, Member>

	@Test
	fun contextLoadReactiveRedisConnectionFactory() {
		var reactiveRedisConnectionFactory = application.getBean(ReactiveRedisConnectionFactory::class.java)

		Assertions.assertThat(reactiveRedisConnectionFactory).isNotNull
		Assertions.assertThat(reactiveRedisConnectionFactory).isOfAnyClassIn(LettuceConnectionFactory::class.java)
	}

	@Test
	fun contextLoadReactiveStringRedisTemplate() {
		var reactiveRedisTemplate = application.getBean(ReactiveStringRedisTemplate::class.java)

		Assertions.assertThat(reactiveRedisTemplate).isNotNull
	}

	@Test
	fun contextLoadReactiveRedisTemplate() {
		Assertions.assertThat(reactiveRedisTemplate).isNotNull
	}

	@Test
	fun contextLoadRedisMemberTemplate() {
		Assertions.assertThat(redisMemberTemplate).isNotNull
	}

	@Test
	fun contextLoadRedisService() {
		Assertions.assertThat(redisService).isNotNull
	}

}
