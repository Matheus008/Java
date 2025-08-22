package com.estoque.controle.model.produto;

import jakarta.persistence.*;

@Entity
@Table(name = "produtos_tb")
public class Produto {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "preco")
    private Double preco;

    @Column(name = "valorTotal")
    private Double valorTotal = 0.0;

    @Column(name = "quantidade")
    private int quantidade = 0;

    public void adicionarQuantidadeProduto(int quantidade) {
        this.quantidade += quantidade;
    }

    public void retirarQuantidadeProduto(int quantidade) {

        if (quantidade > 0 && quantidade <= this.quantidade) {
            this.quantidade -= quantidade;
        } else {
            throw new IllegalArgumentException("Quantidade inválida");
        }

    }

    public void calculaValorTotal() {

        this.valorTotal = this.quantidade * this.preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
