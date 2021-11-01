## SpringBoot-SFTP-SharedSession

### dependencies

    implementation("org.springframework.integration:spring-integration-sftp")

### docker-compose.yaml

```yaml

version: '3'
services:
  sftp:
    image: atmoz/sftp
    ports:
      - '2222:22'
    volumes:
      - /Users/pratya.yeekhaday/Desktop/volume:/home/user/upload
    command: user:pass


```

### application.yaml

```yaml

sftp:
  client:
    host: 0.0.0.0
    port: 2222
    username: user
    password: pass
    time-out: 10000
    allow-unknow-key: true
    shared-session: true
logging:
  level:
    org.springframework.integration.sftp: TRACE
    com.jcraft.jsch: DEBUG



```

### demo functions

```kotlin

interface SftpService {

    fun uploadFile()

    fun downloadFile()

    fun listEntries()

    fun listNames()

}

```
