# Order Management API

API REST para gerenciamento de usuários, produtos, categorias, pedidos e itens de pedido.

O projeto foi desenvolvido com Java e Spring Boot utilizando Spring Data JPA/Hibernate e banco relacional, com separação em camadas e uso de DTOs em operações de usuários e pedidos.

## Tecnologias

- Java 21
- Spring Boot 4.0.6
- Spring Web MVC
- Spring Data JPA
- Hibernate
- PostgreSQL
- H2 Database
- Maven

## Funcionalidades

- Cadastro e gerenciamento de usuários
- Cadastro e gerenciamento de produtos
- Cadastro e gerenciamento de categorias
- Criação e gerenciamento de pedidos
- Consulta de pedidos por usuário
- Associação de produtos a categorias
- Associação de pedidos a usuários
- Itens de pedido com chave composta
- DTOs de request/response para usuários e pedidos
- Tratamento global de exceções
- Carga de dados de exemplo no perfil `test`

## Modelo de domínio

```text
User
 └── Orders

Order
 ├── User (customer)
 └── OrderItems

OrderItem
 ├── Order
 └── Product

Product
 └── Categories
```

## Estrutura

```text
src/main/java/com/gspadaro/ordermanagerapi
├── config
├── controller
├── domain
│   ├── enums
│   └── pk
├── dto
├── exception
├── mapper
├── repository
└── service
```

- `controller` — endpoints REST
- `service` — regras de negócio
- `repository` — persistência com Spring Data JPA
- `mapper` — conversão entre entidades e DTOs
- `dto` — objetos de entrada e saída
- `domain` — entidades JPA
- `exception` — tratamento de exceções
- `config` — carga de dados de exemplo (perfil `test`)

## Endpoints

### Users

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/users` | Cria um usuário |
| GET | `/users` | Lista usuários |
| GET | `/users/{id}` | Busca um usuário |
| PUT | `/users/{id}` | Atualiza um usuário |
| DELETE | `/users/{id}` | Remove um usuário |

### Products

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/products` | Cria um produto |
| GET | `/products` | Lista produtos |
| GET | `/products/{id}` | Busca um produto |
| PUT | `/products/{id}` | Atualiza um produto |
| DELETE | `/products/{id}` | Remove um produto |

### Categories

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/categories` | Cria uma categoria |
| GET | `/categories` | Lista categorias |
| GET | `/categories/{id}` | Busca uma categoria |
| PUT | `/categories/{id}` | Atualiza uma categoria |
| DELETE | `/categories/{id}` | Remove uma categoria |

### Orders

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/orders` | Cria um pedido |
| GET | `/orders` | Lista pedidos |
| GET | `/orders/{id}` | Busca um pedido |
| GET | `/orders/users/{id}` | Lista pedidos de um usuário |
| PUT | `/orders/{id}` | Atualiza um pedido |
| DELETE | `/orders/{id}` | Remove um pedido |

## Status do pedido

| Código | Status |
|---|---|
| 1 | `WAITING_PAYMENT` |
| 2 | `PAID` |
| 3 | `SHIPPED` |
| 4 | `DELIVERED` |
| 5 | `CANCELED` |

## DTOs

As operações de usuários e pedidos utilizam DTOs para separar a representação da API das entidades de persistência. Produtos e categorias ainda expõem diretamente as entidades.

Exemplo de criação de pedido:

```json
{
  "moment": "2026-07-29T14:30:00",
  "orderStatus": "WAITING_PAYMENT",
  "customerId": 1,
  "items": []
}
```

Atualmente, o campo `items` aparece nos DTOs, mas não é persistido na criação ou atualização do pedido. Os itens existem apenas nos dados de exemplo do perfil `test`. A criação de itens via API pode ser evoluída com DTOs específicos de item de pedido.

## Perfis de ambiente

O perfil ativo por padrão é `test` (definido em `application.properties`).

### Teste

O perfil `test` utiliza H2 em memória, com console H2 habilitado, e popula o banco com categorias, produtos, usuários, pedidos e itens de exemplo a cada inicialização:

```bash
./mvnw spring-boot:run
```

Banco:

```text
jdbc:h2:mem:testdb
```

### Produção

O perfil `prod` utiliza PostgreSQL por meio das variáveis:

```text
DATABASE_URL
DATABASE_USERNAME
DATABASE_PASSWORD
```

Para executar com esse perfil:

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=prod
```

O projeto utiliza:

```text
spring.jpa.hibernate.ddl-auto=update
```

nesse perfil.

## Como executar

### Pré-requisitos

- Java 21
- Maven

Para desenvolvimento local, o perfil `test` já é o padrão e não exige servidor PostgreSQL.

```bash
./mvnw spring-boot:run
```

A API fica disponível em:

```text
http://localhost:8080
```

## Roadmap

Melhorias e correções planejadas para o projeto:

- Persistir os itens do pedido (`items`) na criação e atualização, com DTOs específicos de item
- Utilizar DTOs também em produtos e categorias, em vez de expor as entidades
- Adicionar validação dos dados de entrada

## Autor

**Guilherme Spadaro**
