package br.com.tech.challenge.msproduto.core.usecase;

import br.com.tech.challenge.msproduto.core.domain.Produto;
import br.com.tech.challenge.msproduto.core.domain.exception.ProdutoNaoEncontradoException;
import br.com.tech.challenge.msproduto.core.gateway.ProdutoGateway;

public class BuscarProdutoUseCase {
    private final ProdutoGateway produtoGateway;

    public BuscarProdutoUseCase(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    public Produto executar(Long id) {
        return produtoGateway.buscarPorId(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
    }
}
