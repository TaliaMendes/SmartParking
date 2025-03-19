package com.estacionamento.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "veiculos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String placa;

    private String modelo;
    private String cor;
    private String tipo;

    
    @ManyToOne //aqui um veículo pertence a um cliente
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
}
