package com.estoque.controle.repository;

import com.estoque.controle.model.vendas.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Integer> {
}
