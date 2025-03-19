// package com.estacionamento.repositories;

// import com.estacionamento.models.RegistroEntradaSaida;
// import com.estacionamento.Interfaces.RegistroEntradaSaidaInterface;
// import jakarta.persistence.EntityManager;
// import jakarta.persistence.PersistenceContext;
// import jakarta.transaction.Transactional;
// import org.springframework.stereotype.Repository;
// import java.util.List;
// import java.util.Optional;

// @Repository
// @Transactional
// public class RegistroEntradaSaidaRepository implements RegistroEntradaSaidaInterface {

//     @PersistenceContext
//     private EntityManager entityManager;

//     @Override
//     public RegistroEntradaSaida save(RegistroEntradaSaida registro) {
//         entityManager.persist(registro);
//         return registro;
//     }

//     @Override
//     public Optional<RegistroEntradaSaida> findById(Long id) {
//         RegistroEntradaSaida registro = entityManager.find(RegistroEntradaSaida.class, id);
//         return Optional.ofNullable(registro);
//     }

//     @Override
//     public List<RegistroEntradaSaida> findAll() {
//         return entityManager.createQuery("SELECT r FROM RegistroEntradaSaida r", RegistroEntradaSaida.class)
//                             .getResultList();
//     }

//     @Override
//     public Optional<RegistroEntradaSaida> findFirstByVeiculoPlacaAndDataSaidaIsNull(String placa) {
//         List<RegistroEntradaSaida> resultados = entityManager.createQuery(
//             "SELECT r FROM RegistroEntradaSaida r WHERE r.veiculo.placa = :placa AND r.dataSaida IS NULL",
//             RegistroEntradaSaida.class)
//             .setParameter("placa", placa)
//             .getResultList();
//         return resultados.stream().findFirst();
//     }

//     @Override
//     public Long countByDataExitIsNull() {
//         return entityManager.createQuery(
//             "SELECT COUNT(r) FROM RegistroEntradaSaida r WHERE r.dataSaida IS NULL", Long.class)
//             .getSingleResult();
//     }

//     @Override
//     public RegistroEntradaSaida update(RegistroEntradaSaida registro) {
//         return entityManager.merge(registro);
//     }

//     @Override
//     public void delete(RegistroEntradaSaida registro) {
//         entityManager.remove(entityManager.contains(registro) ? registro : entityManager.merge(registro));
//     }
// }

package com.estacionamento.repositories;

import com.estacionamento.models.RegistroEntradaSaida;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RegistroEntradaSaidaRepository extends JpaRepository<RegistroEntradaSaida, Long> {

    // È para encontrar o primeiro registro de entrada sem data de saída
    Optional<RegistroEntradaSaida> findFirstByVeiculoPlacaAndDataSaidaIsNull(String placa);

    // Contar o número de registros com data de saída nula
    Long countByDataSaidaIsNull();
}