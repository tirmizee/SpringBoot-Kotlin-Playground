# SpringBoot-WebClient-ConnectionPool

### configurations properties

- <b>maxConnections</b> จำนวนการเชื่อมต่อสูงสุด
- <b>maxIdleTime</b> เวลาหลังจากที่แชนเนลจะถูกปิดเมื่อไม่ได้ใช้งาน ค่าเริ่มต้น: ไม่ได้ระบุ
- <b>maxLifeTime</b> อายุการใช้งานหลังจากนั้นช่องจะถูกปิด ค่าเริ่มต้น: ไม่ได้ระบุ
- <b>evictInBackground</b> เมื่อเปิดใช้งานตัวเลือกนี้ แต่ละ connection pool จะตรวจสอบการเชื่อมต่อที่เข้าเกณฑ์สำหรับการลบออก เช่น maxIdleTime ตามค่าเริ่มต้น ปิดใช้งาน

### configurations

```kotlin

    @Bean(name = ["webClient"])
    fun webClient(): WebClient {
        val provider = ConnectionProvider
            .builder("custom")
            .maxConnections(500)
            .maxLifeTime(Duration.ofMinutes(15))
            .maxIdleTime(Duration.ofMinutes(10))
            .build()
        val httpClient = HttpClient.create(provider)
        val httpConnector = ReactorClientHttpConnector(httpClient)
        return WebClient
            .builder()
            .clientConnector(httpConnector)
            .build()
    }
```

## Reference

- https://stackoverflow.com/questions/64810671/spring-boot-random-sslexception-connection-reset-in-kubernetes-with-jdk11
- https://github.com/reactor/reactor-netty/issues/1774?fbclid=IwAR2GUPSZR0QRFlQbGfU_-LY5N_vy12b8P-dR3cBpq4ee23oCtxFWJGlqRm0
- https://projectreactor.io/docs/netty/release/reference/index.html#_connection_pool_2