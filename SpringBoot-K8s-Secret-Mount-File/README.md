### create YAML secret from commands 

    kubectl create secret generic secret-data --from-file ./secret-data/config-1.json --from-file ./secret-data/config-2.properties --dry-run=client -o yaml > secret-data.yaml

### secret-data.yaml

```yaml

apiVersion: v1
kind: Secret
metadata:
  creationTimestamp: null
  name: secret-data
data:
  config-1.json: ewogICJ1c2VybmFtZSIgOiAicm9vdCIsCiAgInBhc3N3b3JkIiA6ICJ6YXExMndzeCIKfQ==
  config-2.properties: dXNlcm5hbWU9cm9vdApwYXNzd29yZD16YXExMndzeA==

```

### deployment.yaml

```yaml

apiVersion: apps/v1
kind: Deployment
metadata:
  name: spring-secret-mount-pod
spec:
  replicas: 1
  selector:
    matchLabels:
      app: spring-secret-mount-pod
  template:
    metadata:
      labels:
        app: spring-secret-mount-pod
    spec:
      containers:
        - name: spring-secret-mount-pod
          image: tirmizee/spring-secret-mount:0.0.1
          ports:
            - containerPort: 8080
          volumeMounts:
            - name: secret-volume
              readOnly: true
              mountPath: "/config"
      volumes:
        - name: secret-volume
          secret:
            secretName: secret-data

```
