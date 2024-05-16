package br.com.tech.challenge.msproduto.core.usecase;

import br.com.tech.challenge.msproduto.core.domain.Produto;
import br.com.tech.challenge.msproduto.core.gateway.ProdutoGateway;

public class CadastrarProdutoUseCase {
    private final ProdutoGateway gateway;

    public CadastrarProdutoUseCase(ProdutoGateway gateway) {
        this.gateway = gateway;
    }

    public Produto executar(Produto produto) {
        return gateway.salvar(produto);
    }
}
