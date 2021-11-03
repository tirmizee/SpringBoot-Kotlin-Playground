## SFTP Inbound Channel

- <b>local-directory</b> ตำแหน่งที่จะโอนไฟล์ไปยัง local
- <b>remote-directory</b> remote directory ต้นทางที่จะถ่ายโอนไฟล์ไปยัง local
- <b>session-factory</b> remote connection ที่ตั้งค่าไว้ (ip, port, user, pass)

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
### configuration

```kotlin

@Bean
fun sessionFactory(): SessionFactory<LsEntry?>? {
    val sftpSessionFactory = DefaultSftpSessionFactory(sftpProperty.client.sharedSession)
    sftpSessionFactory.setHost(sftpProperty.client.host)
    sftpSessionFactory.setPort(sftpProperty.client.port)
    sftpSessionFactory.setUser(sftpProperty.client.username)
    sftpSessionFactory.setPassword(sftpProperty.client.password)
    sftpSessionFactory.setTimeout(sftpProperty.client.timeOut)
    sftpSessionFactory.setAllowUnknownKeys(sftpProperty.client.allowUnknowKey)
    return sftpSessionFactory
}

@Bean
fun cachingSessionFactory(sessionFactory: SessionFactory<LsEntry>?): CachingSessionFactory<LsEntry>? {
    val cachingSessionFactory = CachingSessionFactory(sessionFactory)
    cachingSessionFactory.setPoolSize(sftpProperty.client.cache.poolSize)
    cachingSessionFactory.setTestSession(sftpProperty.client.cache.testSession)
    cachingSessionFactory.setSessionWaitTimeout(sftpProperty.client.cache.sessionWaitTimeout)
    return cachingSessionFactory
}

@Bean
fun sftpInboundFlow(@Qualifier("cachingSessionFactory") cachingSessionFactory: CachingSessionFactory<LsEntry>): IntegrationFlow? {
    return IntegrationFlows
        .from(
            Sftp.inboundAdapter(cachingSessionFactory)
                .preserveTimestamp(true)
                .remoteDirectory(sftpProperty.inbound.remoteDirectory)
                .deleteRemoteFiles(sftpProperty.inbound.remoteFilesDelete)
                .filter(SftpSimplePatternFileListFilter(sftpProperty.inbound.remoteFilesFilter))
                .autoCreateLocalDirectory(sftpProperty.inbound.localDirectoryAutoCreate)
                .localDirectory(File(sftpProperty.inbound.localDirectory))
        ) { e: SourcePollingChannelAdapterSpec ->
            e.id("sftpInboundAdapter")
                .autoStartup(true)
                .poller(Pollers.fixedDelay(3000))
        }
        .handle { message: Message<*> -> println(message.payload) }
        .get()
}

```