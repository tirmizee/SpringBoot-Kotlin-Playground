
### application.yaml


```yaml

spring:
  cloud:
    stream:
      binders:
        kafka-broker-0:
          type: kafka
          environment.spring.cloud.stream.kafka.binder:
            brokers: localhost:9092
            zkNodes: localhost:2181
            autoAddPartitions: true
        kafka-broker-1:
          type: kafka
          environment.spring.cloud.stream.kafka.binder:
            brokers: localhost:9093
            zkNodes: localhost:2182
            autoAddPartitions: true
      bindings:
        customerConsumer0:
          binder: kafka-broker-0
          group: Customer.Group
          destination: topic.customer
        customerProducer0:
          binder: kafka-broker-0
          destination: topic.customer
          producer:
            configuration:
        customerConsumer1:
          binder: kafka-broker-1
          group: Customer.Group
          destination: topic.customer
        customerProducer1:
          binder: kafka-broker-0
          destination: topic.customer
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
