# SIVEC - Sistema de Controle de Estoque Profissional

O **SIVEC** é uma API REST de alta performance desenvolvida com **Spring Boot 3.4.2** e **Java 17**. O projeto foi refatorado de um sistema monolítico básico para uma arquitetura orientada a domínios (**DDD**), aplicando padrões de projeto modernos e segurança de dados em todas as camadas.

---

## Tecnologias e Decisões Técnicas

### Java 17 + Records
Utilizamos **Java 17** para aproveitar as últimas melhorias da linguagem. A implementação de **Records** para os DTOs garante:
*   **Imutabilidade:** Dados que não mudam durante o transporte, evitando efeitos colaterais.
*   **Código Conciso:** Redução drástica de *boilerplate* (Getters, Setters, Equals, HashCode).

### Spring Boot 3 & Spring Data JPA
*   **Spring Boot 3:** Versão mais estável e performática, com suporte nativo ao Jakarta EE.
*   **PostgreSQL:** Banco de dados relacional robusto para garantir a integridade referencial do estoque.

### Blindagem com Bean Validation (JSR 380)
A API implementa uma camada de "defesa" que valida os dados antes de chegarem à lógica de negócio:
*   `@NotBlank`: Impede produtos sem nome ou descrição.
*   `@Positive`: Garante que preços e estoque façam sentido no mundo real (proibindo valores negativos).

---

## Arquitetura e Padrões de Projeto

### 1. Domain-Driven Design (DDD) Simplificado
O projeto foi organizado em torno do domínio **Produto** (`com.sivec.sivec.produto`). Esta estrutura agrupa:
*   **Entity:** Mapeamento objeto-relacional.
*   **Repository:** Abstração da camada de dados.
*   **Service:** Onde reside a inteligência e as regras de negócio.
*   **Controller:** Porta de entrada e saída (API).

### 2. Padrão DTO (Data Transfer Object)
Separamos a **Entidade do Banco** dos **Dados da API**.
*   `ProdutoRequestDTO`: O que o usuário envia (entrada).
*   `ProdutoResponseDTO`: O que o sistema devolve (saída).
*   **Vantagem:** Segurança. Nunca expomos nossa estrutura de banco de dados diretamente para o mundo externo.

### ⚠️ 3. Tratamento Global de Exceções
Implementamos um **Global Exception Handler** usando `@RestControllerAdvice`. Isso permite que erros técnicos do Java sejam transformados em mensagens amigáveis:
```json
{
  "mensagem": "Erro de validação nos campos",
  "detalhes": [
    "preco: O preço deve ser maior que zero",
    "nome: O nome do produto é obrigatório"
  ]
}
