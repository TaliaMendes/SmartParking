package com.estacionamento.repositories;

import com.estacionamento.models.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

//Aqui tambem o CRUD é oferecido pelo JPA, e preciso do metodo para buscar a placa que é personalizado.
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    Optional<Veiculo> findByPlaca(String placa);
}
