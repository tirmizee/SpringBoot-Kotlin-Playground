
### Filter order 1

```kotlin

@Order(1)
@Component
class UniqueFilter: WebFilter {

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        exchange.attributes["requestId"] = UUID.randomUUID().toString()
        return chain.filter(exchange)
    }

}

```

### Filter logging order 2

```kotlin

@Order(2)
@Component
class LoggingFilter : WebFilter {

    companion object {
        val log: Logger = LoggerFactory.getLogger(LoggingFilter::class.java)
    }

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {

        val time = System.currentTimeMillis()
        val requestId = exchange.attributes["requestId"] as String
        val contentLength = exchange.request.headers.contentLength

        if(contentLength < 0) {
            log.info("Request $requestId : method=${exchange.request.method}, uri=${exchange.request.path}")
        }

        return chain.filter(RequestResponseExchange(exchange, requestId, time))
    }

}

class RequestResponseExchange(
    exchange: ServerWebExchange,
    private val requestId: String,
    private val time: Long
): ServerWebExchangeDecorator(exchange) {

    override fun getRequest(): ServerHttpRequest = RequestLoggingDecorator(super.getRequest(), requestId)
    override fun getResponse(): ServerHttpResponse = ResponseLoggingDecorator(super.getResponse(), requestId, time)

}

class RequestLoggingDecorator(
    delegate: ServerHttpRequest,
    private val requestId: String
) : ServerHttpRequestDecorator(delegate) {

    override fun getBody(): Flux<DataBuffer> =
        super.getBody().doOnNext{ buffer ->
            val body = StandardCharsets.UTF_8.decode(buffer.asByteBuffer()).toString()
            LoggingFilter.log.info("Request $requestId : method=${delegate.method}, uri=${delegate.path}, payload=$body")
        }

}

class ResponseLoggingDecorator(
    delegate: ServerHttpResponse,
    private val requestId: String,
    private val time: Long
) : ServerHttpResponseDecorator(delegate) {

    override fun writeWith(body: Publisher<out DataBuffer>): Mono<Void> {
        val buffer = Flux.from(body).doOnNext { dataBuffer ->
            val bodyBuffer = StandardCharsets.UTF_8.decode(dataBuffer.asByteBuffer()).toString()
            LoggingFilter.log.info("Response $requestId : status=${delegate.statusCode}, time=${System.currentTimeMillis()- time}, payload=$bodyBuffer")
        }
        return super.writeWith(buffer)
    }

}

```

### Demo with CURL

    curl -XGET -H "Content-type: application/json" 'http://localhost:8080/api/ping'
    curl -XPOST -H "Content-type: application/json" -d '{ "paymentId" : "2222222","paymentAmount" : 33333 }' 'http://localhost:8080/api/payment'

    2021-11-14 23:02:31.104  INFO 60181 --- [ctor-http-nio-2] com.tirmizee.filters.CleanLoggingFilter  : Request b0a5be22-973f-47ad-bed9-ae4edc592a88 : method=GET, uri=/api/ping, payload=
    2021-11-14 23:02:31.123  INFO 60181 --- [ctor-http-nio-2] com.tirmizee.filters.CleanLoggingFilter  : Response b0a5be22-973f-47ad-bed9-ae4edc592a88 : status=200 OK, method=GET, uri=/api/ping, time=132, payload={"statusCode":"0000","statusDesc":"SUCCESS"}
    2021-11-14 23:02:43.232  INFO 60181 --- [ctor-http-nio-3] com.tirmizee.filters.CleanLoggingFilter  : Request e385c92d-43be-420a-afa0-371deb69a3e2 : method=POST, uri=/api/payment, payload={ "paymentId" : "2222222","paymentAmount" : 33333 }
    2021-11-14 23:02:43.232  INFO 60181 --- [ctor-http-nio-3] com.tirmizee.filters.CleanLoggingFilter  : Response e385c92d-43be-420a-afa0-371deb69a3e2 : status=200 OK, method=POST, uri=/api/payment, time=80, payload={"statusCode":"0000","statusDesc":"SUCCESS"} 