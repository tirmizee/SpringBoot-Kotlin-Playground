### Dockerfile

    FROM adoptopenjdk/openjdk11:alpine-jre

    ARG JAR_FILE=./build/libs/*.jar

    WORKDIR /opt/app

    # cp *.jar /opt/app/app.jar
    COPY ${JAR_FILE} app.jar

    # port at runtime in docker-container
    EXPOSE 8080

    ENTRYPOINT ["java","-jar","app.jar"]

    # ENTRYPOINT ["java","-Xms128M ", "-Xmx256m","-jar","app.jar"]

### deployment1.yaml

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
          image: tirmizee/spring-deployment:0.0.2
          ports:
            - containerPort: 8080
          env:
            # tomcat or ant
            - name: JAVA_OPTS
              value: "-Xms256m -Xmx512m"
            # linux or window
            - name: _JAVA_OPTIONS
              value: "-Xms256m -Xmx512m"

```

### deployment2.yaml

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
        - name: spring-container
          image: tirmizee/spring-deployment:0.0.2
          ports:
            - containerPort: 8080
          resources:
            requests:
#              memory: "128Mi"
              cpu: "0.25"
            limits:
#              memory: "256Mi"
              cpu: "0.50"
              
```

### demo

```kotlin

@SpringBootApplication
class SpringBootK8sDeploymentApplication : CommandLineRunner {
	override fun run(vararg args: String?) {
		println("MAX MEMORY ${Runtime.getRuntime().maxMemory()}")
		println("FREE MEMORY ${Runtime.getRuntime().freeMemory()}")
		println("USE MEMORY ${Runtime.getRuntime().maxMemory() - Runtime.getRuntime().freeMemory()}")
	}
}

fun main(args: Array<String>) {
	runApplication<SpringBootK8sDeploymentApplication>(*args)
}

```
