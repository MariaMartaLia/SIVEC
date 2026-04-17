package com.sivec.sivec.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;


public record ProdutoRequestDTO(

        // Record para Record para receber dados de requisição de Produto e  Blindar a API = Validar na porta de entrada (DTO) para garantir que o Banco de Dados só receba dados confiáveis e que a lógica do sistema não quebre.

        @NotBlank(message = "O nome do produto é obrigatório") // Garante que não venha vazio ou só com espaços
        String nome,

        @NotBlank(message = "A descrição é obrigatória")
        String descricao,
        @NotNull(message = "O preço é obrigatório") // Garante que o campo foi enviado
        @Positive(message = "O preço deve ser maior que zero") // Garante que o valor faz sentido no mundo real
        BigDecimal preco,

        @NotNull(message = "A quantidade em estoque é obrigatória")
        @Positive(message = "A quantidade deve ser um número positivo")
        Integer quantidadeEstoque

)
{ }
