
### application.yaml

```yaml

web-client:
  base-url: http://0.0.0.0:6060
  headers:
    x-key: "CDERF"
    X-code: "5505"
  all-product-uri: /product/all
  all-product-url: ${base-url}/product/all
  get-product-uri: /product/{id}
  get-product-url: ${base-url}/product/{id}
  create-product-uri: /product
  create-product-url: ${base-url}/product
  update-product-uri: /product/{id}
  update-product-url: ${base-url}/product/{id}

```

### smocker config

```yaml
 
- request:
    method: GET
    path: /product/12
  response:
    status: 200
    body: '{"id":1, "name": "Android", "isActive":true}'
    headers:
      Content-Type: application/json
- request:
    method: GET
    path: /product/all
  response:
    status: 200
    body: |-
      [
          {"id":1, "name": "Android", "isActive":true},
          {"id":2, "name": "IOS", "isActive":true}
      ]
    headers:
      Content-Type: application/json
- request:
    method: POST
    path:
      matcher: ShouldMatch
      value: /product
    body:
      id: 11
      name: IOS
      isActive: true
  response:
    status: 200
    body: '{"code":"000", "message": "Success"}'
    headers:
      Content-Type: application/json
---

```