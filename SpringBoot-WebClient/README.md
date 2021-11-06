
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

### get method demo

```kotlin

    // web-flux
    fun getProduct(id: Int): Result<GetProductResponse?> =
        webClient.runCatching {
            this.get()
                .uri(webClientProperty.getProductUri.replace("{id}", id.toString()))
                .headers{ webClientProperty.headers }
                .retrieve()
                .bodyToMono(GetProductResponse::class.java)
                .block()
        }.onSuccess { response ->
            response
        }.onFailure {}

    // coroutine
    suspend fun getProduct(id: Int): Result<GetProductResponse?> =
        webClient.runCatching {
            this.get()
                .uri(webClientProperty.getProductUri.replace("{id}", id.toString()))
                .headers{ webClientProperty.headers }
                .retrieve()
                .bodyToMono(GetProductResponse::class.java)
                .awaitSingle()
        }.onSuccess { response ->
            response
        }.onFailure {}
    

```

### post method demo

```kotlin

    // web-flux
    fun createProduct(createProductRequest: CreateProductRequest): Result<CreateProductResponse?> =
        webClient.runCatching {
            this.post()
                .uri(webClientProperty.createProductUri)
                .contentType(MediaType.APPLICATION_JSON)
                .headers{ webClientProperty.headers }
                .bodyValue(createProductRequest)
                .retrieve()
                .bodyToMono(CreateProductResponse::class.java)
                .block()
        }.onSuccess { response ->
            response
        }.onFailure {}

    // coroutine
    suspend fun createProduct(createProductRequest: CreateProductRequest): Result<CreateProductResponse?> =
        webClient.runCatching {
            this.post()
                .uri(webClientProperty.createProductUri)
                .contentType(MediaType.APPLICATION_JSON)
                .headers{ webClientProperty.headers }
                .bodyValue(createProductRequest)
                .retrieve()
                .bodyToMono(CreateProductResponse::class.java)
                .awaitSingle()
        }.onSuccess { response ->
            response
        }.onFailure {}

```

### extension web-client demo

```kotlin

    inline fun <reified T: Any>WebClient.get(uri: URI, headers: MultiValueMap<*,*> = HttpHeaders()) : T? =
        this.get()
            .uri(uri)
            .headers { headers }
            .retrieve()
            .bodyToMono(T::class.java)
            .block()
    
    inline fun <reified T: Any>WebClient.post(uri: URI, headers: MultiValueMap<*,*> = HttpHeaders()) : T? =
        this.post()
            .uri(uri)
            .headers { headers }
            .contentType(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(T::class.java)
            .block()
    
    inline fun <reified S: Any, reified T: Any>WebClient.post(uri: URI, request: S, headers: MultiValueMap<*,*> = HttpHeaders()) : T? =
        this.post()
            .uri(uri)
            .headers { headers }
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(request)
            .retrieve()
            .bodyToMono(T::class.java)
            .block()

```