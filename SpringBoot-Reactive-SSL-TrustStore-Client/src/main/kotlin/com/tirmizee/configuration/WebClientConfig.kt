package com.tirmizee.configuration

import com.tirmizee.properties.ServerProperty
import io.netty.handler.ssl.SslContext
import io.netty.handler.ssl.SslContextBuilder
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import java.io.InputStream
import java.security.KeyStore
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import javax.net.ssl.TrustManagerFactory


@Configuration
class WebClientConfig(
    private val serverProperty: ServerProperty
) {

    companion object {
        public val log = LoggerFactory.getLogger(WebClientConfig::class.java)
    }

    @Bean(name = ["webClient"])
    fun webClient() : WebClient =
        WebClient.builder()
            .clientConnector(ReactorClientHttpConnector(httpClient()))
            .build()


    @Bean(name = ["httpClient"])
    fun httpClient(): HttpClient =
        HttpClient
            .create()
            .secure{ spec -> spec.sslContext(sslContext()) }


    @Bean(name = ["sslContext"])
    fun sslContext(): SslContext =
        SslContextBuilder
            .forClient()
//            .trustManager(trustManagerFactory())
//            .trustManager(x509Certificate())
            .trustManager(x509InputStream())
            .build()


    private fun trustManagerFactory(): TrustManagerFactory {
        val trustAlgorithm = TrustManagerFactory.getDefaultAlgorithm()
        val trustManagerFactory: TrustManagerFactory = TrustManagerFactory.getInstance(trustAlgorithm).apply {
            val trustStoreFile = ClassPathResource(serverProperty.trustStore).inputStream
            val trustStore = KeyStore.getInstance(serverProperty.trustStoreType).apply {
                this.load(trustStoreFile, serverProperty.trustStorePassword.toCharArray())
            }
            this.init(trustStore)
        }

        return trustManagerFactory
    }

    private fun x509Certificate(): X509Certificate {
        val certificateFactory = CertificateFactory.getInstance("X.509")
        val x509File = ClassPathResource(serverProperty.trustStorePem).inputStream
        return (certificateFactory.generateCertificate(x509File) as X509Certificate)
    }

    private fun x509InputStream(): InputStream =
        ClassPathResource(serverProperty.trustStorePem).inputStream

}