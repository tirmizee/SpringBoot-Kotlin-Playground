### Convert certificate.pem (base64) to DER format (binary)

    openssl x509 -outform der -in certificate.pem -out certificate.der 

### Import the certificate into the trust store

    keytool -importcert -alias local-ssl -keystore truststore.jks -file certificate.der -storepass storepass -noprompt -storetype JKS

    keytool -import -trustcacerts -noprompt -alias ca -ext san=dns:localhost,ip:127.0.0.1 -file certificate.der -keystore truststore.jks -storepass storepass -storetype JKS

### Configuration

```kotlin

@Bean(name = ["webClient"])
fun webClient() : WebClient =
    WebClient.builder()
        .clientConnector(ReactorClientHttpConnector(httpClient()))
        .build()


@Bean(name = ["httpClient"])
fun httpClient(): HttpClient =
    HttpClient
        .create()
        .secure{ spec -> spec.sslContext(sslContext()) }


@Bean(name = ["sslContext"])
fun sslContext(): SslContext =
    SslContextBuilder
        .forClient()
//            .trustManager(trustManagerFactory())
//            .trustManager(x509Certificate())
        .trustManager(x509InputStream())
        .build()

private fun trustManagerFactory(): TrustManagerFactory {
    val trustAlgorithm = TrustManagerFactory.getDefaultAlgorithm()
    val trustManagerFactory: TrustManagerFactory = TrustManagerFactory.getInstance(trustAlgorithm).apply {
        val trustStoreFile = ClassPathResource(serverProperty.trustStore).inputStream
        val trustStore = KeyStore.getInstance(serverProperty.trustStoreType).apply {
            this.load(trustStoreFile, serverProperty.trustStorePassword.toCharArray())
        }
        this.init(trustStore)
    }

    return trustManagerFactory
}

private fun x509Certificate(): X509Certificate {
    val certificateFactory = CertificateFactory.getInstance("X.509")
    val x509File = ClassPathResource(serverProperty.trustStorePem).inputStream
    return certificateFactory.generateCertificate(x509File) as X509Certificate
}

private fun x509InputStream(): InputStream =
    ClassPathResource(serverProperty.trustStorePem).inputStream

```