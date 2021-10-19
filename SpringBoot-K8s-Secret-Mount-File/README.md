### Create YAML secret from commands 

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

```
