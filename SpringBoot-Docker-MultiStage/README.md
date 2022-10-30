# SpringBoot-Docker-MultiStage

If you build Jar file outside of Image, this is not required.

### Dockerfile single stage

```

FROM adoptopenjdk/openjdk11-openj9:alpine-slim
WORKDIR /opt/app
COPY gradlew *.kts .
COPY gradle ./gradle
COPY src ./src
RUN ./gradlew clean build
ENTRYPOINT ["./gradlew", "bootRun"]

```

### Dockerfile multi stage

```

# stage 0 or stage builder
FROM adoptopenjdk/openjdk11-openj9:alpine-slim as builder
WORKDIR /opt/app
COPY gradlew *.kts .
COPY gradle ./gradle
COPY src ./src
RUN ./gradlew clean build

# stage 1
FROM adoptopenjdk/openjdk11-openj9:alpine-slim
WORKDIR /opt/app
COPY --from=builder /opt/app/build/libs/*SNAPSHOT.jar /opt/app/app.jar
ENTRYPOINT ["java", "-jar", "./app.jar"]

```