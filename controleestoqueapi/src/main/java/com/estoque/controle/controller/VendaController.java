package com.estoque.controle.controller;

import com.estoque.controle.model.usuario.Usuario;
import com.estoque.controle.model.vendas.Venda;
import com.estoque.controle.model.vendas.VendasDTO;
import com.estoque.controle.repository.UsuarioRepository;
import com.estoque.controle.services.VendaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    private VendaService vendaService;
    private UsuarioRepository usuarioRepository;

    public VendaController(VendaService vendaService, UsuarioRepository usuarioRepository) {
        this.vendaService = vendaService;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public Venda registrar(@RequestBody VendasDTO vendasDTO, Principal principal) {
        Usuario usuario = (Usuario) usuarioRepository.findByEmail(principal.getName());

        return vendaService.registrarVenda(vendasDTO.produtoId(),
                vendasDTO.quantidade(), vendasDTO.clienteId(), usuario);
    }
}
