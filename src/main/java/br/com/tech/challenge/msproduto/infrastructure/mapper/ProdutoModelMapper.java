package br.com.tech.challenge.msproduto.infrastructure.mapper;

import br.com.tech.challenge.msproduto.core.domain.Produto;
import br.com.tech.challenge.msproduto.core.domain.vo.Descricao;
import br.com.tech.challenge.msproduto.core.domain.vo.Nome;
import br.com.tech.challenge.msproduto.core.domain.vo.Preco;
import br.com.tech.challenge.msproduto.infrastructure.persistence.model.ProdutoModel;
import org.springframework.stereotype.Component;

@Component
public class ProdutoModelMapper {
    public Produto toDomain(ProdutoModel produto) {
        return Produto.builder()
                .id(produto.getId())
                .nome(new Nome(produto.getNome()))
                .categoria(produto.getCategoria())
                .descricao(new Descricao(produto.getDescricao()))
                .preco(new Preco(produto.getPreco()))
                .build();
    }

    public ProdutoModel toModel(Produto produto) {
        return ProdutoModel.builder()
                .id(produto.getId())
                .nome(produto.getNome().toString())
                .categoria(produto.getCategoria())
                .descricao(produto.getDescricao().toString())
                .preco(produto.getPreco().getValor())
                .build();
    }
}
