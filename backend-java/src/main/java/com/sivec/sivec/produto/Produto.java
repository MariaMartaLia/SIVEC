package com.sivec.sivec.produto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "produto") //Nome da tabela no banco de dados
@Data//Gera getters, setters, toString, equals e hasCode do lombok
@NoArgsConstructor //Construtor de Argumentos , necessario para JPA
@AllArgsConstructor//Construtor com todos os argumentos
public class Produto{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Estrategia de geraçãpo de id
    private Long id;

    @Column(nullable = false)//Campo não pode ser nulo
    private String nome;

    private String descricao;

    @Column(nullable = false)
    private BigDecimal preco;

    @Column(name ="quantidade_estoque", nullable = false)
    private Integer quantidadeEstoque;
}