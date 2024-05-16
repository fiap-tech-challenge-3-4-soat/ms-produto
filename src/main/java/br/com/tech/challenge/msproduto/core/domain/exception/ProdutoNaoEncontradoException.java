package br.com.tech.challenge.msproduto.core.domain.exception;

public class ProdutoNaoEncontradoException extends RuntimeException {
    private static final String MENSAGEM = "Produto não encontrado id: %s";

    public ProdutoNaoEncontradoException(Long id) {
        super(String.format(MENSAGEM, id));
    }
}
