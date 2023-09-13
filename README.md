# Wellcar_sg
API para gestao de clientes e servico de oficina mecanica

## Funcionalidades

- Cadastro de clientes com informações pessoais.
- Associação de carros aos clientes.
- Criação de orçamentos para serviços a serem realizados.
- Geração de ordens de serviço com status (em aberto ou finalizado).
- Cálculo do valor total de serviços executados.
- Geração de relatórios de clientes e serviços executados.
- Interface de API RESTful para interação com a aplicação.

## Pré-requisitos

Certifique-se de que você tenha as seguintes ferramentas instaladas em sua máquina:

- [Java JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [PostgreSQL](https://www.postgresql.org/download/)
- [Maven](https://maven.apache.org/download.cgi) (opcional, caso você não use uma IDE que inclua o Maven)

## Configuração do Banco de Dados

Esta aplicação utiliza o PostgreSQL como banco de dados. Certifique-se de configurar o banco de dados antes de executar a aplicação. Você pode fazer isso editando o arquivo `application.properties` e definindo as configurações do banco de dados, incluindo URL, nome de usuário e senha.

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/meu_banco_de_dados
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```
## Executando a Aplicação

Para executar a aplicação, você pode usar o Maven ou sua IDE favorita.

**Usando o Maven:**

```bash
mvn spring-boot:run
```

A aplicação estará disponível em http://localhost:8080. Certifique-se de que a porta esteja livre para uso.

## Documentação da API
A documentação da API está disponível em /swagger-ui.html, onde você pode explorar e testar os endpoints da API.

##Exemplos de Uso
Aqui estão alguns exemplos de como usar a API:

## Cadastro de Cliente

```json
  POST /client/save
  Content-Type: application/json
  {
    "name": "Marcelo",
    "phone": "(62)9999-8888",
    "cpf": 87912734333,
    "email": "teste@teste.com"
}
```

## Associação de Carro a Cliente
```json
   POST /client/{id}/cars
  Content-Type: application/json
  
  {
     "brand": "Toyota",
     "model": "Corolla",
     "license": "JHH-0323",
     "color": "Prata",
     "ageModel": "12/13"
  }

```


## Relatórios

- Relatório de Clientes
- Relatório de Serviços Executados





