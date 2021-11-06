
### dependencies

    implementation("org.springframework.cloud:spring-cloud-starter-gateway")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

### docker-compose.yaml

```yaml

version: '3.3'
services:
  smocker-1:
    container_name: smocker-1
    image: thiht/smocker
    ports:
      - '6060:8080'
      - '6061:8081'
  smocker-2:
    container_name: smocker-2
    image: thiht/smocker
    ports:
      - '7070:8080'
      - '7071:8081'


```

### application.yaml

```yaml

server:
  port: 8080
spring:
  cloud:
    gateway:
      routes:
        - id: customer-service
          uri: http://0.0.0.0:6060
          predicates:
            - Path=/customer-service/**
          filters:
            - RewritePath=/customer-service/(?<segment>.*), /customer-service/$\{segment}
        - id: product-service
          uri: http://0.0.0.0:7070
          predicates:
            - Path=/product-service/**
          filters:
            - RewritePath=/product-service/(?<segment>.*), /$\{segment}
management:
  endpoint:
    gateway:
      enabled: true
  endpoints:
    web:
      exposure:
        include: gateway

# http://0.0.0.0:8080/actuator/gateway/routes
# http://0.0.0.0:8080/actuator/gateway/routes/{id}

```

### demo

    http://0.0.0.0:8080/customer-service/get
    http://0.0.0.0:8080/customer-service/all

    http://0.0.0.0:8080/product-service/get
    http://0.0.0.0:8080/product-service/all
    

### Reference

- https://cloud.spring.io/spring-cloud-gateway/reference/html/