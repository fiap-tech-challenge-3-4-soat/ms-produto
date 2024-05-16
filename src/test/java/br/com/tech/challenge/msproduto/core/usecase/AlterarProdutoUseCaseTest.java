package br.com.tech.challenge.msproduto.core.usecase;

import br.com.tech.challenge.msproduto.TestObjects;
import br.com.tech.challenge.msproduto.core.domain.Produto;
import br.com.tech.challenge.msproduto.core.domain.exception.ProdutoNaoEncontradoException;
import br.com.tech.challenge.msproduto.core.gateway.ProdutoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlterarProdutoUseCaseTest {

    @Mock
    private ProdutoGateway gateway;

    @InjectMocks
    private AlterarProdutoUseCase underTest;

    @Test
    void deveriaAlterarUmProdutoComSucesso() {
        var produto = TestObjects.getProduto(1L);

        when(gateway.buscarPorId(anyLong()))
                .thenReturn(Optional.of(produto));
        when(gateway.salvar(any(Produto.class)))
                .thenReturn(produto);

        underTest.executar(1L, produto);

        verify(gateway).buscarPorId(anyLong());
        verify(gateway).salvar(any(Produto.class));
    }

    @Test
    void deveriaLancarExcecaoQuandoNaoEncontrarOProduto() {
        var produto = TestObjects.getProduto(1L);
        var idProduto = 2L;
        when(gateway.buscarPorId(anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(ProdutoNaoEncontradoException.class, () ->
                underTest.executar(idProduto, produto));

        verify(gateway).buscarPorId(anyLong());
        verify(gateway, never()).salvar(any(Produto.class));
    }
}