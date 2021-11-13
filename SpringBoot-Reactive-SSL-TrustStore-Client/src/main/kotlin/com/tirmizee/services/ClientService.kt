package com.tirmizee.services

import com.tirmizee.configuration.WebClientConfig
import com.tirmizee.properties.WebClientProperty
import kotlinx.coroutines.reactor.awaitSingle
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class ClientService(
    val webClient: WebClient,
    val webClientProperty: WebClientProperty
){

    companion object {
        val log: Logger = LoggerFactory.getLogger(ClientService::class.java)
    }

    suspend fun ping() =
        webClient.runCatching {
            this.get()
                .uri(webClientProperty.ping)
                .retrieve()
                .bodyToMono(String::class.java)
                .awaitSingle()
        }.onSuccess {
            log.info(it)
        }.onFailure {
            log.info(it.message)
        }.getOrNull()

}