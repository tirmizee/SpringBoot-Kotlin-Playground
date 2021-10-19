### Create YAML secret from kubectl commands 

    kubectl create secret generic secret-data --from-file ./secret-data/config-1.json --from-file ./secret-data/config-2.properties --dry-run=client -o yaml > secret-data.yaml

### secret-data.yaml

