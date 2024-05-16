package br.com.tech.challenge.msproduto.application.controller;

import br.com.tech.challenge.msproduto.TestObjects;
import br.com.tech.challenge.msproduto.application.dto.ProdutoDTO;
import br.com.tech.challenge.msproduto.application.request.ProdutoRequest;
import br.com.tech.challenge.msproduto.core.domain.Produto;
import br.com.tech.challenge.msproduto.core.domain.exception.ProdutoNaoEncontradoException;
import br.com.tech.challenge.msproduto.core.gateway.ProdutoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProdutoControllerTest {
    private Produto produto;

    private ProdutoDTO produtoDTO;

    @Mock
    private ProdutoGateway produtoGateway;

    @Mock
    private ProdutoDataMapper produtoMapper;

    @Mock
    private ProdutoRequest produtoRequest;

    @InjectMocks
    private ProdutoController underTest;

    @BeforeEach
    void setUp() {
        this.produto = TestObjects.getProduto(1L);
        this.produtoDTO = TestObjects.getProdutoDTO(produto);
    }

    @Test
    void deveriaListarProdutosComSucesso() {
        when(produtoGateway.buscarTodos())
                .thenReturn(List.of(produto));
        when(produtoMapper.toList(anyList()))
                .thenReturn(List.of(produtoDTO));

        var response = underTest.listar();

        assertThat(response.getProdutos()).contains(produtoDTO);

        verify(produtoGateway).buscarTodos();
        verify(produtoMapper).toList(anyList());
    }

    @Test
    void deveriaBuscarUmProdutoComSucesso() {
        when(produtoGateway.buscarPorId(anyLong()))
                .thenReturn(Optional.of(produto));
        when(produtoMapper.toDTO(produto))
                .thenReturn(produtoDTO);

        var response = underTest.buscar(produto.getId());

        assertThat(response.getProduto()).isEqualTo(produtoDTO);

        verify(produtoGateway).buscarPorId(anyLong());
        verify(produtoMapper).toDTO(produto);
    }

    @Test
    void deveriaFalharQuandoNaoEncontrarUmProduto() {
        var idProduto = produto.getId();
        when(produtoGateway.buscarPorId(anyLong()))
                .thenReturn(Optional.empty());

        var exception = assertThrows(ProdutoNaoEncontradoException.class, () -> underTest.buscar(idProduto));

        assertThat(exception.getMessage()).contains(idProduto.toString());

        verify(produtoGateway).buscarPorId(anyLong());
        verify(produtoMapper, never()).toDTO(produto);
    }

    @Test
    void deveriaCadastrarUmProdutoComSucesso() {
        when(produtoGateway.salvar(produto))
                .thenReturn(produto);
        when(produtoMapper.toDomain(produtoRequest))
                .thenReturn(produto);

        var response = underTest.criar(produtoRequest);

        assertThat(response.getIdProduto()).isEqualTo(produto.getId());

        verify(produtoGateway).salvar(produto);
        verify(produtoMapper).toDomain(produtoRequest);
    }

    @Test
    void deveriaAlterarUmProdutoComSucesso() {
        when(produtoGateway.buscarPorId(anyLong()))
                .thenReturn(Optional.of(produto));
        when(produtoGateway.salvar(any(Produto.class)))
                .thenReturn(produto);
        when(produtoMapper.toDomain(produtoRequest))
                .thenReturn(produto);
        when(produtoMapper.toDTO(produto))
                .thenReturn(produtoDTO);

        var response = underTest.alterar(produto.getId(), produtoRequest);

        assertThat(response.getProduto()).isEqualTo(produtoDTO);

        verify(produtoGateway).buscarPorId(anyLong());
        verify(produtoGateway).salvar(any(Produto.class));
        verify(produtoMapper).toDTO(produto);
        verify(produtoMapper).toDomain(produtoRequest);
    }

    @Test
    void naoDeveriaAlterarUmProdutoInvalido() {
        var idProduto = produto.getId();
        when(produtoGateway.buscarPorId(anyLong()))
                .thenReturn(Optional.empty());

        var exception = assertThrows(ProdutoNaoEncontradoException.class, () -> underTest.alterar(idProduto, produtoRequest));

        assertThat(exception.getMessage()).contains(idProduto.toString());

        verify(produtoGateway).buscarPorId(anyLong());
        verify(produtoGateway, never()).salvar(any(Produto.class));
        verify(produtoMapper).toDomain(produtoRequest);
        verify(produtoMapper, never()).toDTO(produto);
    }

    @Test
    void deveriaExcluirUmProdutoComSucesso() {
        when(produtoGateway.buscarPorId(anyLong()))
                .thenReturn(Optional.of(produto));
        doNothing().when(produtoGateway).excluir(produto);

        underTest.excluir(produto.getId());

        verify(produtoGateway).buscarPorId(anyLong());
        verify(produtoGateway).excluir(produto);
    }

    @Test
    void naoDeveriaExcluirUmProdutoInvalido() {
        var idProduto = produto.getId();
        when(produtoGateway.buscarPorId(anyLong()))
                .thenReturn(Optional.empty());

        var exception = assertThrows(ProdutoNaoEncontradoException.class, () -> underTest.excluir(idProduto));

        assertThat(exception.getMessage()).contains(idProduto.toString());

        verify(produtoGateway).buscarPorId(anyLong());
        verify(produtoGateway, never()).excluir(produto);
    }
}