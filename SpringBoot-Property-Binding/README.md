### dependencies

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

### application.yaml

```yaml

server:
  port: 8080
  address: 127.0.0.1
  compression:
    enabled: true
    mime-types:
      - text/html
      - text/plain
    min-response-size: 2KB
  config:
    ips:
      - 10.10.10.10
      - 10.10.10.11
      - 10.10.10.12
      - 10.10.10.13
    filesystem:
      - /dev/root
      - /dev/md2
      - /dev/md4
  user:
    root:
      username: root
      password: rootpass
    guest:
      username: guest
      password: guestpass
---
web-client:
  gateway:
    gateway-dev:
      url: https://gateway.dev
      headers:
        x-api-key: zaq12wsdkmkwelfml22
        x-source: dev
        x-destination: dev
    gateway-uat:
      url: https://gateway.uat
      headers:
        x-api-key: zaq12wsdkmkwelfml22lm3333
        x-source: uat
        x-destination: uat
  endpoint:
    get-profile:
      gateway: gateway-dev
      path: /v1/user/profile
      headers: {}
    inquiry:
      gateway: gateway-uat
      path: /v1/inquiry
      headers: {}

```

### ServerProperty

```kotlin

@ConstructorBinding
@ConfigurationProperties(prefix = "server")
data class ServerProperty(
    val port: Int,
    val address: String,
    val compression: CompressionProperty,
    val config: Map<String, List<String>>,
    val user: Map<String, CredentialProperty>
) {

    data class CredentialProperty(
        val username: String,
        val password: String
    )

    data class CompressionProperty(
        val enabled: Boolean,
        val minResponseSize: String,
        val mimeTypes: List<String>
    )

}

```

### WebClientProperty

```kotlin

@ConstructorBinding
@ConfigurationProperties(prefix = "web-client")
data class WebClientProperty (
    val gateway: Map<String, GatewayProperty>,
    val endpoint: EndpointProperty
) {

    data class GatewayProperty(
        val url: String,
        val headers: Map<String, String>
    )

    data class EndpointProperty(
        val inquiry: EndpointDetailProperty,
        val getProfile: EndpointDetailProperty,
    )

    data class EndpointDetailProperty(
        val path: String,
        val gateway: String,
        val headers: Map<String, String>
    )

}

```

### Reference

- https://www.baeldung.com/spring-yaml-inject-map
- https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#application-properties.integration
