### dependencies

    implementation("org.springframework.cloud:spring-cloud-starter-gateway")

### application.yaml 

``` yaml

server:
  port: 8080
spring:
  cloud:
    gateway:
      routes:
        - id: service
          uri: http://localhost:8081
          predicates:
            - Path=/service-b/**
          filters:
            - RewritePath=/service-b/(?<segment>.*), /service-b/$\{segment}
            - AddRequestHeader=X-Custom-Owner, Tirmizee

```

### demo

    curl localhost:8080/service-b/sleuth

### Reference

- https://cloud.spring.io/spring-cloud-gateway/2.1.x/multi/multi__gatewayfilter_factories.html