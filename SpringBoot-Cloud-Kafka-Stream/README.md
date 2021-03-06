### Spring cloud Stream Kafka


### run docker-compose 


    version: "3"
    services:
      zookeeper:
        image: 'bitnami/zookeeper:latest'
        ports:
          - '2181:2181'
        environment:
          - ALLOW_ANONYMOUS_LOGIN=yes
      kafka:
        image: 'bitnami/kafka:latest'
        ports:
          - '9092:9092'
        environment:
          - KAFKA_BROKER_ID=1
          - KAFKA_CFG_LISTENERS=PLAINTEXT://:9092
          - KAFKA_CFG_ADVERTISED_LISTENERS=PLAINTEXT://127.0.0.1:9092
          - KAFKA_CFG_ZOOKEEPER_CONNECT=zookeeper:2181
          - ALLOW_PLAINTEXT_LISTENER=yes
        depends_on:
          - zookeeper


### application.yaml

    server:
      port: 8080
    ---
    spring:
      cloud:
        stream:
          kafka:
            binder:
              autoCreateTopics: true
              brokers:
                - localhost:9092
            configuration:
          bindings:
            customerProducer:
              destination: topic.customer.0
              producer:
                configuration:
            customerConsumer:
              destination: topic.customer.0
              group: Customer.Group.0
              consumer:
                configuration:
            notificationProducer:
              destination: notification.customer.0
              producer:
                configuration:
            notificationConsumer:
              destination: notification.customer.0
              group: Notification.Group.0
              consumer:
                configuration:


#### @EnableBinding

สำหรับกำหนดค่าพื่อผูก INPUT และ OUTPUT ที่กำหนดไว้ภายใน Interface

### Reference

- https://cloud.spring.io/spring-cloud-stream-binder-kafka/spring-cloud-stream-binder-kafka.html
