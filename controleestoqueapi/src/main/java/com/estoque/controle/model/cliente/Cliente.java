package com.estoque.controle.model.cliente;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente_tb")
public class Cliente {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nomeCliente")
    private String nomeCliente;

    @Column(name = "cpfOuCnpj")
    private String cpfOuCnpj;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipoCliente")
    private TipoCliente tipoCliente;

}
