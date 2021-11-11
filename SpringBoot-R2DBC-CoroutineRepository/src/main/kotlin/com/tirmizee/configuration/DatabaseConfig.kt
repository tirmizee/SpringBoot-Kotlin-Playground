package com.tirmizee.configuration

import com.tirmizee.properties.DatabaseProperty
import io.r2dbc.spi.ConnectionFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator

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