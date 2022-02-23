
### Demo

```kotlin

// SimpleFilter.kt
@Component
class SimpleFilter: WebFilter {

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        exchange.attributes["requestId"] = UUID.randomUUID().toString()
        return chain.filter(exchange)
    }

}

// Handler.kt
@Component
class Handler {

    suspend fun ping(serverRequest: ServerRequest) : ServerResponse {
        val uuid = serverRequest.attribute("requestId").get()
        return ServerResponse.ok().bodyValueAndAwait("Hello world $uuid")
    }

}

```