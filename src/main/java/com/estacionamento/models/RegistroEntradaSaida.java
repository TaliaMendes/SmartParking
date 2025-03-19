package com.estacionamento.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "registro_entrada_saida")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistroEntradaSaida {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  
  @ManyToOne //chave estrangeira do veiculo
  @JoinColumn(name = "veiculo_id", nullable = false)
  private Veiculo veiculo;

  private LocalDateTime dataEntrada;
  private LocalDateTime dataSaida;
    
  private Double valorPago;


  @Column(nullable = false)
  private Boolean pagamentoConfirmado = false;
}

