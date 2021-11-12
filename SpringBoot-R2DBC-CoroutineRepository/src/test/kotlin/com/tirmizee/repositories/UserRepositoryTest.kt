package com.tirmizee.repositories

import com.tirmizee.entities.UserEntity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.annotation.Autowired
import java.sql.Timestamp

@SpringBootTest
class UserRepositoryTest @Autowired constructor(
    val userRepository: UserRepository
) {

    @Test
    fun `test get by email`(): Unit = runBlocking {

        // given
        val email = "zee_pratya@hotmail.com"

        // when
        val user = userRepository.findByEmail(email)

        // then
        assertThat(user?.email).isEqualTo(email)
    }

    @Test
    fun `test get by username`(): Unit = runBlocking {

        // given
        val username = "tirmizee"

        // when
        val user = userRepository.findByUsername(username)

        // then
        assertThat(user?.username).isEqualTo(username)
    }

    @Test
    fun `test get all`(): Unit = runBlocking {

        // when
        val users = userRepository.findAll()

        // then
        assertThat(users.toList().size).isGreaterThan(0)
    }

    @Test
    fun `test create user`(): Unit = runBlocking {

        // given
        val user = UserEntity(
            userId = null,
            username = "test",
            email = "test@hotmail.com",
            password = "password",
            createdOn = Timestamp(System.currentTimeMillis())
        )

        // when
        val persist = userRepository.save(user)

        // then
        assertThat(persist?.userId).isNotNull()
    }

    @Test
    fun `test update user`(): Unit = runBlocking {

        // given
        val user = userRepository.findByUsername("tirmizee")

        // when
        user?.password = "newpasswpord"
        val persist = user?.let {
            userRepository.save(user)
        }

        // then
        assertThat(persist?.password).isEqualTo(user?.password)
    }

    @Test
    fun `test update password when user not found`(): Unit = runBlocking {

        // given
        val username = "no_user"
        val password = "new_password"

        // when
        val updatedCount = userRepository.updatePasswordByUsername(username, password)

        // then
        assertThat(updatedCount).isEqualTo(0)
    }

}