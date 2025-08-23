package com.estoque.controle.controller;

import com.estoque.controle.model.usuario.Usuario;
import com.estoque.controle.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @DeleteMapping("{id}")
    public void deletarUsuario(@PathVariable("id") int id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuarioRepository.deleteById(id);
    }

    @PutMapping("{id}")
    public void atualizarUsuario(@PathVariable int id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        usuarioRepository.save(usuario);
    }

    @GetMapping
    public List<Usuario> buscar(@PathVariable(value = "nome", required = false) String nome) {
        if(nome != null) {
            return  usuarioRepository.findByNomeUsuario(nome);
        }
        return usuarioRepository.findAll();
    }

    @GetMapping("{email}")
    public UserDetails buscarPorEmail(@PathVariable String email) {
        if(email == null) {
            throw new IllegalArgumentException("O campo email está vazio!");
        }
        return usuarioRepository.findByEmail(email);
    }
}
