package br.com.tech.challenge.msproduto.infrastructure.http.resource.v1;

import br.com.tech.challenge.msproduto.application.controller.ProdutoController;
import br.com.tech.challenge.msproduto.application.request.ProdutoRequest;
import br.com.tech.challenge.msproduto.application.response.CadastrarProdutoResponse;
import br.com.tech.challenge.msproduto.application.response.ListarProdutosResponse;
import br.com.tech.challenge.msproduto.application.response.ProdutoResponse;
import br.com.tech.challenge.msproduto.infrastructure.http.resource.v1.openapi.ProdutoResourceOpenApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/produtos")
public class ProdutoResource implements ProdutoResourceOpenApi {
    private final ProdutoController controller;

    @Override
    @GetMapping
    public ResponseEntity<ListarProdutosResponse> listar() {
        var resposta = controller.listar();

        return ResponseEntity.ok(resposta);
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<ProdutoResponse> buscar(@PathVariable Long id) {
        var resposta = controller.buscar(id);

        return ResponseEntity.ok(resposta);
    }

    @Override
    @PostMapping
    public ResponseEntity<CadastrarProdutoResponse> criar(@RequestBody ProdutoRequest request) {
        var resposta = controller.criar(request);

        return new ResponseEntity<>(resposta, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("{id}")
    public ResponseEntity<ProdutoResponse> alterar(@PathVariable Long id, @RequestBody ProdutoRequest request) {
        var resposta = controller.alterar(id, request);

        return ResponseEntity.ok(resposta);
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        controller.excluir(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
