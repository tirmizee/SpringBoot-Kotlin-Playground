

### configmap.yaml

```yaml

apiVersion: v1
kind: ConfigMap
metadata:
  name: spring-pod-comfigmap
data:
  POSTGRES_DB: product
  POSTGRES_USER: product_robot
  PGDATA: /var/lib/postgresql/data/pgdata
---
apiVersion: v1
kind: ConfigMap
metadata:
  name: spring-pod-comfigmap2
data:
  expire: "20"


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
          image: tirmizee/spirng-configmap-env:0.0.2
          ports:
            - containerPort: 8080
          envFrom:
            # get configmap all key
            - configMapRef:
                name: spring-pod-comfigmap
          env:
            # get configmap by key
            - name: EXPIRE
              valueFrom:
                configMapKeyRef:
                  name: spring-pod-comfigmap2
                  key: expire

```

### application.yaml

```yaml

database:
  postgres-db: ${POSTGRES_DB:default}
  postgres-user: ${POSTGRES_USER:default}
  pddata: ${PGDATA:default}
  expire: ${EXPIRE:100}

```

### Dockerfile

   
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

