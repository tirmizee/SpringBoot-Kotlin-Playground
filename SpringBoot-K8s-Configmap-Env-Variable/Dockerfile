FROM adoptopenjdk/openjdk11:alpine-jre

# define variables
ARG JAR_FILE=./build/libs/*.jar

# workspace location
WORKDIR /opt/app

# cp *.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar

# port at runtime in docker-container
EXPOSE 8080

# commands
ENTRYPOINT ["java","-jar","app.jar"]