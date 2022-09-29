# SpringBoot-Cloud-Kafka-Delay


### application.yaml

```yaml

spring:
  cloud:
    stream:
      kafka:
        bindings:
          customerConsumer:
            consumer:
              configuration:
                max.poll.interval.ms: 10000
      binders:
        kafka-broker:
          type: kafka
          environment.spring.cloud.stream.kafka.binder:
            brokers: localhost:9092
            autoAddPartitions: true
            autoCreateTopics: true
      bindings:
        customerConsumer:
          binder: kafka-broker
          group: Customer.Group
          destination: topic.customer
        customerProducer:
          binder: kafka-broker
          destination: topic.customer


```

```kotlin

@Component
class Handler(
    private val streamChannels: StreamChannels
) {

    suspend fun produce(serverRequest: ServerRequest): ServerResponse {
        val payload = "hello world broker"
        val messageHeader = MessageHeaders(Collections.singletonMap<String, Any>("X-Kafka-Retry", 0))
        val message = MessageBuilder.createMessage(payload, messageHeader)
        streamChannels.customerProducer().send(message)
        return ok().bodyValueAndAwait("success")
    }
}

@Component
class CustomerListener(
    private val streamChannels: StreamChannels
) {

    @StreamListener(StreamChannels.CUSTOMER_CONSUMER)
    fun customerListener(
        message: Message<String>,
        @Header(RECEIVED_TOPIC) topic: String,
        @Header("X-Kafka-Retry") retry: Int
    ) {
        runBlocking {
            if (retry == 0) {
                println("started ${message.payload} $topic $retry")
                println("ended ${message.payload} $topic $retry")
            } else if (retry <= 5) {
                delay(5000)
                println("started ${message.payload} $topic $retry")
                try {
                    throw NullPointerException()
                } catch (e: Exception) {
                    val payload = "hello world broker"
                    val messageHeader = MessageHeaders(Collections.singletonMap<String, Any>("X-Kafka-Retry", retry + 1))
                    val message = MessageBuilder.createMessage(payload, messageHeader)
                    streamChannels.customerProducer().send(message)
                }
            }
        }
    }
}

```