package com.sivec.sivec.produto;

import java.util.List;

/**
 * Record que padroniza as respostas de erro da nossa API.
 */
public record ErroRespostaDTO(
        String mensagem,
        List<String> detalhes
) {}
