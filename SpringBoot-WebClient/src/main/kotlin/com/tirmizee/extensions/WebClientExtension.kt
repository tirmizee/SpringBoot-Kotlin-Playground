package com.tirmizee.extensions

import jdk.internal.net.http.common.Log.logHeaders
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.util.MultiValueMap
import org.springframework.web.reactive.function.client.ClientRequest
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.net.URI

inline fun <reified T> typeReference() = object : ParameterizedTypeReference<T>() {}

inline fun <reified T: Any>WebClient.get(uri: URI, headers: MultiValueMap<*,*> = HttpHeaders()) : T? =
    this.get()
        .uri(uri)
        .accept(MediaType.APPLICATION_JSON)
        .headers { headers }
        .retrieve()
        .bodyToMono(T::class.java)
        .block()

inline fun <reified T: Any>WebClient.post(uri: URI, headers: MultiValueMap<*,*> = HttpHeaders()) : T? =
    this.post()
        .uri(uri)
        .headers { headers }
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(T::class.java)
        .block()

inline fun <reified S: Any, reified T: Any>WebClient.post(uri: URI, request: S, headers: MultiValueMap<*,*> = HttpHeaders()) : T? =
    this.post()
        .uri(uri)
        .headers { headers }
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(request)
        .retrieve()
        .bodyToMono(T::class.java)
        .block()

