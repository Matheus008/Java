package com.estoque.controle.controller;

import com.estoque.controle.model.produto.Produto;
import com.estoque.controle.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    private ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public Produto salvar(@RequestBody Produto produto) {
        produtoRepository.save(produto);
        return produto;
    }

    @DeleteMapping("{id}")
    public void deletar(@PathVariable("id") int id) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (produto.getQuantidade() != 0) {
            throw new IllegalArgumentException("O produto não pode ser excluido, deixe a quantidade do produto zerado!");
        } else {
            produtoRepository.deleteById(id);
        }
    }

    @PutMapping("{id}")
    public void atualizar(@PathVariable("id") int id, @RequestBody Produto produto) {
        produto.setId(id);
        produtoRepository.save(produto);
    }

    @PutMapping("{id}/adicionar/{quantidade}")
    public void adicionar(@PathVariable int id, @PathVariable int quantidade) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encotrado"));
        produto.adicionarQuantidadeProduto(quantidade);
        produto.calculaValorTotal();
        produtoRepository.save(produto);
    }

    @PutMapping("{id}/remover/{quantidade}")
    public void remover(@PathVariable int id, @PathVariable int quantidade) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encotrado"));
        produto.retirarQuantidadeProduto(quantidade);
        produto.calculaValorTotal();
        produtoRepository.save(produto);
    }

    @GetMapping
    public List<Produto> buscar(@RequestParam(value = "nome", required = false) String nome) {
        if (nome != null) {
            return produtoRepository.findByNome(nome);
        }
        return produtoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Produto obterPorId(@PathVariable("id") int id) {
        return produtoRepository.findById(id).orElse(null);
    }
}
