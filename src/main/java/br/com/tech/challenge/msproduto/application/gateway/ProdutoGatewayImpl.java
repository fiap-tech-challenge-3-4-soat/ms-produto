package br.com.tech.challenge.msproduto.application.gateway;

import br.com.tech.challenge.msproduto.application.repository.ProdutoRepository;
import br.com.tech.challenge.msproduto.core.domain.Produto;
import br.com.tech.challenge.msproduto.core.gateway.ProdutoGateway;
import jakarta.inject.Named;

import java.util.List;
import java.util.Optional;

@Named
public class ProdutoGatewayImpl implements ProdutoGateway {
    private final ProdutoRepository repository;

    public ProdutoGatewayImpl(ProdutoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Produto> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Produto> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public Produto salvar(Produto produto) {
        return repository.save(produto);
    }

    @Override
    public void excluir(Produto produto) {
        repository.delete(produto);
    }
}
