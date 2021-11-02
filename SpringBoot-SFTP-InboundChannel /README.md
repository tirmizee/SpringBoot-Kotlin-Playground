## SFTP Inbound Channel

- <b>local-directory</b> ตำแหน่งที่จะโอนไฟล์ไปยัง local
- remote-directory remote directory ต้นทางที่จะถ่ายโอนไฟล์ไปยัง local
- session-factory remote connection ที่ตั้งค่าไว้(ip, port, user, pass)

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
      - /Users/pratya.yeekhaday/Desktop/volume:/home/user/remote
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
    cache:
      pool-size: 5
      test-session: true
      session-wait-timeout: 30000
  inbound:
    remoteDirectory: /remote
    remoteFilesFilter: "*.txt"
    remoteFilesDelete: true
    localDirectory: /Users/pratya.yeekhaday/Desktop/local
    localDirectoryAutoCreate: true
logging:
  level:
    org.springframework.integration.sftp: TRACE
    com.jcraft.jsch: DEBUG

```
