
### Dockerfile

    FROM adoptopenjdk/openjdk11:alpine-jre

    WORKDIR /opt/app

    ARG JAR_FILE=./build/libs/*.jar

    # cp *.jar /opt/app/app.jar
    COPY ${JAR_FILE} app.jar

    # port at runtime in docker-container
    EXPOSE 8080

    ENTRYPOINT ["java","-jar","app.jar"]


### secret.yaml

```yaml

apiVersion: v1
kind: Secret
metadata:
  name: db-secret
type: Opaque
data:
  url: MTkyLjE2OC4wLjEx
  username: cm9vdA==
  password: emFxMTJ3c3g=
  
```

### deployment.yaml

```yaml

apiVersion: apps/v1
kind: Deployment
metadata:
  name: spring-secret-pod
spec:
  selector:
    matchLabels:
      app: spring-secret-pod
  replicas: 1
  template:
    metadata:
      labels:
        app: spring-secret-pod
    spec:
      containers:
        - name: spring-secre-container
          image: tirmizee/spring-secret:0.0.1
          ports:
            - containerPort: 8080
          env:
            - name: DB_URL
              valueFrom:
                secretKeyRef:
                  name: db-secret
                  key: url
            - name: DB_USERNAME
              valueFrom:
                secretKeyRef:
                  name: db-secret
                  key: username
            - name: DB_PASSWORD
              valueFrom:
                secretKeyRef:
                  name: db-secret
                  key: password
```

### application.yaml

```yaml

database:
  url: ${DB_URL:default}
  username: ${DB_USERNAME:default}
  password: ${DB_PASSWORD:default}
  
```
