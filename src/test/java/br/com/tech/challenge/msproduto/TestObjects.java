package br.com.tech.challenge.msproduto;

import br.com.tech.challenge.msproduto.application.controller.ProdutoDataMapper;
import br.com.tech.challenge.msproduto.application.dto.ProdutoDTO;
import br.com.tech.challenge.msproduto.core.domain.Categoria;
import br.com.tech.challenge.msproduto.core.domain.Produto;
import br.com.tech.challenge.msproduto.core.domain.vo.Descricao;
import br.com.tech.challenge.msproduto.core.domain.vo.Nome;
import br.com.tech.challenge.msproduto.core.domain.vo.Preco;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

public class TestObjects {
    public static Produto getProduto(Long id) {
        return Produto.builder()
                .id(1L)
                .nome(new Nome("Produto Teste"))
                .categoria(Categoria.LANCHE)
                .descricao(new Descricao("Descrição Produto"))
                .preco(new Preco(BigDecimal.TEN))
                .build();
    }

    public static ProdutoDTO getProdutoDTO(Produto produto) {
        return new ProdutoDataMapper().toDTO(produto);
    }
}
