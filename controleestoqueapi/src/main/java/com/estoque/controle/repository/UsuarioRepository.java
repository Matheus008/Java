package com.estoque.controle.repository;

import com.estoque.controle.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import javax.swing.text.html.Option;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    UserDetails findByEmail(String email);

    List<Usuario> findByNomeUsuario(String nomeUsuario);
}
