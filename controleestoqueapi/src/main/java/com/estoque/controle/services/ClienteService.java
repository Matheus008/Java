package com.estoque.controle.services;

import com.estoque.controle.repository.ClienteRepository;

public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    public String formatarCpfOuCnpj(String cpfOuCnpj) {



        return  "";
    }
}
