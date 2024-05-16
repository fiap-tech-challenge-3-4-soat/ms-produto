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
class ExcluirProdutoUseCaseTest {
    @Mock
    private ProdutoGateway gateway;

    @InjectMocks
    private ExcluirProdutoUseCase underTest;

    @Test
    void deveriaExcluirUmProdutoComSucesso() {
        var produto = TestObjects.getProduto(1L);

        when(gateway.buscarPorId(anyLong()))
                .thenReturn(Optional.of(produto));
        doNothing().when(gateway)
                .excluir(any(Produto.class));

        underTest.executar(1L);

        verify(gateway).buscarPorId(anyLong());
        verify(gateway).excluir(any(Produto.class));
    }

    @Test
    void deveriaLancarExcecaoQuandoNaoEncontrarOProduto() {
        var idProduto = 2L;

        when(gateway.buscarPorId(anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(ProdutoNaoEncontradoException.class, () ->
                underTest.executar(idProduto));

        verify(gateway).buscarPorId(anyLong());
        verify(gateway, never()).excluir(any(Produto.class));
    }
}