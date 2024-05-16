package br.com.tech.challenge.msproduto.infrastructure.http.resource.v1;

import br.com.tech.challenge.msproduto.TestObjects;
import br.com.tech.challenge.msproduto.application.request.ProdutoRequest;
import br.com.tech.challenge.msproduto.core.domain.Categoria;
import br.com.tech.challenge.msproduto.core.domain.Produto;
import br.com.tech.challenge.msproduto.core.gateway.ProdutoGateway;
import br.com.tech.challenge.msproduto.infrastructure.persistence.repository.jpa.ProdutoRepositoryJpa;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProdutoResourceIT {
    private final String PATH = "/v1/produtos";

    private Produto produto;

    @Autowired
    private ProdutoGateway produtoGateway;

    @Autowired
    private ProdutoRepositoryJpa produtoRepositoryJpa;

    @Autowired
    private ObjectMapper jsonMapper;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.produto = TestObjects.getProduto(null);
        this.produto = produtoGateway.salvar(this.produto);
    }

    @AfterEach
    void tearDown() {
        produtoRepositoryJpa.deleteAll();
    }

    @Test
    void deveriaCriarUmProdutoComSucesso() throws Exception {
        var request = new ProdutoRequest("Novo Produto", Categoria.LANCHE.name(), "Descrição de Teste", BigDecimal.TEN);
        var jsonRequest = jsonMapper.writeValueAsString(request);

        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idProduto").isNotEmpty());
    }

    @Test
    void deveriaListarProdutosComSucesso() throws Exception {
        mockMvc.perform(get(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.produtos").isArray())
                .andExpect(jsonPath("$.produtos[0].id").value(produto.getId()));
    }

    @Test
    void deveriaBuscarUmProdutoComSucesso() throws Exception {
        mockMvc.perform(get(PATH + "/{idProduto}", produto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.produto.id").value(produto.getId()))
                .andExpect(jsonPath("$.produto.nome").value(produto.getNome().toString()));
    }

    @Test
    void deveriaFalharQuanoBuscarUmProdutoInexistente() throws Exception {
        mockMvc.perform(get(PATH + "/{idProduto}", 99999)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.mensagem").isString());
    }

    @Test
    void deveriaAlterarUmProdutoComSucesso() throws Exception {
        var request = new ProdutoRequest("Produto Alterado", Categoria.BEBIDA.name(), "Descrição de Teste", BigDecimal.ONE);
        var jsonRequest = jsonMapper.writeValueAsString(request);

        mockMvc.perform(put(PATH + "/{idProduto}", produto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.produto.id").value(produto.getId()))
                .andExpect(jsonPath("$.produto.nome").value(not(produto.getNome().toString())))
                .andExpect(jsonPath("$.produto.categoria").value(not(produto.getCategoria().toString())))
                .andExpect(jsonPath("$.produto.preco").value(not(produto.getPreco())))
                .andExpect(jsonPath("$.produto.nome").value(request.nome()))
                .andExpect(jsonPath("$.produto.categoria").value(request.categoria()))
                .andExpect(jsonPath("$.produto.preco").value(request.preco()));
    }

    @Test
    void deveriaFalharQuanoAlterarUmProdutoInexistente() throws Exception {
        var request = new ProdutoRequest("Produto Alterado", Categoria.BEBIDA.name(), "Descrição de Teste", BigDecimal.ONE);
        var jsonRequest = jsonMapper.writeValueAsString(request);

        mockMvc.perform(put(PATH + "/{idProduto}", 99999)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
                )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.mensagem").isString());
    }

    @Test
    void deveriaExcluirUmProdutoComSucesso() throws Exception {

        mockMvc.perform(delete(PATH + "/{idProduto}", produto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());
    }

    @Test
    void deveriaFalharQuanoExcluirUmProdutoInexistente() throws Exception {
        mockMvc.perform(delete(PATH + "/{idProduto}", 99999)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.mensagem").isString());
    }
}