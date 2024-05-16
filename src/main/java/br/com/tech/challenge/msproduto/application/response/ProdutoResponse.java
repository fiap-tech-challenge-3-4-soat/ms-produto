package br.com.tech.challenge.msproduto.application.response;

import br.com.tech.challenge.msproduto.application.dto.ProdutoDTO;
import lombok.Getter;

@Getter
public class ProdutoResponse {
    private ProdutoDTO produto;

    public ProdutoResponse(ProdutoDTO produto) {
        this.produto = produto;
    }
}
