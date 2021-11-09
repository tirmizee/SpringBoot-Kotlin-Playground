### Generate key with keytool

    keytool -genkey -alias server -keyalg RSA -keysize 2048 -keystore server.jks -storetype JKS -storepass storepass -keypass keypass -dname "CN=192.168.1.100,OU=test, O=test, L=test, ST=test, C=TH"

### Lists entries in a keystore

    # -v  verbose output
    # -rfc output in RFC style
    keytool -list -v -keystore server.jks -storepass storepass

### Export public key to file

    # rfc format
    keytool -export -alias server -keystore server.jks -storepass storepass -file certificate.pem -rfc 

    # der format
    keytool -export -alias server -keystore server.jks -storepass storepass -file certificate.der