
### Dockerfile 

    FROM adoptopenjdk/openjdk11:alpine-jre

    WORKDIR /opt/app

    ARG JAR_FILE=./build/libs/*.jar

    # cp *.jar /opt/app/app.jar
    COPY ${JAR_FILE} app.jar

    # port at runtime in docker-container
    EXPOSE 8080

    ENTRYPOINT ["java","-jar","app.jar"]
