package com.estoque.controle.model.produto;

public record MovimentacaoDTO(int quantidade, String descricao, int produtoId, TipoMovimentacao tipoMovimentacao) {
}
