package com.estoque.controle.controller;

import com.estoque.controle.model.produto.TipoMovimentacao;
import com.estoque.controle.model.relatorios.FiltroRelatorioDTO;
import com.estoque.controle.model.relatorios.RelatorioMovimentacaoDTO;
import com.estoque.controle.model.relatorios.RelatorioVendaDTO;
import com.estoque.controle.services.RelatorioService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/relatorio")
public class RelatorioController {

    private final RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @GetMapping("/movimentacoes")
    public List<RelatorioMovimentacaoDTO> relatorioMovimentacoes(
            @RequestParam(required = false) LocalDateTime inicio,
            @RequestParam(required = false) LocalDateTime fim,
            @RequestParam(required = false) Long idUsuario,
            @RequestParam(required = false) Long idProduto,
            @RequestParam(required = false) TipoMovimentacao tipoMovimentacao
    ) {
        FiltroRelatorioDTO filtro = new FiltroRelatorioDTO(inicio, fim, idUsuario, idProduto, null, tipoMovimentacao);
        return relatorioService.relatioriDeMovimentacao(filtro);
    }

    @GetMapping("/vendas")
    public List<RelatorioVendaDTO> relatorioVendas(
            @RequestParam(required = false) LocalDateTime inicio,
            @RequestParam(required = false) LocalDateTime fim,
            @RequestParam(required = false) Long idUsuario,
            @RequestParam(required = false) Long idproduto,
            @RequestParam(required = false) Long idCliente
    ) {
        FiltroRelatorioDTO filtro = new FiltroRelatorioDTO(inicio, fim, idUsuario, idproduto, idCliente, null);
        return relatorioService.relatorioDeVenda(filtro);
    }

}
