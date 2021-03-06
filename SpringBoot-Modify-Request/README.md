### filter

```kotlin 

@Component
class DecryptRequestFilter : WebFilter {

    companion object {
        val log: Logger = LoggerFactory.getLogger(DecryptRequestFilter::class.java)
    }

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val requestResponseExchange = DecryptRequestExchange(exchange)
        return chain.filter(requestResponseExchange)
    }

    class DecryptRequestExchange(exchange: ServerWebExchange) : ServerWebExchangeDecorator(exchange) {
        override fun getRequest(): DecryptRequestDecorator = DecryptRequestDecorator(super.getRequest())
    }

    class DecryptRequestDecorator(delegate: ServerHttpRequest) : ServerHttpRequestDecorator(delegate) {

        private var decryptBuffer : ByteArray? = null

        override fun getBody(): Flux<DataBuffer> {
            return super.getBody().flatMap {
                decryptBuffer = AESUtils.decrypt(StandardCharsets.UTF_8.decode(it.asByteBuffer()).toString())
                Flux.just(DefaultDataBufferFactory().wrap(ByteBuffer.wrap(decryptBuffer)))
            }
        }

    }

}

```

### Demo

    curl -XPOST -H "Content-type: application/json" -d '9KMaVQa+bS+smEc5NWtn4Cfkjpa6GT6rhmdMwYHgt3s=' 'http://localhost:8080/api/hello'
    hello revise%                                                   