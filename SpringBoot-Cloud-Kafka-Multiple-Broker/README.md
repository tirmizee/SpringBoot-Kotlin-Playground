
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
