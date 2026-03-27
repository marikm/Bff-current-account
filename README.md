# 🏦 Conta-Corrente BFF (Mobile Banking API)

![Java](https://img.shields.io/badge/Java-21-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0-brightgreen.svg)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue.svg)
![Apache Kafka](https://img.shields.io/badge/Apache%20Kafka-Event%20Driven-black.svg)
![Docker](https://img.shields.io/badge/Docker-Containerized-blue.svg)

## 📌 Sobre o Projeto
Esta é uma API RESTful desenvolvida em **Java 21** e **Spring Boot 3**, aplicando o padrão arquitetural **Backend For Frontend (BFF)**. 

O objetivo do projeto é servir como a camada intermediária entre um aplicativo bancário móvel (Cliente) e os ecossistemas de microsserviços do banco. A API agrega dados complexos de domínio (Contas e Transações) em um único payload otimizado, reduzindo o tráfego de rede e melhorando a performance em conexões móveis (4G/5G).

Além do fluxo síncrono de leitura, o projeto implementa uma **Arquitetura Orientada a Eventos (EDA)** com **Apache Kafka** para processar transações financeiras assíncronas (ex: recebimento de Pix), garantindo que a API principal não seja bloqueada por sistemas externos.

## 🏗️ Arquitetura e Padrões Aplicados
* **BFF (Backend For Frontend):** Agregação de dados sob medida para a renderização da tela inicial do app.
* **DTO Pattern (Data Transfer Object):** Utilização de `records` do Java 21 para garantir imutabilidade e encapsular a estrutura interna do banco de dados (Segurança / Information Hiding).
* **RESTful API (Maturidade Nível 2):** Uso semântico de verbos HTTP, mapeamento orientado a recursos e retorno correto de *Status Codes*.
* **Event-Driven Architecture:** Desacoplamento do fluxo de escrita (depósitos/Pix) utilizando mensageria assíncrona.
* **Transações ACID:** Uso de `@Transactional` para garantir a integridade financeira no PostgreSQL.

## 🛠️ Tecnologias Utilizadas
* **Linguagem:** Java 21
* **Framework:** Spring Boot 3 (Web, Data JPA)
* **Banco de Dados:** PostgreSQL
* **Mensageria:** Apache Kafka & Zookeeper (Spring Kafka)
* **Gerenciamento de Dependências:** Maven
* **Infraestrutura:** Docker & Docker Compose

## 🚀 Como Executar o Projeto

### 1. Subindo a Infraestrutura (Banco de Dados e Kafka)
Certifique-se de ter o Docker instalado e rodando. Na raiz do projeto, execute:
```bash
docker compose up -d
```
### 2. Rodando a Aplicação
Execute a classe principal BffMobileApplication.java na sua IDE ou rode via Maven:

```Bash
./mvnw spring-boot:run
```
A API estará disponível em http://localhost:8080.

## 🧪 Testando o Fluxo Completo
### 1. Fluxo Síncrono (Leitura da Tela Inicial)
Faça uma requisição GET para buscar os dados consolidados da conta e o histórico de transações:
```
GET /api/v1/mobile/bff/home/1
```
**Resposta (JSON otimizado via DTO):**

```
{
  "customerName": "Marina",
  "currentBalance": 1500.50,
  "transactions": [
    // Lista de transações recentes
  ]
}

```
### 2. Fluxo Assíncrono (Simulando o recebimento de um Pix via Kafka)
Para simular um sistema externo enviando um pagamento, abra o terminal e acesse o Producer do Kafka no Docker:

```
docker exec -it kafka kafka-console-producer --topic transaction-events --bootstrap-server localhost:9092
```
Envie o payload do evento (Pressione Enter):

```
{"accountId": 1, "amount": 100.00, "description": "Pix recebido - Maria"}
```
A aplicação irá consumir o evento em tempo real, atualizar o saldo no PostgreSQL de forma transacional e registrar o histórico no console. Consulte o endpoint GET novamente para verificar o saldo atualizado!
