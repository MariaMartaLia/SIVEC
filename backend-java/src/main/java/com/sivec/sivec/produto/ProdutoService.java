package com.sivec.sivec.produto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    // Método para criar um novo produto
    public ProdutoResponseDTO criarProduto(ProdutoRequestDTO produtoDTO) {
        Produto produto = toEntity(produtoDTO); // Converte DTO de requisição para entidade
        Produto produtoSalvo = produtoRepository.save(produto);
        return toResponseDTO(produtoSalvo); // Converte entidade salva para DTO de resposta
    }

    // Método para listar todos os produtos
    public List<ProdutoResponseDTO> listarTodosProdutos() {
        return produtoRepository.findAll().stream()
                .map(this::toResponseDTO) // Converte cada entidade para DTO de resposta
                .collect(Collectors.toList());
    }

    // Método para buscar um produto por ID
    public Optional<ProdutoResponseDTO> buscarProdutoPorId(Long id) {
        return produtoRepository.findById(id)
                .map(this::toResponseDTO); // Converte entidade encontrada para DTO de resposta
    }

    // Método para atualizar um produto existente
    public ProdutoResponseDTO atualizarProduto(Long id, ProdutoRequestDTO produtoDTO) {
        return produtoRepository.findById(id)
                .map(produtoExistente -> {
                    produtoExistente.setNome(produtoDTO.nome());
                    produtoExistente.setDescricao(produtoDTO.descricao());
                    produtoExistente.setPreco(produtoDTO.preco());
                    produtoExistente.setQuantidadeEstoque(produtoDTO.quantidadeEstoque());
                    Produto produtoAtualizado = produtoRepository.save(produtoExistente);
                    return toResponseDTO(produtoAtualizado);
                })
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id)); // Exemplo de tratamento de erro
    }

    // Método para deletar um produto
    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }

    // Métodos de conversão (privados, auxiliares)
    private Produto toEntity(ProdutoRequestDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setNome(produtoDTO.nome());
        produto.setDescricao(produtoDTO.descricao());
        produto.setPreco(produtoDTO.preco());
        produto.setQuantidadeEstoque(produtoDTO.quantidadeEstoque());
        return produto;
    }

    private ProdutoResponseDTO toResponseDTO(Produto produto) {
        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getQuantidadeEstoque()
        );
    }
}
