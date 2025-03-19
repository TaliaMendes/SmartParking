package com.estacionamento.controllers;

import com.estacionamento.models.Veiculo;
import com.estacionamento.services.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Rota para cadastrar novos veículos
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @PostMapping
    public ResponseEntity<Veiculo> cadastrarVeiculo(@RequestBody Veiculo veiculo) {
        Veiculo novoVeiculo = veiculoService.cadastrarVeiculo(veiculo);
        return ResponseEntity.ok(novoVeiculo);
    }

}
