# 🛒 Order Manager API

API REST para gerenciamento de pedidos, produtos, usuários e pagamentos, desenvolvida com **Java e Spring Boot**, seguindo boas práticas de desenvolvimento backend e arquitetura em camadas.

---

## 🚀 Tecnologias

- **Java 21**
- **Spring Boot**
- **Spring Data JPA / Hibernate**
- **PostgreSQL**
- **H2 Database**
- **Maven**
- **Git**

---

## 📦 Funcionalidades

- Cadastro e gerenciamento de usuários
- Cadastro e gerenciamento de produtos
- Organização de produtos por categorias
- Criação e acompanhamento de pedidos
- Registro de pagamentos vinculados aos pedidos
- Tratamento global de exceções com respostas padronizadas

---

## 🗂️ Estrutura do Projeto

```text
src/
├── controllers/       # Endpoints REST
├── services/          # Regras de negócio
├── repositories/      # Acesso ao banco de dados
├── model/             # Entidades JPA
│   ├── enums/         # Enumerações do sistema
│   └── pk/            # Chaves compostas
├── exceptions/        # Tratamento de exceções
└── config/            # Configurações de ambiente
```

---

## 🔗 Endpoints

### Usuários `/users`

| Método | Rota         | Descrição              |
|--------|--------------|-------------------------|
| GET    | /users       | Lista todos os usuários |
| GET    | /users/{id}  | Busca usuário por ID    |
| POST   | /users       | Cria novo usuário       |
| PUT    | /users/{id}  | Atualiza usuário        |
| DELETE | /users/{id}  | Remove usuário          |

### Produtos `/products`

| Método | Rota           | Descrição              |
|--------|----------------|--------------------------|
| GET    | /products      | Lista todos os produtos |
| GET    | /products/{id} | Busca produto por ID    |
| POST   | /products      | Cria novo produto       |
| DELETE | /products/{id} | Remove produto          |

### Categorias `/categories`

| Método | Rota             | Descrição                  |
|--------|------------------|------------------------------|
| GET    | /categories      | Lista todas as categorias   |
| GET    | /categories/{id} | Busca categoria por ID      |
| POST   | /categories      | Cria categoria              |
| PUT    | /categories/{id} | Atualiza categoria          |
| DELETE | /categories/{id} | Remove categoria            |

### Pedidos `/orders`

| Método | Rota         | Descrição            |
|--------|--------------|------------------------|
| GET    | /orders      | Lista todos os pedidos |
| GET    | /orders/{id} | Busca pedido por ID    |
| POST   | /orders      | Cria pedido             |
| PUT    | /orders/{id} | Atualiza pedido         |
| DELETE | /orders/{id} | Remove pedido           |

---

## 📊 Status do Pedido (OrderStatus)

| Código | Status           |
|--------|------------------|
| 1      | WAITING_PAYMENT  |
| 2      | PAID             |
| 3      | SHIPPED          |
| 4      | DELIVERED        |
| 5      | CANCELED         |

---

## ⚙️ Como executar localmente

### Pré-requisitos

- Java 21+
- Maven
- Perfil de teste (H2 Database)

### Clone o projeto

```bash
git clone https://github.com/seu-usuario/order-manager-api.git
cd order-manager-api
```

### Execute

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=test
```

A API estará disponível em:

```
http://localhost:8080
```

### Perfil de produção (PostgreSQL)

Configure as variáveis de ambiente:

```properties
spring.datasource.url=jdbc:postgresql://HOST:PORT/DATABASE
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA

spring.jpa.hibernate.ddl-auto=update
```

---

## 🌐 Deploy

Aplicação hospedada no Railway utilizando:

- Spring Boot
- PostgreSQL
- Perfil de produção

---

## 📐 Modelo de Dados

**User**
- Possui vários pedidos (Orders)

**Order**
- Pertence a um usuário
- Possui um pagamento
- Possui vários itens (OrderItems)

**OrderItem**
- Utiliza chave composta (Order + Product)
- Armazena quantidade e preço

**Product**
- Associado a categorias

**Payment**
- Relacionado ao pedido utilizando `@MapsId`

---

## 🛡️ Tratamento de Erros

A API possui respostas padronizadas para exceções:

```json
{
  "timestamp": "2024-01-01T10:00:00",
  "status": 404,
  "error": "Resource not found",
  "message": "Resource not found: 99",
  "path": "/users/99"
}
```

---

## 👤 Autor

Feito por **Guilherme**