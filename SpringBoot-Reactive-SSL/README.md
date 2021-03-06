### Generate key with keytool

    keytool -genkeypair -alias server -keyalg RSA -keysize 2048 -keystore server.jks -storetype JKS -storepass storepass -keypass keypass -dname "CN=privatedomain,OU=test, O=test, L=test, ST=test, C=TH" 

### Lists entries in a keystore

    # -v  verbose output
    # -rfc output in RFC style
    keytool -list -v -keystore server.jks -storepass storepass

### Export public key to file

    # rfc format
    keytool -export -alias server -keystore server.jks -storepass storepass -file certificate.pem -rfc 

    # der format
    keytool -export -alias server -keystore server.jks -storepass storepass -file certificate.der

### application.yaml

```yaml

server:
  port: 8443
  ssl:
    key-store: classpath:keystore/server.jks
    key-store-password: storepass
    key-password: keypass
    key-alias: server
    key-store-type: JKS

```