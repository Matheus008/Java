package com.estoque.controle.services;

import com.estoque.controle.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Double calcularValorTotal(int quantidade, double preco) {
        return quantidade * preco;
    }
}
