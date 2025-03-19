// package com.estacionamento.services;

// import com.estacionamento.repositories.RegistroEntradaSaidaRepository;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Service;

// @Service
// public class EstacionamentoService {

//     @Value("${estacionamento.totalVagas}")
//     private int totalVagas;

//     private final RegistroEntradaSaidaRepository registroRepository;

//     public EstacionamentoService(RegistroEntradaSaidaRepository registroRepository) {
//         this.registroRepository = registroRepository;
//     }

//     public int getVagasOcupadas() {
//         return registroRepository.countByDataExitIsNull().intValue();
//     }

//     public int getVagasDisponiveis() {
//         int ocupadas = getVagasOcupadas();
//         return totalVagas - ocupadas;
//     }

//     public int getTotalVagas() {
//         return totalVagas;
//     }
// }




package com.estacionamento.services;

import com.estacionamento.repositories.RegistroEntradaSaidaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EstacionamentoService {

    @Value("${estacionamento.totalVagas}")
    private int totalVagas;

    private final RegistroEntradaSaidaRepository registroRepository;

    public EstacionamentoService(RegistroEntradaSaidaRepository registroRepository) {
        this.registroRepository = registroRepository;
    }

    // Quantas vagas ocupadas
    public int getVagasOcupadas() {
        return registroRepository.countByDataSaidaIsNull().intValue();
    }

    // Quantas vagas disponíveis
    public int getVagasDisponiveis() {
        return totalVagas - getVagasOcupadas();
    }

    // Quantas vagas tem o estacionamento
    public int getTotalVagas() {
        return totalVagas;
    }
}

