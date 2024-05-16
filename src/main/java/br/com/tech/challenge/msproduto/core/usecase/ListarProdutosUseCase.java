package br.com.tech.challenge.msproduto.core.usecase;

import br.com.tech.challenge.msproduto.core.domain.Produto;
import br.com.tech.challenge.msproduto.core.gateway.ProdutoGateway;

import java.util.List;

public class ListarProdutosUseCase {
    private final ProdutoGateway gateway;

    public ListarProdutosUseCase(ProdutoGateway repository) {
        this.gateway = repository;
    }

    public List<Produto> executar() {
        return gateway.buscarTodos();
    }
}
