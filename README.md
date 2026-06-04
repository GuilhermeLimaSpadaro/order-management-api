# 🛒 Order Manager API

API REST para gerenciamento de pedidos, produtos, usuários e pagamentos, desenvolvida com Java e Spring Boot, com deploy na nuvem via Railway.

## 🚀 Tecnologias

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA / Hibernate**
- **PostgreSQL** (produção - Railway)
- **H2 Database** (testes)
- **Maven**

## 📦 Funcionalidades

- Cadastro e gerenciamento de usuários
- Cadastro e gerenciamento de produtos com categorias
- Criação e acompanhamento de pedidos
- Registro de pagamentos vinculados a pedidos
- Tratamento global de exceções com respostas padronizadas

## 🗂️ Estrutura do Projeto

```
src/
├── controllers/       # Endpoints REST
├── services/          # Regras de negócio
├── repositories/      # Acesso ao banco de dados
├── model/             # Entidades JPA
│   ├── enums/         # OrderStatus
│   └── pk/            # Chave composta OrderItemPk
├── exceptions/        # Tratamento de erros
└── config/            # Configuração de perfis (test/prod)
```

## 🔗 Endpoints

### Usuários `/users`
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/users` | Lista todos os usuários |
| GET | `/users/{id}` | Busca usuário por ID |
| POST | `/users` | Cria novo usuário |
| PUT | `/users/{id}` | Atualiza usuário |
| DELETE | `/users/{id}` | Remove usuário |

### Produtos `/products`
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/products` | Lista todos os produtos |
| GET | `/products/{id}` | Busca produto por ID |
| POST | `/products` | Cria novo produto |
| DELETE | `/products/{id}` | Remove produto |

### Categorias `/categories`
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/categories` | Lista todas as categorias |
| GET | `/categories/{id}` | Busca categoria por ID |
| POST | `/categories` | Cria nova categoria |
| PUT | `/categories/{id}` | Atualiza categoria |
| DELETE | `/categories/{id}` | Remove categoria |

### Pedidos `/orders`
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/orders` | Lista todos os pedidos |
| GET | `/orders/{id}` | Busca pedido por ID |
| POST | `/orders` | Cria novo pedido |
| PUT | `/orders/{id}` | Atualiza status/pagamento |
| DELETE | `/orders/{id}` | Remove pedido |

## 📊 Status do Pedido (OrderStatus)

| Código | Status |
|--------|--------|
| 1 | WAITING_PAYMENT |
| 2 | PAID |
| 3 | SHIPPED |
| 4 | DELIVERED |
| 5 | CANCELED |

## ⚙️ Como rodar localmente

### Pré-requisitos
- Java 17+
- Maven

### Perfil de teste (H2 em memória)

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/order-manager-api.git
cd order-manager-api

# Rode com perfil de teste
./mvnw spring-boot:run -Dspring-boot.run.profiles=test
```

A API estará disponível em `http://localhost:8080`.

### Perfil de produção (PostgreSQL)

Configure as variáveis no `application-prod.properties`:

```properties
spring.datasource.url=jdbc:postgresql://HOST:PORT/DATABASE
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
spring.jpa.hibernate.ddl-auto=update
```

## 🌐 Deploy

A aplicação está hospedada no **Railway** com banco de dados PostgreSQL.

## 📐 Modelo de Dados

- **User** → tem muitos **Orders**
- **Order** → pertence a um **User**, tem um **Payment**, tem muitos **OrderItems**
- **OrderItem** → chave composta (Order + Product), com quantidade e preço
- **Product** → pertence a muitas **Categories**
- **Payment** → vinculado a um **Order** via `@MapsId`

## 🛡️ Tratamento de Erros

Respostas de erro padronizadas com timestamp, status HTTP, mensagem e path:

```json
{
  "timestamp": "2024-01-01T10:00:00",
  "status": 404,
  "error": "Resource not found",
  "message": "Resource not found: 99",
  "path": "/users/99"
}
```

## 👤 Autor

Feito por **Guilherme** — [LinkedIn](https://linkedin.com/in/seu-perfil) · [GitHub](https://github.com/seu-usuario)
