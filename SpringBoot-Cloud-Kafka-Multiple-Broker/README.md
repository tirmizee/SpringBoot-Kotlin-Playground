
### StreamChannel.kt

```kotlin

interface StreamChannels {

    companion object {
        const val CUSTOMER_INPUT_BROKER_0 = "customerConsumer0"
        const val CUSTOMER_OUTPUT_BROKER_0 = "customerProducer0"
        const val CUSTOMER_INPUT_BROKER_1 = "customerConsumer1"
        const val CUSTOMER_OUTPUT_BROKER_1 = "customerProducer1"
    }

    @Input(CUSTOMER_INPUT_BROKER_0)
    fun customerConsumerBroker0(): SubscribableChannel

    @Output(CUSTOMER_OUTPUT_BROKER_0)
    fun customerProducerBroker0(): MessageChannel

    @Input(CUSTOMER_INPUT_BROKER_1)
    fun customerConsumerBroker1(): SubscribableChannel

    @Output(CUSTOMER_OUTPUT_BROKER_1)
    fun customerProducerBroker1(): MessageChannel

}

```

### application.yaml


```yaml

spring:
  cloud:
    stream:
      binders:
        # broker one
        kafka-broker-0:
          type: kafka
          environment.spring.cloud.stream.kafka.binder:
            brokers: localhost:9092
            # zkNodes: localhost:2181 deprecated since 2.0
            autoAddPartitions: true
            autoCreateTopics: true
        # broker two
        kafka-broker-1:
          type: kafka
          environment.spring.cloud.stream.kafka.binder:
            brokers: localhost:9093
            # zkNodes: localhost:2182 deprecated since 2.0
            autoAddPartitions: true
            autoCreateTopics: true
      bindings:
        customerConsumer0:
          binder: kafka-broker-0
          group: Customer.Group
          destination: topic.customer.0
        customerProducer0:
          binder: kafka-broker-0
          destination: topic.customer.0
          producer:
            configuration:
        customerConsumer1:
          binder: kafka-broker-1
          group: Customer.Group
          destination: topic.customer.1
        customerProducer1:
          binder: kafka-broker-1
          destination: topic.customer.1
          producer:
            configuration:
  
```

### docker-compose.yaml

```yaml

version: "3"
services:
  zookeeper-0:
    image: 'bitnami/zookeeper:latest'
    environment:
      - ALLOW_ANONYMOUS_LOGIN=yes
  zookeeper-1:
    image: 'bitnami/zookeeper:latest'
    environment:
      - ALLOW_ANONYMOUS_LOGIN=yes
  kafka-0:
    image: 'bitnami/kafka:latest'
    ports:
      - '9092:9092'
    environment:
      - KAFKA_BROKER_ID=1
      - KAFKA_CFG_LISTENERS=PLAINTEXT://:9092
      - KAFKA_CFG_ADVERTISED_LISTENERS=PLAINTEXT://127.0.0.1:9092
      - KAFKA_CFG_ZOOKEEPER_CONNECT=zookeeper-0:2181
      - ALLOW_PLAINTEXT_LISTENER=yes
    depends_on:
      - zookeeper-0
  kafka-1:
    image: 'bitnami/kafka:latest'
    ports:
      - '9093:9092'
    environment:
      - KAFKA_BROKER_ID=1
      - KAFKA_CFG_LISTENERS=PLAINTEXT://:9092
      - KAFKA_CFG_ADVERTISED_LISTENERS=PLAINTEXT://127.0.0.1:9092
      - KAFKA_CFG_ZOOKEEPER_CONNECT=zookeeper-1:2181
      - ALLOW_PLAINTEXT_LISTENER=yes
    depends_on:
      - zookeeper-1

```
