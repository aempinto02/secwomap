# SecWomap project

**SecWomap** surgiu como uma solução para auxiliar **_mulheres_** e **_pessoas que sofram com preconceito de gênero_**, de um _modo colaborativo_, ou seja, uma pessoa que sofreu algum tipo de assédio, incômodo ou sujeição a algum evento que tenha um prejuízo emocional ou até financeiro para a pessoa, ela poderá reportar nesse *WebService* onde isso aconteceu (pelo endereço completo ou quando houver o aplicativo mobile pela localização georreferenciada por latitude e longitude) e com auxílio da **API do Google Maps** se for inserido pelo endereço, ela irá descobrir a localização geográfica, e se for pela localização geográfica irá retornar o endereço daquela localização, de modo a quando houver o aplicativo mobile seja possível mostrar um mapa pontuando os locais onde ocorreram incidentes classificados em três níveis: **Leve, Moderado e Grave**. Hoje esses incidentes são gravados em um banco de dados *MySQL*, e há um script que popula o BD com dados, para isso basta trocar a linha abaixo no arquivo *application.properties* da pasta **src/main/resources**:

```application.properties
spring.jpa.hibernate.ddl-auto=none
```
> TROCAR PARA:

```application.properties
spring.jpa.hibernate.ddl-auto=create
```

O principal objetivo deste serviço é situar os usuários de onde e com qual gravidade mais ocorrem incidentes de modo a tornar bem visual com um mapa os locais que merecem cuidado, atenção e o distanciamento. O *WebService* funciona com uma requisição HTTP, mas antes para que seja possível testar é preciso com o código em mãos entrar no diretório raiz do projeto e executar os comandos (para isso ter instalado *Java* e *Maven*):

```bash
# Compilação e importação das dependências
mvn clean install

# Entrada na pasta do .jar criado
cd target

# Execução da aplicação Spring
java -jar sec-womap-0.0.1-SNAPSHOT.jar
```

# Postman, CURL ou outra aplicação de requisição HTTP

Com a aplicação funcionando, agora vem as requisições HTTP ao *WebService*. As requisições são feitas na *inserção* com **JSON** com os campos demonstrados a seguir e essas requisições podem ser feitas em aplicações como o **Postman** ou **Insomnia**, ou até diretamente na linha de comando pelo **_CURL_**, que serão apresentados a seguir:

##### Requisição de inserção de incidente pelo endereço
```curl
curl --location --request POST 'http://localhost:8080/incident/address/2?description=Depois de uma confusão e briga, um homem me assediou' \
--header 'Content-Type: application/json' \
--data-raw '{
    "address": "Rua Mario Palmério 74",
    "city-neighborhood": "Francisco Morato",
    "state": "São Paulo",
    "cep": "",
    "country": "Brasil"
}'
```

##### Requisição de inserção de incidente pela geolocalização
```curl
curl --location --request POST 'http://localhost:8080/incident/location/2?description=Mandaram eu sentar em um banco na rua e fizeram eu ficar na roda dos homens sem que eu quisesse' \
--header 'Content-Type: application/json' \
--data-raw '{
    "lat": 47.46388,
    "lng" : 2.68819
}'
```

##### Requisição de leitura de todos os incidentes
```curl
curl --location --request GET 'http://localhost:8080/incident'
```

##### Requisição de leitura pelo endereço (neste exemplo todos com 'Brazil')
```curl
curl --location --request GET 'http://localhost:8080/incident/address?address=Brazil'
```

##### Requisição de leitura pela gravidade do incidente (neste exemplo 3 - Grave)
```curl
curl --location --request GET 'http://localhost:8080/incident/severity/3'
```

## Documentação visual da aplicação (Swagger UI)

Caso a aplicação *SecWomap* seja rodada localmente, na própria máquina, a URL abaixo oferece a documentação visual da aplicação feita pelo *Swagger UI*, podendo ser visualizada em qualquer navegador:

```swagger
http://localhost:8080/swagger-ui/index.html
```

Caso a aplicação suba em algum serviço de nuvem ou com DNS, para inserir o caminho criado pelo serviço ou DNS com o final:

```
/swagger-ui/index.html
```

Qualquer dúvida ou erro encontrado, reportar ao GitHub abaixo:

```
https://github.com/aempinto02/secwomap
```