package com.estacionamento.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nome;

  @Column(unique = true, nullable = false)
  private String cpf;

  @Column(nullable = false)
  private String telefone;

  @Column(nullable = false)
  private String endereco;

  @Column(nullable = false)
  private Boolean ativo = true;

  private LocalDateTime dataCadastro = LocalDateTime.now();
}
