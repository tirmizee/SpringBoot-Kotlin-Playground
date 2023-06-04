# SpringBoot-Logging-Level

### Logging 

```java

suspend fun testLogLevel(request: HelloRequest): HelloResponse {
    logger.error("Error message");
    logger.warn("Warning message");
    logger.info("Info message");
    logger.debug("Debug message");
    logger.trace("Trace message");
    return HelloResponse(request.name)
}
    
```

### application.yaml

```yaml

logging.level:
  root: INFO

```

### Demo with CURL

    curl -XPOST -H "Content-type: application/json" -d '{ "name" : "tirmizee" }' 'http://localhost:8080/api/hello'
