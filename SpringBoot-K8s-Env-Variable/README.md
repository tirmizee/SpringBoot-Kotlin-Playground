
### Dockerfile 

    FROM adoptopenjdk/openjdk11:alpine-jre

    WORKDIR /opt/app

    ARG JAR_FILE=./build/libs/*.jar

    # cp *.jar /opt/app/app.jar
    COPY ${JAR_FILE} app.jar

    # port at runtime in docker-container
    EXPOSE 8080

    ENTRYPOINT ["java","-jar","app.jar"]
    
### deployment.yaml

```yaml

apiVersion: apps/v1
kind: Deployment
metadata:
  name: spring-app-pod
spec:
  selector:
    matchLabels:
      app: spring-app-pod
  replicas: 1
  template:
    metadata:
      labels:
        app: spring-app-pod
    spec:
      containers:
        - name: spring-app
          image: tirmizee/spring-env:0.0.1
          ports:
            - containerPort: 8080
          env:
            - name: SERVICE_PORT
              value: "80"
            - name: SERVICE_IP
              value: "172.17.0.1"
            - name: PROTOCOL
              value: "https"
            - name: SERVICE_ADDRESS
              value: "$(PROTOCOL)://$(SERVICE_IP):$(SERVICE_PORT)"
---
apiVersion: v1
kind: Service
metadata:
  name: spring-app-service
spec:
  type: ClusterIP
  selector:
    app: spring-app-pod
  ports:
    - protocol: TCP
      port: 8080
      targetPort: 8080

```
