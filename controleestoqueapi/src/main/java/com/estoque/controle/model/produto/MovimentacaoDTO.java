package com.estoque.controle.model.produto;

import com.estoque.controle.model.usuario.Usuario;

public record MovimentacaoDTO(int quantidade, String descricao, int produtoId, TipoMovimentacao tipoMovimentacao) {
}
