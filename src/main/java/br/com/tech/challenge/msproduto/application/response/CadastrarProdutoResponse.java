package br.com.tech.challenge.msproduto.application.response;

import lombok.Getter;

@Getter
public class CadastrarProdutoResponse {
    private final Long idProduto;

    public CadastrarProdutoResponse(Long idProduto) {
        this.idProduto = idProduto;
    }
}
