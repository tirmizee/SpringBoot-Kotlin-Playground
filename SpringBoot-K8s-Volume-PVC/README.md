### Dockerfile

    FROM adoptopenjdk/openjdk11:alpine-jre

    ARG JAR_FILE=./build/libs/*.jar

    WORKDIR /opt/app

    # cp *.jar /opt/app/app.jar
    COPY ${JAR_FILE} app.jar

    # port at runtime in docker-container
    EXPOSE 8080

    ENTRYPOINT ["java","-jar","app.jar"]

### persistent-volume.yaml

```yaml

apiVersion: v1
kind: PersistentVolume
metadata:
  name: host-pv
spec:
  capacity:
    storage: 50Mi
  volumeMode: Filesystem
  storageClassName: manual
  accessModes:
    - ReadWriteOnce
  hostPath:
    path: /data
---
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: host-pvc
spec:
  storageClassName: manual
  accessModes:
    - ReadWriteOnce
  resources:
    requests:
      storage: 40Mi

```

### deployment.yaml

```yaml

apiVersion: apps/v1
kind: Deployment
metadata:
  name: spring-pvc-pod
spec:
  replicas: 1
  selector:
    matchLabels:
      app: spring-pvc-pod
  template:
    metadata:
      labels:
        app: spring-pvc-pod
    spec:
      containers:
        - name: spring-pvc-container
          image: tirmizee/spring-pvc:0.0.1
          volumeMounts:
            - name: pvc-volume
              mountPath: /data
      volumes:
        - name: pvc-volume
          persistentVolumeClaim:
            claimName: host-pvc

```
