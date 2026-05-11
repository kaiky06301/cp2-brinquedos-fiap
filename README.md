# 🧸 CP2 - API de Brinquedos | FIAP TDS

> **Checkpoint 2 — Programação Spring Boot com Persistência**
> Professor: Dr. Marcel Stefan Wagner

## 👥 Integrantes do Grupo

| Nome | RM |
|---|---|
| Erick Bernardes Bradaschia | 565733 |
| Gabriel Santos Claudino | 564054 |
| Kaiky de Oliveira Silva | 566067 |
| Lucas Fortes de Lima | 559523 |
| Jonathan Moreira Gomes | 565060 |

---

## 📋 Descrição do Projeto

API REST desenvolvida com **Spring Boot + Maven + Java 17** para gerenciar o catálogo de brinquedos de uma empresa especializada em produtos para crianças de até 14 anos.

A aplicação realiza operações completas de **CRUD** (Create, Read, Update e Delete) via endpoints HTTP, com persistência dos dados no banco **Oracle SQL Developer** (ORACLE_FIAP), testados via **Postman**.

---

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.2.5**
- **Maven** (gerenciador de dependências)
- **Spring Data JPA** (persistência)
- **Oracle Database** (ORACLE_FIAP)
- **Hibernate** (ORM)
- **Lombok** (redução de boilerplate)
- **Postman** (testes HTTP)
- **Tomcat** (servidor embutido, porta 8080)

---

## ⚙️ Configuração do Spring Initializr

