// package com.estacionamento.controllers;

// import com.estacionamento.dto.ParkingStatus;
// import com.estacionamento.services.EstacionamentoService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/vagas")
// public class EstacionamentoController {

//     @Autowired
//     private EstacionamentoService estacionamentoService;

//     @GetMapping
//     public ResponseEntity<ParkingStatus> getStatusVagas() {
//         int total = estacionamentoService.getTotalVagas();
//         int ocupadas = estacionamentoService.getVagasOcupadas();
//         int disponiveis = estacionamentoService.getVagasDisponiveis();
//         ParkingStatus status = new ParkingStatus(total, ocupadas, disponiveis);
//         return ResponseEntity.ok(status);
//     }
// }

// @RestController
// public class EstacionamentoController {

//     @Autowired
//     private EstacionamentoService estacionamentoService;

//     // Endpoint para obter o status do estacionamento diretamente
//     @GetMapping("/status-estacionamento")
//     public ResponseEntity<String> getStatusEstacionamento() {
//         int totalVagas = estacionamentoService.getTotalVagas();
//         int vagasOcupadas = estacionamentoService.getVagasOcupadas();
//         int vagasDisponiveis = estacionamentoService.getVagasDisponiveis();

//         // Retorna a informação diretamente como uma string ou poderia ser um objeto JSON
//         String status = String.format("Total Vagas: %d, Vagas Ocupadas: %d, Vagas Disponíveis: %d",
//                 totalVagas, vagasOcupadas, vagasDisponiveis);

//         return ResponseEntity.ok(status);
//     }
// }

package com.estacionamento.controllers;

import com.estacionamento.services.EstacionamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EstacionamentoController {

    @Autowired
    private EstacionamentoService estacionamentoService;

    // Classe interna para representar o status em formato JSON
    public static class StatusEstacionamento {
        private int totalVagas;
        private int vagasOcupadas;
        private int vagasDisponiveis;

        public StatusEstacionamento(int totalVagas, int vagasOcupadas, int vagasDisponiveis) {
            this.totalVagas = totalVagas;
            this.vagasOcupadas = vagasOcupadas;
            this.vagasDisponiveis = vagasDisponiveis;
        }

        public int getTotalVagas() {
            return totalVagas;
        }

        public int getVagasOcupadas() {
            return vagasOcupadas;
        }

        public int getVagasDisponiveis() {
            return vagasDisponiveis;
        }
    }

    // Endpoint para obter o status do estacionamento diretamente em JSON
    @GetMapping("/vagas")
    public StatusEstacionamento getStatusEstacionamento() {
        int totalVagas = estacionamentoService.getTotalVagas();
        int vagasOcupadas = estacionamentoService.getVagasOcupadas();
        int vagasDisponiveis = estacionamentoService.getVagasDisponiveis();

        // Retorna o status como um objeto que o Spring converte automaticamente em JSON
        return new StatusEstacionamento(totalVagas, vagasOcupadas, vagasDisponiveis);
    }
}
