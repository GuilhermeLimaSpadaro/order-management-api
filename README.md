<div align="center">

# 🛒 Order Management API

[![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.java.net/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.1.1-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)](LICENSE)
[![Build](https://img.shields.io/badge/Build-Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)

API REST para gerenciamento de pedidos, produtos, categorias e usuários.

[Funcionalidades](#-funcionalidades) •
[Arquitetura](#-arquitetura) •
[Endpoints](#-endpoints) •
[Como Executar](#-como-executar) •
[Padrões Adotados](#-padr%C3%B5es-adotados)

</div>

---

## 📖 Sobre o Projeto

**Order Management API** é uma API RESTful construída com **Java 21** e **Spring Boot 4.1.1** que gerencia o ciclo completo de pedidos de compra, desde o cadastro de produtos e categorias até a criação e acompanhamento de pedidos com seus itens.

O projeto segue uma arquitetura em camadas, separando claramente as responsabilidades entre controllers, services, repositories e mappers, com uso de DTOs para isolamento da camada de persistência e validação robusta dos dados de entrada.

---

## 🛠 Tecnologias

| Categoria | Tecnologia | Versão |
|---|---|---|
| **Linguagem** | Java | 21 |
| **Framework** | Spring Boot | 4.1.1 |
| **Web** | Spring Web MVC | — |
| **Persistência** | Spring Data JPA + Hibernate | — |
| **Banco (Dev)** | H2 Database | In-memory |
| **Banco (Prod)** | PostgreSQL | 42.7.14 |
| **Mapper** | MapStruct | 1.6.3 |
| **Validação** | Jakarta Validation | — |
| **Build** | Maven | — |
| **Servidor** | Apache Tomcat | 11.0.27 |

---

## ✨ Funcionalidades

- ✅ **Usuários** — Cadastro, consulta, atualização e remoção
- ✅ **Produtos** — CRUD completo com nome, descrição, preço e imagem
- ✅ **Categorias** — CRUD completo para organização dos produtos
- ✅ **Pedidos** — Criação com itens, consulta por ID e por usuário
- ✅ **Itens de Pedido** — Vinculação de produtos aos pedidos com quantidade e preço
- ✅ **Validação** — Validação de dados com Jakarta Validation em todos os endpoints
- ✅ **Tratamento de Erros** — Handler global com respostas padronizadas em JSON
- ✅ **Spring Profiles** — Configuração separada para desenvolvimento e produção
- ✅ **Versionamento** — API versionada com prefixo `/api/v1`

---

## 🏗 Arquitetura

### Diagrama de Camadas

```
┌─────────────────────────────────────────────────────────────┐
│                     Client (HTTP)                           │
└──────────────────────────┬──────────────────────────────────┘
                           │
                           ▼
┌─────────────────────────────────────────────────────────────┐
│                  Controller Layer                           │
│  @RestController • @Valid • ResponseEntity                 │
│  UserController • ProductController                        │
│  CategoryController • OrderController                      │
└──────────────────────────┬──────────────────────────────────┘
                           │
                           ▼
┌─────────────────────────────────────────────────────────────┐
│                    Service Layer                            │
│  @Service • @Transactional • Lógica de negócio             │
│  UserService • ProductService                              │
│  CategoryService • OrderService                            │
└──────────────────────────┬──────────────────────────────────┘
                           │
                           ▼
┌─────────────────────────────────────────────────────────────┐
│                     Mapper Layer                            │
│  MapStruct • DTO ↔ Entity conversion                      │
│  UserMapper • ProductMapper                                │
│  CategoryMapper • OrderMapper                              │
└──────────────────────────┬──────────────────────────────────┘
                           │
                           ▼
┌─────────────────────────────────────────────────────────────┐
│                  Repository Layer                           │
│  JpaRepository • Spring Data JPA                          │
│  UserRepository • ProductRepository                        │
│  CategoryRepository • OrderRepository                      │
└──────────────────────────┬──────────────────────────────────┘
                           │
                           ▼
┌─────────────────────────────────────────────────────────────┐
│                    Database (H2 / PostgreSQL)               │
└─────────────────────────────────────────────────────────────┘
```

### Modelo de Domínio

```
┌──────────┐         ┌──────────┐         ┌──────────┐
│  User    │────1:N──│  Order   │────1:N──│OrderItem │
│          │         │          │         │          │
│  id      │         │  id      │         │  id      │
│  name    │         │  moment  │         │  qty     │
│  email   │         │  status  │         │  price   │
│  phone   │         │  userId  │         │  orderId │
│  password│         └────┬─────┘         │  prodId  │
└──────────┘              │               └────┬─────┘
                          │                    │
                          │                    │ M:1
                          │               ┌────▼──────┐
                          │               │  Product  │
                          │               │           │
                          │               │  id       │
                          │               │  name     │
                          │               │  price    │
                          │               │  imgUrl   │
                          │               └────┬──────┘
                          │                    │
                          │               ┌────▼──────────┐
                          │               │   Category    │
                          │               │               │
                          └───────────────│  id           │
                                         │  name         │
                                         └───────────────┘
```

### Relacionamentos

| Relação | Tipo | Descrição |
|---|---|---|
| `User` → `Order` | OneToMany | Um usuário pode ter vários pedidos |
| `Order` → `OrderItem` | OneToMany | Um pedido pode ter vários itens |
| `OrderItem` → `Order` | ManyToOne | Um item pertence a um pedido |
| `OrderItem` → `Product` | ManyToOne | Um item referencia um produto |
| `Product` → `Category` | ManyToMany | Um produto pode estar em várias categorias |

---

## 📁 Estrutura do Projeto

```
src/main/java/br/com/gspadaro/ordermanagerapi/
├── controller/           # Endpoints REST
│   ├── UserController.java
│   ├── ProductController.java
│   ├── CategoryController.java
│   └── OrderController.java
├── dto/                  # Objetos de transferência (records)
│   ├── category/
│   │   ├── CategoryRequestDTO.java
│   │   └── CategoryResponseDTO.java
│   ├── order/
│   │   ├── OrderRequestDTO.java
│   │   ├── OrderResponseDTO.java
│   │   ├── OrderItemRequestDTO.java
│   │   └── OrderItemResponseDTO.java
│   ├── product/
│   │   ├── ProductRequestDTO.java
│   │   └── ProductResponseDTO.java
│   └── user/
│       ├── UserRequestDTO.java
│       └── UserResponseDTO.java
├── exception/            # Tratamento global de exceções
│   ├── GlobalHandlerException.java
│   ├── ResourceNotFoundException.java
│   └── StandardError.java
├── mapper/               # Conversão entre entidades e DTOs
│   ├── ObjectMapper.java          # Interface base genérica
│   └── custom/
│       ├── UserMapper.java
│       ├── ProductMapper.java
│       ├── CategoryMapper.java
│       └── OrderMapper.java
├── model/                # Entidades JPA
│   ├── enums/
│   │   └── OrderStatus.java
│   ├── User.java
│   ├── Product.java
│   ├── Category.java
│   ├── Order.java
│   └── OrderItem.java
├── repository/           # Persistência com Spring Data JPA
│   ├── UserRepository.java
│   ├── ProductRepository.java
│   ├── CategoryRepository.java
│   ├── OrderRepository.java
│   └── OrderItemRepository.java
├── service/              # Regras de negócio
│   ├── UserService.java
│   ├── ProductService.java
│   ├── CategoryService.java
│   └── OrderService.java
└── OrderManagerApiApplication.java
```

---

## 🔌 Endpoints

> **Base URL:** `http://localhost:8080/api/v1`

### Users

| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/api/v1/users` | Cria um usuário |
| `GET` | `/api/v1/users/{id}` | Busca um usuário por ID |
| `PUT` | `/api/v1/users/{id}` | Atualiza um usuário |
| `DELETE` | `/api/v1/users/{id}` | Remove um usuário |

### Products

| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/api/v1/products` | Cria um produto |
| `GET` | `/api/v1/products/{id}` | Busca um produto por ID |
| `PUT` | `/api/v1/products/{id}` | Atualiza um produto |
| `DELETE` | `/api/v1/products/{id}` | Remove um produto |

### Categories

| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/api/v1/categories` | Cria uma categoria |
| `GET` | `/api/v1/categories/{id}` | Busca uma categoria por ID |
| `PUT` | `/api/v1/categories/{id}` | Atualiza uma categoria |
| `DELETE` | `/api/v1/categories/{id}` | Remove uma categoria |

### Orders

| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/api/v1/orders` | Cria um pedido com itens |
| `GET` | `/api/v1/orders/{id}` | Busca um pedido por ID |
| `GET` | `/api/v1/orders/users/{id}` | Lista pedidos de um usuário |
| `PUT` | `/api/v1/orders/{id}` | Atualiza um pedido |
| `DELETE` | `/api/v1/orders/{id}` | Remove um pedido |

---

## 📋 Status do Pedido

| Status | Descrição |
|---|---|
| `WAITING_PAYMENT` | Aguardando pagamento |
| `PAID` | Pago |
| `SHIPPED` | Enviado |
| `DELIVERED` | Entregue |
| `CANCELED` | Cancelado |

---

## 📝 Exemplos de Request

### Criar Usuário

```bash
curl -X POST http://localhost:8080/api/v1/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Guilherme Spadaro",
    "email": "guilherme@email.com",
    "phone": "11999999999"
  }'
```

### Criar Produto

```bash
curl -X POST http://localhost:8080/api/v1/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Café Premium",
    "description": "Café torrado e moído",
    "price": 29.90,
    "imgUrl": "https://exemplo.com/cafe.jpg"
  }'
```

### Criar Categoria

```bash
curl -X POST http://localhost:8080/api/v1/categories \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Bebidas"
  }'
```

### Criar Pedido

```bash
curl -X POST http://localhost:8080/api/v1/orders \
  -H "Content-Type: application/json" \
  -d '{
    "moment": "2026-10-28T19:00:00",
    "orderStatus": "WAITING_PAYMENT",
    "userId": 1,
    "items": [
      {
        "productId": 1,
        "quantity": 2,
        "price": 29.90
      },
      {
        "productId": 2,
        "quantity": 1,
        "price": 15.50
      }
    ]
  }'
```

---

## ✅ Validações

Os DTOs de request possuem validações com **Jakarta Validation**:

| DTO | Validações |
|---|---|
| `UserRequestDTO` | `name` (@NotBlank), `email` (@Email, @NotBlank), `phone` (@NotBlank, @Size min=9, max=9) |
| `ProductRequestDTO` | `name` (@NotBlank), `description` (@NotBlank), `price` (@Positive, @NotNull) |
| `CategoryRequestDTO` | `name` (@NotBlank) |
| `OrderRequestDTO` | `moment` (@NotNull), `orderStatus` (@NotNull), `userId` (@NotNull), `items` (@NotEmpty) |

---

## 🚨 Tratamento de Exceções

A API possui tratamento global de exceções com respostas padronizadas:

| Exceção | Status HTTP | Descrição |
|---|:---:|---|
| `ResourceNotFoundException` | 404 | Recurso não encontrado |
| `MethodArgumentNotValidException` | 400 | Validação dos dados falhou |
| `MethodArgumentTypeMismatchException` | 400 | Tipo de argumento inválido |
| `HttpMessageNotReadableException` | 400 | Corpo da requisição inválido |

### Exemplo de Resposta de Erro

```json
{
  "timeStamp": "2026-10-28T19:00:00Z",
  "status": 400,
  "error": "Validation Failed",
  "path": "/api/v1/users"
}
```

---

## ⚙️ Configuração

O projeto utiliza **Spring Profiles** para separar as configurações de ambiente.

### Arquivo Principal

`application.yml` — Configuração comum:

```yaml
spring:
  application:
    name: order-manager-api
  profiles:
    active: dev
  jpa:
    open-in-view: false
```

### Profile de Desenvolvimento

`application-dev.yml` — H2 em memória com console habilitado:

- **Banco:** `jdbc:h2:mem:order_db`
- **Console H2:** `/h2-console`
- **DDL auto:** `update`
- **SQL log:** habilitado

### Profile de Produção

`application-prod.yml` — Para configurar com PostgreSQL:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/order_db
    username: postgres
    password: ${DB_PASSWORD}
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: validate
```

Para trocar o profile:

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=prod
```

---

## 🚀 Como Executar

### Pré-requisitos

- **Java 21**
- **Maven**

> Para desenvolvimento local, não é necessário instalar banco de dados: o H2 em memória já é o padrão.

### Rodar a Aplicação

```bash
./mvnw spring-boot:run
```

A API fica disponível em:

```
http://localhost:8080
```

### Console H2 (Desenvolvimento)

```
http://localhost:8080/h2-console
```

**Credenciais do console:**
- **JDBC URL:** `jdbc:h2:mem:order_db`
- **Username:** `sa`
- **Password:** *(vazio)*

---

## 🗄 Tabelas do Banco

| Tabela | Descrição |
|---|---|
| `tb_user` | Usuários do sistema |
| `products` | Produtos disponíveis |
| `tb_category` | Categorias de produtos |
| `orders` | Pedidos realizados |
| `order_items` | Itens de cada pedido |

---

## 📐 Padrões Adotados

Este projeto segue práticas e convenções amplamente adotadas no mercado:

### Arquitetura

- **Camadas separadas** — Controller → Service → Mapper → Repository
- **Injeção via construtor** — Imutabilidade e testabilidade
- **DTOs com records** — Objetos imutáveis e concisos para transferência de dados
- **MapStruct** — Mapeamento tipo-safe gerado em tempo de compilação
- **Spring Profiles** — Configuração separada por ambiente

### Nomenclatura

- **Tabelas** — Snake_case (`order_items`, `products`)
- **Campos** — Snake_case (`user_id`, `order_status`)
- **Classes Java** — PascalCase (`OrderService`, `UserRequestDTO`)
- **Variáveis** — camelCase (`orderRepository`, `createdOrder`)

### REST

- **Versionamento** — Prefixo `/api/v1` nos endpoints
- **HTTP verbs** — POST (criar), GET (ler), PUT (atualizar), DELETE (remover)
- **Status codes** — 200 (OK), 201 (Created), 204 (No Content), 400 (Bad Request), 404 (Not Found)
- **URI de criação** — `Location` header com URI do recurso criado

### Validação e Erros

- **Jakarta Validation** — Validação declarativa nos DTOs
- **Handler global** — Tratamento centralizado de exceções
- **Resposta padronizada** — Estrutura JSON consistente para erros

### Banco de Dados

- **IDs simples** — `@GeneratedValue` com `IDENTITY`
- **Relacionamentos** — `@ManyToOne`, `@OneToMany`, `@ManyToMany`
- **Timestamp automático** — `@CreationTimestamp` para data de criação
- **Equals/HashCode** — Baseados no ID das entidades

---

## 📅 Roadmap

- [ ] **Spring Security** — Autenticação e autorização com JWT
- [ ] **Criptografia de senhas** — BCrypt para armazenamento seguro
- [ ] **Testes unitários** — Cobertura de services e controllers
- [ ] **Testes de integração** — Endpoints com MockMvc
- [ ] **OpenAPI/Swagger** — Documentação interativa da API
- [ ] **Paginação** — Listagem paginada nos endpoints
- [ ] **PostgreSQL** — Configuração completa do profile de produção
- [ ] **Docker** — Containerização da aplicação
- [ ] **CI/CD** — Pipeline de integração contínua

---

## 📄 Licença

Este projeto está sob a licença **MIT**. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

## 👤 Autor

**Guilherme Spadaro**

---

<div align="center">

Feito com ❤️ usando Java 21 e Spring Boot 4.1.1

</div>