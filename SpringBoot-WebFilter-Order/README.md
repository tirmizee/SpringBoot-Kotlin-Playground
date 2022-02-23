
- Lower values have higher priority

### Demo

```kotlin


@Order(3)
@Component
class FilterOrder1: WebFilter {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        logger.info("FilterOrder1")
        return chain.filter(exchange)
    }

}

@Order(2)
@Component
class FilterOrder2 :WebFilter {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        logger.info("FilterOrder2")
        return chain.filter(exchange)
    }

}

@Order(1)
@Component
class FilterOrder3 : WebFilter {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        logger.info("FilterOrder3")
        return chain.filter(exchange)
    }

}

```
        curl localhost:8080/

        2022-02-24 03:42:31.273  INFO 12635 --- [ctor-http-nio-2] com.tirmizee.filter.FilterOrder3         : FilterOrder3
        2022-02-24 03:42:31.274  INFO 12635 --- [ctor-http-nio-2] com.tirmizee.filter.FilterOrder2         : FilterOrder2
        2022-02-24 03:42:31.275  INFO 12635 --- [ctor-http-nio-2] com.tirmizee.filter.FilterOrder1         : FilterOrder1