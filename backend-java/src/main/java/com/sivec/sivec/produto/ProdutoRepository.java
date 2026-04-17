package com.sivec.sivec.produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // O Spring Data JPA já fornece os métodos CRUD básicos (save, findById, findAll, deleteById, etc.)
    // Você pode adicionar métodos personalizados aqui se precisar de consultas específicas,
    // como findByNome(String nome) ou findByPrecoGreaterThan(BigDecimal preco);
}
