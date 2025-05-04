# 🗃️ CRUD de Funcionários com Spring Boot + Amazon DynamoDB

Este projeto é uma API REST completa para gerenciamento de funcionários (Employee), construída com **Spring Boot 3** e utilizando o **Amazon DynamoDB** como banco de dados NoSQL. O foco é demonstrar como integrar com serviços da AWS usando o SDK oficial.

---

## ✅ Funcionalidades

- Criar funcionário (`POST /employees`)
- Listar todos os funcionários (`GET /employees`)
- Buscar funcionário por ID (`GET /employees/{id}`)
- Atualizar dados de funcionário (`PUT /employees/{id}`)
- Deletar funcionário (`DELETE /employees/{id}`)
- ID gerado automaticamente com `UUID` se não for enviado
- Criação automática da tabela `Employee` no DynamoDB
- Respostas padronizadas com `status`, `message`, `data`

---

## ⚙️ Tecnologias usadas

- Java 21
- Spring Boot 3.4.5
- Maven
- Amazon DynamoDB (usando AWS SDK v2)
- DynamoDbEnhancedClient

---

## ☁️ Pré-requisitos

### 1. Conta na AWS

- Crie uma conta gratuita: [https://aws.amazon.com/pt/free](https://aws.amazon.com/pt/free)
- Acesse o serviço **IAM** e crie um usuário com permissão `AmazonDynamoDBFullAccess`
- Gere uma **Access Key ID** e **Secret Access Key**

---

### 2. Configuração local

#### 📂 application.properties

No arquivo `src/main/resources/application.properties`, mantenha apenas isso:

```properties
spring.application.name=DynamoDb-SpringBoot-Crud
aws.region=us-east-2
```
🔐 As chaves de acesso devem estar configuradas no seu ambiente local, como no AWS CLI (aws configure) ou arquivo ~/.aws/credentials.

ou se preferir, como eu fiz(indicado apenas para rodar localmente):

```properties
spring.application.name=DynamoDb-SpringBoot-Crud
aws.region=us-east-2
aws.accessKey=SUA_ACESSKEY
aws.secretKey=SUA_SECRETKEY
```
### 🚀 Como rodar o projeto localmente

🔧 Passo a passo
1. Clone o repositório:
``` text
git clone https://github.com/matbarroso97/springboot-dynamodb-crud.git
cd springboot-dynamodb-crud
```
2. (Opcional) Configure as credenciais AWS:
``` text
aws configure
# informe sua access key, secret key e região (ex:us-east-2)
```
3. Execute o projeto:
A API iniciará em:
📍 http://localhost:8080/employees
A tabela Employee será criada automaticamente no DynamoDB se não existir.

📬 Endpoints da API

| Método | URL                  | Descrição                       |
|--------|----------------------|----------------------------------|
| POST   | `/employees`         | Criar novo funcionário           |
| GET    | `/employees`         | Listar todos os funcionários     |
| GET    | `/employees/{id}`    | Buscar funcionário por ID        |
| PUT    | `/employees/{id}`    | Atualizar dados de funcionário   |
| DELETE | `/employees/{id}`    | Deletar funcionário por ID       |

📦 Exemplo de JSON (POST)
```json
{
  "name": "Matheus Barroso",
  "email": "matheus@email.com",
  "department": {
    "departmentName": "Medicina",
    "departmentCode": "M302"
  }
}
```
Resposta esperada:
```
{
  "status": "success",
  "message": "Funcionário criado com sucesso",
  "data": {
    "id": "b92fe320-5f47-4c53-92fa-d19ebea234ff",
    "name": "Matheus Barroso",
    "email": "matheus@email.com",
    "department": {
      "departmentName": "Medicina",
      "departmentCode": "M302"
    }
  }
}
```

### 🗂 Estrutura do projeto
```text
src
├── config         → Configuração do cliente DynamoDB
├── controller     → Endpoints da API REST
├── entity         → Modelos Employee e Department
├── exception      → Tratamento global de erros
├── repository     → Operações com o DynamoDB usando o EnhancedClient
├── service        → Regras de negócio
└── resources
    └── application.properties
```

## ⚠️ Aviso sobre uso da AWS

Este projeto utiliza o Amazon DynamoDB, que oferece **camada gratuita com limites mensais**.  
É **altamente recomendado** que, após testar o projeto, você:

- Exclua a tabela `Employee` no console do DynamoDB
- Revogue suas chaves se não forem mais usadas
- Monitore o uso pelo [AWS Billing](https://console.aws.amazon.com/billing/home)

Assim, você evita **cobranças indesejadas mesmo estando no Free Tier**.

### 👨‍💻 Autor
- Matheus Barroso
- GitHub: @matbarroso97
