### Dockerfile

    FROM adoptopenjdk/openjdk11:alpine-jre

    WORKDIR /opt/app

    ARG JAR_FILE=./build/libs/*.jar

    # cp *.jar /opt/app/app.jar
    COPY ${JAR_FILE} app.jar

    # port at runtime in docker-container
    EXPOSE 8080

    ENTRYPOINT ["java","-jar","app.jar","--spring.config.location=file:/config/application.yaml"]


### secret.yaml

```yaml

apiVersion: v1
kind: Secret
metadata:
  name: db-secret
type: Opaque
data:
  username: cm9vdA==
  password: emFxMTJ3c3g=

```

### configmap.yaml

```yaml

apiVersion: v1
kind: ConfigMap
metadata:
  name: spring-pod-configmap
data:
  application.yaml: |+
    server:
      port: 8080
    ---
    database:
      url: 10.5.0.xxxx
      username: ${DB_USERNAME:default}
      password: ${DB_PASSWORD:default}

```

### deployment.yaml

```yaml

apiVersion: apps/v1
kind: Deployment
metadata:
  name: spring-pod
spec:
  replicas: 1
  selector:
    matchLabels:
      app: spring-pod
  template:
    metadata:
      labels:
        app: spring-pod
    spec:
      containers:
        - name: spring-pod
          image: tirmizee/spring-configmap-mount:0.0.2
          ports:
            - containerPort: 8080
          env:
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
          volumeMounts:
            - name: config-volume
              mountPath: /config
      volumes:
        - name: config-volume
          configMap:
            name: spring-pod-configmap

```
