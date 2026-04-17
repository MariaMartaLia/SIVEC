# SIVEC - Sistema de Inventário e Controle de Estoque 

O **SIVEC** é uma API REST de alta performance desenvolvida para o gerenciamento eficiente de estoques. O projeto foi construído seguindo os padrões mais modernos do ecossistema Java, com foco em **Clean Code**, **Imutabilidade** e **Arquitetura de Camadas Desacoplada**.

---

## Diferenciais Técnicos (Nível Pleno)

Para elevar a qualidade do projeto e garantir a escalabilidade, foram aplicados os seguintes conceitos:

*   **Java 17 Records:** Implementação de **DTOs (Data Transfer Objects)** utilizando Records para garantir a imutabilidade dos dados e um código mais limpo.
*   **Domain-Driven Design (DDD):** Organização de pacotes focada no domínio do negócio, facilitando a manutenção e evolução do sistema.
*   **Separação de Responsabilidades:** Camadas de Controller, Service e Repository rigorosamente isoladas.
*   **Ambiente de Desenvolvimento Robusto:** Desenvolvimento realizado em ambiente **WSL2 (Ubuntu)** com controle de versões via **Git Flow**.

---

## Tecnologias Utilizadas

*   **Backend:** Java 17 (LTS), Spring Boot 3.x, Spring Data JPA.
*   **Banco de Dados:** PostgreSQL (Relacional).
*   **Infraestrutura:** Docker (Containerização do Banco de Dados).
*   **Build & Dependências:** Maven.
*   **Ferramentas:** IntelliJ IDEA, Postman, Git.

---

##  Arquitetura do Sistema

A API segue o padrão de arquitetura em camadas:
1.  **Controller:** Ponto de entrada REST, lidando com requisições e respostas via DTOs.
2.  **Service:** Camada de lógica de negócio e orquestração de dados.
3.  **Repository:** Interface de comunicação com o banco de dados via JPA/Hibernate.
4.  **Model (Entity):** Representação fiel das tabelas do banco de dados PostgreSQL.

---

## Como Executar o Projeto

### 1. Subir o Banco de Dados com Docker
Para garantir a consistência dos dados, utilizamos um container PostgreSQL:
```bash
docker run --name postgres-sivec \
  -e POSTGRES_DB=sivec \
  -e POSTGRES_USER=admin \
  -e POSTGRES_PASSWORD=admin \
  -p 5432:5432 \
  -d postgres
