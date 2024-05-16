package br.com.tech.challenge.msproduto.application.repository;

import br.com.tech.challenge.msproduto.core.domain.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository {
    Optional<Produto> findById(Long id);
    List<Produto> findAll();
    Produto save(Produto produto);
    void delete(Produto produto);
}
