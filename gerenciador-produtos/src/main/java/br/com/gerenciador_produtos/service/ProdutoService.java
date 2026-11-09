package br.com.gerenciador_produtos.service;

import br.com.gerenciador_produtos.model.Produto;
import br.com.gerenciador_produtos.repository.ProdutoRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    // Cadastrar produto
    public Produto cadastrar(Produto produto) {
        return produtoRepository.save(produto);
    }

    // Listar todos os produtos
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    // Consultar produto pelo ID
    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    // Atualizar produto
    public Produto atualizar(Long id, Produto dados) {

        Produto produto = buscarPorId(id);

        produto.setNome(dados.getNome());
        produto.setDescricao(dados.getDescricao());
        produto.setPreco(dados.getPreco());
        produto.setQuantidade(dados.getQuantidade());

        return produtoRepository.save(produto);
    }

    // Excluir produto
    public void excluir(Long id) {
        Produto produto = buscarPorId(id);
        produtoRepository.delete(produto);
    }
}