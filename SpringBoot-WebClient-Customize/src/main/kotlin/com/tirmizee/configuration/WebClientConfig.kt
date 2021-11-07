package com.tirmizee.configuration

import com.tirmizee.properties.WebClientProperty
import io.netty.channel.ChannelOption
import io.netty.channel.epoll.EpollChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.slf4j.LoggerFactory
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.*

import reactor.core.publisher.Mono
import reactor.netty.http.client.HttpClient
import java.time.Duration
import java.util.concurrent.TimeUnit
import java.util.function.Consumer


@Configuration
class WebClientConfig(
    private val webClientProperty: WebClientProperty
) {

    companion object {
        private val log = LoggerFactory.getLogger(WebClientConfig::class.java)
    }

    @Bean(name = ["webClient"])
    fun webClient(httpClient: HttpClient) : WebClient {
        val builder = WebClient.builder()
            .baseUrl(webClientProperty.baseUrl)
            .clientConnector(ReactorClientHttpConnector(httpClient))
        return builder.build()
    }

    @Bean(name = ["httpClient"])
    fun httpClient() : HttpClient {
        return HttpClient.create()
            .responseTimeout(Duration.ofSeconds(3))
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
            .option(ChannelOption.SO_KEEPALIVE, true)
            .option(EpollChannelOption.TCP_KEEPIDLE, 300)
            .option(EpollChannelOption.TCP_KEEPINTVL, 60)
            .option(EpollChannelOption.TCP_KEEPCNT, 8)
            .doOnConnected{conn ->
                conn.addHandler( ReadTimeoutHandler(10))
                conn.addHandler( WriteTimeoutHandler(10))
            }
    }

}