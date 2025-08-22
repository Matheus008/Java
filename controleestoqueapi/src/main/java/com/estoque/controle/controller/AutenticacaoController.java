package com.estoque.controle.controller;

import com.estoque.controle.model.usuario.AutenticacaoDTO;
import com.estoque.controle.model.usuario.LoginResponseDTO;
import com.estoque.controle.model.usuario.RegisterDTO;
import com.estoque.controle.model.usuario.Usuario;
import com.estoque.controle.repository.UsuarioRepository;
import com.estoque.controle.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AutenticacaoDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.gerarToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterDTO data) {
        if(this.repository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedSenha = new BCryptPasswordEncoder().encode(data.senha());

        Usuario novoUsuario = new Usuario(data.email(), encryptedSenha, data.nivelDeUsuario(), data.nomeUsuario());

        this.repository.save(novoUsuario);

        return ResponseEntity.ok().build();
    }
}
