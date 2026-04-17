package com.sivec.sivec.produto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos" ) // Define o endpoint base para este controlador
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    // Endpoint para criar um novo produto
    @PostMapping
    public ResponseEntity<ProdutoResponseDTO>  criarProduto(@Valid @RequestBody ProdutoRequestDTO produtoDTO) {
        ProdutoResponseDTO novoProduto = produtoService.criarProduto(produtoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }


    // Endpoint para listar todos os produtos
    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listarTodosProdutos() {
        List<ProdutoResponseDTO> produtos = produtoService.listarTodosProdutos();
        return ResponseEntity.ok(produtos);
    }

    // Endpoint para buscar um produto por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarProdutoPorId(@PathVariable Long id) {
        return produtoService.buscarProdutoPorId(id)
                .map(ResponseEntity::ok) // Se encontrar, retorna 200 OK com o DTO
                .orElse(ResponseEntity.notFound().build()); // Se não encontrar, retorna 404 Not Found
    }

    // Endpoint para atualizar um produto existente
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoRequestDTO produtoDTO) {
        try {
            ProdutoResponseDTO produtoAtualizado = produtoService.atualizarProduto(id, produtoDTO);
            return ResponseEntity.ok(produtoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // Em um caso real, trataríamos exceções mais específicas
        }
    }

    // Endpoint para deletar um produto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    }
}
