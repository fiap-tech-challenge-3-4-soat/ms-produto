package br.com.tech.challenge.msproduto.core.usecase;

import br.com.tech.challenge.msproduto.TestObjects;
import br.com.tech.challenge.msproduto.core.domain.Produto;
import br.com.tech.challenge.msproduto.core.gateway.ProdutoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CadastrarProdutoUseCaseTest {
    @Mock
    private ProdutoGateway gateway;
    @InjectMocks
    private CadastrarProdutoUseCase underTest;

    @Test
    void deveriaCadastrarUmProdutoComSucesso() {
        var produto = TestObjects.getProduto(1L);

        when(gateway.salvar(any(Produto.class)))
                .thenReturn(produto);

        underTest.executar(produto);

        verify(gateway).salvar(any(Produto.class));
    }
}