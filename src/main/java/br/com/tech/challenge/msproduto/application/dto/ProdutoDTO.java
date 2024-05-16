package br.com.tech.challenge.msproduto.application.dto;

import br.com.tech.challenge.msproduto.core.domain.Categoria;

import java.math.BigDecimal;

public record ProdutoDTO(Long id,
                         String nome,
                         Categoria categoria,
                         String descricao,
                         BigDecimal preco) {
}
