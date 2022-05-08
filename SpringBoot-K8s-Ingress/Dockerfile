FROM openjdk:11-jre-slim

WORKDIR /app

ADD build/libs/*SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app/app.jar"]