
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