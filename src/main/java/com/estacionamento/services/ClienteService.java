package com.estacionamento.services;

import com.estacionamento.models.Cliente;
import com.estacionamento.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Se o CPF ja existir não cadastra, pois a pessoa ja fez cadastro. Se não, cria um novo cliente. 
    public Cliente cadastrarCliente(Cliente cliente) {
        if (clienteRepository.findByCpf(cliente.getCpf()).isPresent()) {
            throw new RuntimeException("CPF já cadastrado!");
        }
        return clienteRepository.save(cliente);
    }
}

