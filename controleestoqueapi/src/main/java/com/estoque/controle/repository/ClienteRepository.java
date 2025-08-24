package com.estoque.controle.repository;

import com.estoque.controle.model.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByNomeCliente(String nomeCliente);

    String findByCpfOuCnpj(String cpfOuCnpj);

}
