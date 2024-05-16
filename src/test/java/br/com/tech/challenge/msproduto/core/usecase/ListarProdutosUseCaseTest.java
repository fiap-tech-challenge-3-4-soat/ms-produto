package br.com.tech.challenge.msproduto.core.usecase;

import br.com.tech.challenge.msproduto.TestObjects;
import br.com.tech.challenge.msproduto.core.gateway.ProdutoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListarProdutosUseCaseTest {
    @Mock
    private ProdutoGateway gateway;
    @InjectMocks
    private ListarProdutosUseCase underTest;

    @Test
    void deveriaListarProdutosComSucesso() {
        var produto = TestObjects.getProduto(1L);

        when(gateway.buscarTodos())
                .thenReturn(List.of(produto));

        var response = underTest.executar();

        assertThat(response).hasSize(1);
        verify(gateway).buscarTodos();
    }
}