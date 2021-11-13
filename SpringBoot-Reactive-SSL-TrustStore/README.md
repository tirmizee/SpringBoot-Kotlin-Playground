
### 1. Convert certificate.pem (base64) to DER format (binary)

    openssl x509 -outform der -in certificate.pem -out certificate.der 

### 2. Validate the certificate content

    keytool -v -printcert -file certificate.der

### 3. Import the certificate into the trust store

    keytool -importcert -alias local-ssl -keystore truststore.jks -file certificate.der -storepass storepass -noprompt -storetype JKS

    keytool -import -trustcacerts -noprompt -alias ca -ext san=dns:localhost,ip:127.0.0.1 -file certificate.der -keystore truststore.jks -storepass storepass -storetype JKS

### 4. Verify that the certificate has been imported

    keytool -keystore truststore.jks -storepass storepass -list

    -Djavax.net.ssl.trustStore=/Users/pratya.yeekhaday/Desktop/SpringBoot-Kotlin-Playground/SpringBoot-Reactive-SSL-TrustStore/src/main/resources/store/truststore.jks -Djavax.net.ssl.trustStorePassword=storepass -Djavax.net.ssl.trustStoreType=JKS