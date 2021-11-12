
### docker-compose.yaml

```yaml

version: '3'
services:
  database:
    image: postgres
    ports:
      - '5432:5432'
    environment:
      - POSTGRES_USER=posttgresuser
      - POSTGRES_PASSWORD=posttgrespassword
      - POSTGRES_DB=rainbow


```

### application.yaml

```yaml

spring:
  r2dbc:
    url: "r2dbc:pool:postgresql://0.0.0.0:5432/rainbow"
    username: posttgresuser
    password: posttgrespassword
    datasource-initialization: true
    datasource-schema: /database/schema.sql
    datasource-data: /database/data.sql
    pool:
      max-size: 10
      initial-size: 2
      max-idle-time: 30m
      validation-query: SELECT 1
logging:
  level:
    org.springframework.data.r2dbc: TRACE



```

### database/schema.sql

```roomsql

DROP TABLE IF EXISTS users;
CREATE TABLE users (
    user_id serial PRIMARY KEY,
    email VARCHAR ( 255 ) UNIQUE NOT NULL,
    username VARCHAR ( 50 ) UNIQUE NOT NULL,
    password VARCHAR ( 50 ) NOT NULL,
    created_on TIMESTAMP NOT NULL
);

DROP TABLE IF EXISTS branch;
CREATE TABLE branch (
    branch_id serial PRIMARY KEY,
    bank_code VARCHAR ( 50 )  NOT NULL,
    branch_code VARCHAR ( 50 ) NOT NULL,
    branch_name VARCHAR ( 250 ),
    created_on TIMESTAMP NOT NULL
);

```

### database/data.sql

```roomsql

INSERT INTO users(email, username, "password", created_on) VALUES('yeekhaday@hotmail.com', 'pratya', 'password', '2021-11-12 02:31:05.428');
INSERT INTO users(email, username, "password", created_on) VALUES('tirmizee@hotmail.com', 'tirmizee', 'password', '2021-11-12 02:31:05.428');
COMMIT;

```

### set up a database during initialization

```kotlin

@Configuration
class DatabaseConfig(
    val databaseProperty: DatabaseProperty
) {

    @Bean
    fun initializer(@Qualifier("connectionFactory") connectionFactory: ConnectionFactory): ConnectionFactoryInitializer {
        val initializer = ConnectionFactoryInitializer()
        initializer.setEnabled(databaseProperty.datasourceInitialization)
        initializer.setConnectionFactory(connectionFactory)
        val populator = CompositeDatabasePopulator()
        populator.addPopulators(ResourceDatabasePopulator(ClassPathResource(databaseProperty.datasourceSchema)))
        populator.addPopulators(ResourceDatabasePopulator(ClassPathResource(databaseProperty.datasourceData)))
        initializer.setDatabasePopulator(populator)
        return initializer
    }

}

```

### CoroutineRepository

```kotlin

interface UserRepository : CoroutineSortingRepository<UserEntity, Long>, CustomUserRepository {

    suspend fun findByUsername(username: String) : UserEntity?

    suspend fun findByEmail(username: String) : UserEntity?

}

```

### Custom CoroutineRepository

```kotlin

@Repository
class CustomUserRepositoryImpl(val r2dbcEntityTemplate: R2dbcEntityTemplate): CustomUserRepository {

    override suspend fun getVersion() = "1"

    override suspend fun updatePasswordByUsername(username: String, password: String): Int? =
        r2dbcEntityTemplate.runCatching {
            this.update(UserEntity::class.java)
                .matching(query(where("username").`is`(username)))
                .apply(
                    Update.update("password", password)
//                        .set("column_name","value")
//                        .set("column_name","value")
//                        .set("column_name","value")
//                        .set("column_name","value")
//                        .set("column_name","value")
//                        .set("column_name","value")
//                        .set("column_name","value")
//                        .set("column_name","value")
//                        .set("column_name","value")
                )
                .awaitSingle()
        }
        .onSuccess { updated -> updated }
        .onFailure { exception ->  println(exception) }
        .getOrNull()

}

```

### Demo

```kotlin

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

```

### Reference

- https://github.com/spring-guides/tut-spring-boot-kotlin