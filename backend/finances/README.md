# Realize o import das Collections do Postman através dos arquivos na pasta others/postman/

# Gerando um arquivo JKS com um par de chaves:

##### Alterar ALIAS_NAME para um nome definido, exemplo: finances
##### Alterar ALIAS_PASSWORD para uma senha referente ao ALIAS, exemplo: personal
##### Alterar NOME_ARQUIVO para um nome do arquivo com extensão ".jks", exemplo: finances.jks
##### Alterar KEYSTORE_PASSWORD para uma senha referente ao arquivo com extensão ".jks", exemplo: "123456"

Execute os comandos:
```bash
keytool -genkeypair -alias ALIAS_NAME -keyalg RSA -keypass ALIAS_PASSWORD -keystore NOME_ARQUIVO.jks -storepass KEYSTORE_PASSWORD
```
```bash
keytool -list -keystore NOME_ARQUIVO.jks
```
# Gerando o certificado

##### Alterar ALIAS_NAME para um nome definido, exemplo: personalfinances
##### Alterar NOME_ARQUIVO para um nome definido, exemplo: finances
##### Alterar NOME_ARQUIVO_CERT para um nome definido, exemplo: finances-cert

```bash
keytool -export -rfc -alias ALIAS_NAME -keystore NOME_ARQUIVO.jks -file NOME_ARQUIVO_CERT.pem
```

# Gerando a chave pública

##### Alterar NOME_ARQUIVO_CERT para um nome definido, exemplo: finances-cert
##### Alterar NOME_ARQUIVO_PUB_KEY para um nome definido, exemplo: finances-pkey

```bash
openssl x509 -pubkey -noout -in NOME_ARQUIVO_CERT.pem > NOME_ARQUIVO_PUB_KEY.pem
```

##### Transforme o arquivo.jks em Base 64

```bash
cat NOME_ARQUIV0.jks | base64
```

# Crie variaveis de ambiente para rodar as properties do yaml

```bash
export DATABASE_USER=root
```

```bash
export DATABASE_PASSWORD=root
```

##### Substitua o "CONTEUDO_BASE64_ARQUIVO_JKS" pelo resultado do comando CAT demonstrado acima
```bash
KEY_JKS=base64:CONTEUDO_BASE64_ARQUIVO_JKS
```

```bash
export KEY_ALIAS=personalfinances
```

```bash
export KEY_ALIAS_PASSWORD=personal
```