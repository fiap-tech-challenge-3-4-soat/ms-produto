package br.com.tech.challenge.msproduto.application.request;

import java.math.BigDecimal;

public record ProdutoRequest(String nome,
                             String categoria,
                             String descricao,
                             BigDecimal preco) {}
