package com.sivec.sivec.produto;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.math.BigDecimal;

// Record para enviar dados de resósta de produto

public record ProdutoResponseDTO(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        Integer quantidadeEstoque
){}