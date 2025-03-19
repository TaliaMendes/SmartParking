// package com.estacionamento.controllers;

// import com.estacionamento.dto.ConsultaRegistroDTO;
// import com.estacionamento.models.RegistroEntradaSaida;
// import com.estacionamento.services.RegistroService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/registros")
// public class RegistroController {

//     @Autowired
//     private RegistroService registroService;

//     @PostMapping("/entrada")
//     public ResponseEntity<RegistroEntradaSaida> registrarEntrada(@RequestParam String placa) {
//         RegistroEntradaSaida registro = registroService.registrarEntrada(placa);
//         return ResponseEntity.ok(registro);
//     }


//     @PostMapping("/saida")
//     public ResponseEntity<RegistroEntradaSaida> registrarSaida(@RequestParam String placa) {
//         RegistroEntradaSaida registro = registroService.registrarSaida(placa);
//         return ResponseEntity.ok(registro);
//     }    

//     @PostMapping("/confirmarPagamento")
//     public ResponseEntity<RegistroEntradaSaida> confirmarPagamento(@RequestParam String placa) {
//         RegistroEntradaSaida registro = registroService.confirmarPagamento(placa);
//         return ResponseEntity.ok(registro);
//     }

//     @GetMapping("/consulta")
//     public ResponseEntity<ConsultaRegistroDTO> consultarRegistro(@RequestParam String placa) {
//         ConsultaRegistroDTO consulta = registroService.consultarRegistro(placa);
//         return ResponseEntity.ok(consulta);
//     }
// }



package com.estacionamento.controllers;

import com.estacionamento.dto.ConsultaRegistroDTO;
import com.estacionamento.models.RegistroEntradaSaida;
import com.estacionamento.services.RegistroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registros")
public class RegistroController {

    @Autowired
    private RegistroService registroService;

    // Registrar entrada de veículo 
    @PostMapping("/entrada")
    public ResponseEntity<RegistroEntradaSaida> registrarEntrada(@RequestParam String placa) {
        try {
            RegistroEntradaSaida registro = registroService.registrarEntrada(placa);
            return ResponseEntity.status(HttpStatus.CREATED).body(registro);  // Status 201 para "Criado"
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);  // Se ocorrer erro, retorna BadRequest
        }
    }

    // Registrar saída de veículo 
    @PostMapping("/saida")
    public ResponseEntity<RegistroEntradaSaida> registrarSaida(@RequestParam String placa) {
        try {
            RegistroEntradaSaida registro = registroService.registrarSaida(placa);
            return ResponseEntity.ok(registro);  
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); 
        }
    }

    // Confirmar pagamento 
    @PostMapping("/confirmarPagamento")
    public ResponseEntity<RegistroEntradaSaida> confirmarPagamento(@RequestParam String placa) {
        try {
            RegistroEntradaSaida registro = registroService.confirmarPagamento(placa);
            return ResponseEntity.ok(registro);  
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); 
        }
    }

    @GetMapping("/consulta")
        public ResponseEntity<ConsultaRegistroDTO> consultarRegistro(@RequestParam String placa) {
        try {
            ConsultaRegistroDTO consulta = registroService.consultarRegistro(placa);
            return ResponseEntity.ok(consulta);  
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); 
        }
    }
}



