package com.sivec.sivec.produto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice // Esta anotação diz ao Spring para monitorar todos os Controllers
public class GlobalExceptionHandler {

    // Este método é chamado sempre que o @Valid do Controller encontrar um erro
    @ExceptionHandler(MethodArgumentNotValidException.class )
    public ResponseEntity<ErroRespostaDTO> tratarErroValidacao(MethodArgumentNotValidException ex) {

        // Extraímos as mensagens amigáveis que você colocou no ProdutoRequestDTO
        List<String> erros = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(erro -> erro.getField() + ": " + erro.getDefaultMessage())
                .collect(Collectors.toList());

        // Criamos o nosso objeto de resposta padronizado
        ErroRespostaDTO resposta = new ErroRespostaDTO("Erro de validação nos campos", erros);

        // Retornamos o erro 400 (Bad Request) com o nosso JSON bonito
        return ResponseEntity.badRequest().body(resposta);
    }
}
