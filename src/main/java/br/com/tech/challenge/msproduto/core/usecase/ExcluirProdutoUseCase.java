package br.com.tech.challenge.msproduto.core.usecase;

import br.com.tech.challenge.msproduto.core.domain.exception.ProdutoNaoEncontradoException;
import br.com.tech.challenge.msproduto.core.gateway.ProdutoGateway;

public class ExcluirProdutoUseCase {
    private final ProdutoGateway produtoGateway;

    public ExcluirProdutoUseCase(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    public void executar(Long id) {
        var produto = this.produtoGateway.buscarPorId(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));

        produtoGateway.excluir(produto);
    }
}
