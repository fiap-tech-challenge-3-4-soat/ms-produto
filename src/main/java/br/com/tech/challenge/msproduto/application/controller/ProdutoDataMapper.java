package br.com.tech.challenge.msproduto.application.controller;

import br.com.tech.challenge.msproduto.application.dto.ProdutoDTO;
import br.com.tech.challenge.msproduto.application.request.ProdutoRequest;
import br.com.tech.challenge.msproduto.core.domain.Categoria;
import br.com.tech.challenge.msproduto.core.domain.Produto;
import br.com.tech.challenge.msproduto.core.domain.vo.Descricao;
import br.com.tech.challenge.msproduto.core.domain.vo.Nome;
import br.com.tech.challenge.msproduto.core.domain.vo.Preco;
import jakarta.inject.Named;

import java.util.List;

@Named
public class ProdutoDataMapper {
    public ProdutoDTO toDTO(Produto produto) {
        return new ProdutoDTO(produto.getId(),
                produto.getNome().toString(),
                produto.getCategoria(),
                produto.getDescricao().toString(),
                produto.getPreco().getValor());
    }

    public Produto toDomain(ProdutoRequest request) {
        return Produto.builder()
                .nome(new Nome(request.nome()))
                .categoria(Categoria.valueOf(request.categoria().toUpperCase()))
                .descricao(new Descricao(request.descricao()))
                .preco(new Preco(request.preco()))
                .build();
    }

    public List<ProdutoDTO> toList(List<Produto> produtos) {
        return produtos.stream()
                .map(this::toDTO)
                .toList();
    }
}
