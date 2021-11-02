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
