# Order Management API

API REST para gerenciamento de usuários, produtos, categorias, pedidos e itens de pedido.

O projeto foi desenvolvido com Java e Spring Boot utilizando Spring Data JPA/Hibernate e banco relacional, com separação em camadas, uso de DTOs e mapeamento com MapStruct.

## Tecnologias

- Java 21
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data JPA
- Hibernate
- PostgreSQL
- H2 Database
- MapStruct
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
- DTOs de request/response para usuários, produtos, categorias e pedidos
- Mapeamento entre entidades e DTOs com MapStruct
- Versionamento da API com prefixo `/api/v1`
- Tratamento global de exceções

## Modelo de domínio

```text
User
 └── Orders

Order
 ├── User
 └── OrderItems

OrderItem
 ├── Order
 └── Product

Product
 └── Categories
```

## Estrutura

```text
src/main/java/br/com/gspadaro/ordermanagerapi
├── controller
├── dto
│   ├── category
│   ├── order
│   ├── product
│   └── user
├── exception
├── mapper
│   └── custom
├── model
│   ├── enums
│   └── pk
├── repository
└── service
```

- `controller` — endpoints REST
- `service` — regras de negócio
- `repository` — persistência com Spring Data JPA
- `mapper` — conversão entre entidades e DTOs (MapStruct)
- `dto` — objetos de entrada e saída
- `model` — entidades JPA
- `exception` — tratamento de exceções

## Endpoints

### Users

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/api/v1/users` | Cria um usuário |
| GET | `/api/v1/users/{id}` | Busca um usuário |
| PUT | `/api/v1/users/{id}` | Atualiza um usuário |
| DELETE | `/api/v1/users/{id}` | Remove um usuário |

### Products

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/api/v1/products` | Cria um produto |
| GET | `/api/v1/products/{id}` | Busca um produto |
| PUT | `/api/v1/products/{id}` | Atualiza um produto |
| DELETE | `/api/v1/products/{id}` | Remove um produto |

### Categories

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/api/v1/categories` | Cria uma categoria |
| GET | `/api/v1/categories/{id}` | Busca uma categoria |
| PUT | `/api/v1/categories/{id}` | Atualiza uma categoria |
| DELETE | `/api/v1/categories/{id}` | Remove uma categoria |

### Orders

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/api/v1/orders` | Cria um pedido |
| GET | `/api/v1/orders/{id}` | Busca um pedido |
| GET | `/api/v1/orders/users/{id}` | Lista pedidos de um usuário |
| PUT | `/api/v1/orders/{id}` | Atualiza um pedido |
| DELETE | `/api/v1/orders/{id}` | Remove um pedido |

## Status do pedido

| Código | Status |
|---|---|
| 1 | `WAITING_PAYMENT` |
| 2 | `PAID` |
| 3 | `SHIPPED` |
| 4 | `DELIVERED` |
| 5 | `CANCELED` |

## DTOs

Todas as operações utilizam DTOs de request/response para separar a representação da API das entidades de persistência, com conversão feita por mappers MapStruct.

Exemplo de criação de pedido:

```json
{
  "moment": "2026-07-29T14:30:00",
  "orderStatus": "WAITING_PAYMENT",
  "userId": 1,
  "items": []
}
```

Atualmente, o campo `items` aparece nos DTOs, mas não é persistido na criação ou atualização do pedido. A criação de itens via API pode ser evoluída com DTOs específicos de item de pedido.

## Configuração

A configuração fica em `application.yml`. Por padrão, a aplicação usa H2 em memória, com console habilitado em `/h2-console`:

```text
jdbc:h2:mem:order_db
```

O driver do PostgreSQL já está no projeto. Para usá-lo, basta ajustar `spring.datasource` no `application.yml`.

## Como executar

### Pré-requisitos

- Java 21
- Maven

Para desenvolvimento local, não é necessário instalar nenhum banco: o H2 em memória já é o padrão.

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
- Adicionar validação dos dados de entrada

## Autor

**Guilherme Spadaro**