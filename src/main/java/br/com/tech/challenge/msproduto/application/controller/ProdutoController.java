package br.com.tech.challenge.msproduto.application.controller;

import br.com.tech.challenge.msproduto.application.request.ProdutoRequest;
import br.com.tech.challenge.msproduto.application.response.CadastrarProdutoResponse;
import br.com.tech.challenge.msproduto.application.response.ListarProdutosResponse;
import br.com.tech.challenge.msproduto.application.response.ProdutoResponse;
import br.com.tech.challenge.msproduto.core.gateway.ProdutoGateway;
import br.com.tech.challenge.msproduto.core.usecase.*;
import jakarta.inject.Named;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

@Named
public class ProdutoController {
    public  static final String CACHE_PRODUTOS = "produtosCache";
    private final ProdutoGateway produtoGateway;
    private final ProdutoDataMapper produtoMapper;

    public ProdutoController(ProdutoGateway produtoGateway, ProdutoDataMapper produtoMapper) {
        this.produtoGateway = produtoGateway;
        this.produtoMapper = produtoMapper;
    }

    @Cacheable(value = CACHE_PRODUTOS)
    public ListarProdutosResponse listar() {
        var buscarTodosProdutosUseCase = new ListarProdutosUseCase(this.produtoGateway);

        var produtos = buscarTodosProdutosUseCase.executar();

        return new ListarProdutosResponse(produtoMapper.toList(produtos));
    }

    //TODO adicionar @Cacheable(value = CACHE_PRODUTOS, key = "#id")
    public ProdutoResponse buscar(Long id) {
        var buscarProdutoUseCase = new BuscarProdutoUseCase(this.produtoGateway);

        var produto = buscarProdutoUseCase.executar(id);

        return new ProdutoResponse(produtoMapper.toDTO(produto));
    }

    //TODO adicionar @CacheEvict(value = CACHE_PRODUTOS, allEntries = true)
    public CadastrarProdutoResponse criar(ProdutoRequest request) {
        var cadastrarProdutoUseCase = new CadastrarProdutoUseCase(this.produtoGateway);
        var produto = cadastrarProdutoUseCase.executar(produtoMapper.toDomain(request));

        return new CadastrarProdutoResponse(produto.getId());
    }

    //TODO adicionar @CacheEvict(value = CACHE_PRODUTOS, allEntries = true)
    public ProdutoResponse alterar(Long id, ProdutoRequest request) {
        var alterarProdutoUseCase = new AlterarProdutoUseCase(this.produtoGateway);
        var produto = alterarProdutoUseCase.executar(id, produtoMapper.toDomain(request));

        return new ProdutoResponse(produtoMapper.toDTO(produto));
    }

    //TODO adicionar @CacheEvict(value = CACHE_PRODUTOS, allEntries = true)
    public void excluir(Long id) {
        var excluirProdutoUseCase = new ExcluirProdutoUseCase(this.produtoGateway);
        excluirProdutoUseCase.executar(id);
    }
}
