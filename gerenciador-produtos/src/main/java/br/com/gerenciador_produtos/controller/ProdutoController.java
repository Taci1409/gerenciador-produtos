package br.com.gerenciador_produtos.controller;

import br.com.gerenciador_produtos.model.Produto;
import br.com.gerenciador_produtos.service.ProdutoService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    // Cadastrar produto
    @PostMapping
    public ResponseEntity<Produto> cadastrar(
            @Valid @RequestBody Produto produto) {

        Produto novoProduto = produtoService.cadastrar(produto);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }

    // Listar produtos
    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos() {

        return ResponseEntity.ok(produtoService.listarTodos());
    }

    // Consultar produto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {

        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }

    // Atualizar produto
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Produto produto) {

        return ResponseEntity.ok(produtoService.atualizar(id, produto));
    }

    // Excluir produto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        produtoService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}