Acesse [https://start.spring.io](https://start.spring.io) e configure:

| Campo | Valor |
|---|---|
| Project | Maven |
| Language | Java |
| Spring Boot | 3.2.5 |
| Group | br.com.fiap |
| Artifact | brinquedos |
| Java | 17 |

**Dependências selecionadas:**
- Spring Web
- Spring Data JPA
- Oracle Driver
- Validation
- Lombok

> 📸 *O print da tela do Spring Initializr está incluído na pasta raiz do projeto como `spring-initializr.png`*

---

## 🗄️ Banco de Dados Oracle

### Tabela: `TDS_TB_Brinquedos`

| Coluna | Tipo | Descrição |
|---|---|---|
| ID | NUMBER | Chave primária (auto-gerada via Sequence) |
| NOME | VARCHAR2(100) | Nome do brinquedo |
| TIPO | VARCHAR2(50) | Categoria/tipo do brinquedo |
| CLASSIFICACAO | NUMBER | Idade mínima recomendada (0–14) |
| TAMANHO | VARCHAR2(20) | Tamanho (Pequeno / Médio / Grande) |
| PRECO | NUMBER | Preço em reais |

### Script SQL para criação manual (opcional):

```sql
CREATE SEQUENCE SEQ_BRINQUEDO START WITH 1 INCREMENT BY 1;

CREATE TABLE TDS_TB_Brinquedos (
    id            NUMBER PRIMARY KEY,
    nome          VARCHAR2(100) NOT NULL,
    tipo          VARCHAR2(50)  NOT NULL,
    classificacao NUMBER        NOT NULL,
    tamanho       VARCHAR2(20)  NOT NULL,
    preco         NUMBER        NOT NULL
);
```

---

## ▶️ Como Executar

1. Clone o repositório:
```bash
git clone https://github.com/SEU_USUARIO/cp2-brinquedos-fiap.git
cd cp2-brinquedos-fiap
```

2. Configure as credenciais do Oracle em `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
spring.datasource.username=SEU_RM
spring.datasource.password=SUA_SENHA
```

3. Execute a aplicação:
```bash
mvn spring-boot:run
```

4. A API estará disponível em: `http://localhost:8080/brinquedos`

---

## 🔗 Endpoints da API

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/brinquedos` | Cadastrar novo brinquedo |
| GET | `/brinquedos` | Listar todos os brinquedos |
| GET | `/brinquedos/{id}` | Buscar brinquedo por ID |
| GET | `/brinquedos/buscar?nome=xxx` | Buscar brinquedo por nome |
| PUT | `/brinquedos/{id}` | Atualizar brinquedo |
| DELETE | `/brinquedos/{id}` | Excluir brinquedo por ID |

---

## 📦 Exemplos de JSON para CRUD no Postman

### ✅ POST — Criar novo brinquedo
**URL:** `http://localhost:8080/brinquedos`
**Method:** `POST`
**Headers:** `Content-Type: application/json`

**Body (JSON):**
```json
{
  "nome": "LEGO Star Wars",
  "tipo": "Blocos de Montar",
  "classificacao": 7,
  "tamanho": "Médio",
  "preco": 249.90
}
```

**Resposta esperada (201 Created):**
```json
{
  "id": 1,
  "nome": "LEGO Star Wars",
  "tipo": "Blocos de Montar",
  "classificacao": 7,
  "tamanho": "Médio",
  "preco": 249.90
}
```

---

### 📋 GET — Listar todos os brinquedos
**URL:** `http://localhost:8080/brinquedos`
**Method:** `GET`

**Resposta esperada (200 OK):**
```json
[
  {
    "id": 1,
    "nome": "LEGO Star Wars",
    "tipo": "Blocos de Montar",
    "classificacao": 7,
    "tamanho": "Médio",
    "preco": 249.90
  },
  {
    "id": 2,
    "nome": "Boneca Barbie",
    "tipo": "Boneca",
    "classificacao": 3,
    "tamanho": "Pequeno",
    "preco": 89.99
  },
  {
    "id": 3,
    "nome": "Carrinho Hot Wheels",
    "tipo": "Veículos",
    "classificacao": 3,
    "tamanho": "Pequeno",
    "preco": 19.90
  }
]
```

---

### 🔍 GET — Buscar brinquedo por ID
**URL:** `http://localhost:8080/brinquedos/1`
**Method:** `GET`

**Resposta esperada (200 OK):**
```json
{
  "id": 1,
  "nome": "LEGO Star Wars",
  "tipo": "Blocos de Montar",
  "classificacao": 7,
  "tamanho": "Médio",
  "preco": 249.90
}
```

**Resposta se não encontrado (404 Not Found):**
```json
{}
```

---

### 🔍 GET — Buscar brinquedo por nome
**URL:** `http://localhost:8080/brinquedos/buscar?nome=lego`
**Method:** `GET`

**Resposta esperada (200 OK):**
```json
[
  {
    "id": 1,
    "nome": "LEGO Star Wars",
    "tipo": "Blocos de Montar",
    "classificacao": 7,
    "tamanho": "Médio",
    "preco": 249.90
  }
]
```

---

### ✏️ PUT — Atualizar brinquedo existente
**URL:** `http://localhost:8080/brinquedos/1`
**Method:** `PUT`
**Headers:** `Content-Type: application/json`

**Body (JSON):**
```json
{
  "nome": "LEGO Star Wars Millennium Falcon",
  "tipo": "Blocos de Montar",
  "classificacao": 10,
  "tamanho": "Grande",
  "preco": 899.90
}
```

**Resposta esperada (200 OK):**
```json
{
  "id": 1,
  "nome": "LEGO Star Wars Millennium Falcon",
  "tipo": "Blocos de Montar",
  "classificacao": 10,
  "tamanho": "Grande",
  "preco": 899.90
}
```

---

### 🗑️ DELETE — Excluir brinquedo por ID
**URL:** `http://localhost:8080/brinquedos/1`
**Method:** `DELETE`

**Resposta esperada (204 No Content):**
*(Sem corpo na resposta — brinquedo excluído com sucesso do banco de dados Oracle)*

**Resposta se não encontrado (404 Not Found):**
*(Sem corpo)*

---

## ❌ Exemplos de JSON com Validação (erros)

### POST com campos inválidos:
```json
{
  "nome": "",
  "tipo": "Boneca",
  "classificacao": 20,
  "tamanho": "Pequeno",
  "preco": -5.00
}
```

**Resposta (400 Bad Request):**
```json
{
  "nome": "Nome é obrigatório",
  "classificacao": "Classificação máxima é 14 anos",
  "preco": "Preço deve ser maior que zero"
}
```

---

## 🏗️ Estrutura do Projeto

```
brinquedos/
├── src/
│   └── main/
│       ├── java/br/com/fiap/brinquedos/
│       │   ├── BrinquedosApplication.java       ← Classe principal
│       │   ├── controller/
│       │   │   ├── BrinquedoController.java     ← Endpoints REST
│       │   │   └── GlobalExceptionHandler.java  ← Tratamento de erros
│       │   ├── model/
│       │   │   └── Brinquedo.java               ← Entidade JPA
│       │   ├── repository/
│       │   │   └── BrinquedoRepository.java     ← Interface JPA Repository
│       │   └── service/
│       │       └── BrinquedoService.java        ← Regras de negócio
│       └── resources/
│           └── application.properties           ← Configuração Oracle
├── pom.xml                                      ← Dependências Maven
├── integrantes.txt                              ← Dados do grupo
└── spring-initializr.png                       ← Print do Spring Initializr
```

---

## 🔄 Fluxo da Aplicação

```
Postman (JSON) ──── HTTP ────► BrinquedoController
                                      │
                                      ▼
                               BrinquedoService
                                      │
                                      ▼
                            BrinquedoRepository (JPA)
                                      │
                                   Persist
                                      │
                                      ▼
                         BD Oracle SQL Developer
                         (Tabela TDS_TB_Brinquedos)
```

---

*FIAP — Faculdade de Informática e Administração Paulista*
*Curso de Tecnologia em Análise e Desenvolvimento de Sistemas (TDS)*
