FROM adoptopenjdk/openjdk11:alpine-jre

ARG JAR_FILE=./build/libs/*.jar

WORKDIR /opt/app

# cp *.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar

# port at runtime in docker-container
EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]

# ENTRYPOINT ["java","-Xms128M ", "-Xmx256m","-jar","app.jar"]