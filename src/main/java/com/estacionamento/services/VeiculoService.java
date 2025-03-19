package com.estacionamento.services;

import com.estacionamento.models.Veiculo;
import com.estacionamento.repositories.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//Realiza o cadastro de um novo carro, se não tiver a placa, ou seja, se ele ja não foi cadastrado antes
@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public Veiculo cadastrarVeiculo(Veiculo veiculo) {
        if (veiculoRepository.findByPlaca(veiculo.getPlaca()).isPresent()) {
            throw new RuntimeException("Veículo com placa já cadastrada!");
        }
        return veiculoRepository.save(veiculo);
    }

}
