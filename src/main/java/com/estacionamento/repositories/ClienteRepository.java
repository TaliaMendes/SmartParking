package com.estacionamento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.estacionamento.models.Cliente;

//Aqui so preciso do metodo para buscar se aquele cpj ja foi cadastrado ou não. O CRUD e consequentemente o metodo de Salvar, ja é oferecido pelo jPA
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCpf(String cpf);
}
