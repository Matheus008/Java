package com.estoque.controle.controller;

import com.estoque.controle.model.produto.Movimentacao;
import com.estoque.controle.model.produto.MovimentacaoDTO;
import com.estoque.controle.model.usuario.Usuario;
import com.estoque.controle.repository.MovimentacaoRepository;
import com.estoque.controle.repository.UsuarioRepository;
import com.estoque.controle.services.MovimentacaoService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/movimentacao")
public class MovimentacaoController {

    private UsuarioRepository usuarioRepository;
    private MovimentacaoService movimentacaoService;
    private MovimentacaoRepository movimentacaoRepository;

    public MovimentacaoController(UsuarioRepository usuarioRepository, MovimentacaoService movimentacaoService, MovimentacaoRepository movimentacaoRepository) {
        this.movimentacaoService = movimentacaoService;
        this.usuarioRepository = usuarioRepository;
        this.movimentacaoRepository = movimentacaoRepository;
    }

    @PostMapping("/registrar")
    public Movimentacao registrar(@RequestBody MovimentacaoDTO movimentacaoDTO, Principal principal) {
        Usuario usuario = (Usuario) usuarioRepository.findByEmail(principal.getName());

        return movimentacaoService.registrarMovimentacao(movimentacaoDTO.produtoId(),
                movimentacaoDTO.quantidade(),
                movimentacaoDTO.tipoMovimentacao(),
                movimentacaoDTO.descricao(), usuario);
    }

    @GetMapping("produto/{produtoId}")
    public List<Movimentacao> listaPorProduto(@PathVariable int produtoId) {
        return  movimentacaoRepository.findAll()
                .stream()
                .filter(m -> m.getProduto().equals(produtoId))
                .toList();
    }
}
