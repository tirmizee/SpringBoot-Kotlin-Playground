package com.tirmizee.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import javax.annotation.PostConstruct

@Configuration
class TruststoreConfig {

    @PostConstruct
    fun truststore() {
        System.setProperty("javax.net.ssl.trustStore", "/Users/pratya.yeekhaday/Desktop/SpringBoot-Kotlin-Playground/SpringBoot-Reactive-SSL-TrustStore-Global/src/main/resources/store/truststore.jks")
        System.setProperty("javax.net.ssl.trustStorePassword", "storepass")
        System.setProperty("javax.net.ssl.trustStoreType", "JKS")
    }

}