package com.estacionamento.services;

import com.estacionamento.models.RegistroEntradaSaida;
import com.estacionamento.models.Veiculo;
import com.estacionamento.repositories.RegistroEntradaSaidaRepository;
import com.estacionamento.repositories.VeiculoRepository;
import com.estacionamento.dto.ConsultaRegistroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class RegistroService {

    @Autowired
    private RegistroEntradaSaidaRepository registroRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    // Cobra 5 reais pela hora 
    private final double tarifaHora = 5.0;

    // Registra a entrada do veículo
    public RegistroEntradaSaida registrarEntrada(String placa) {
        Veiculo veiculo = veiculoRepository.findByPlaca(placa).orElseThrow(() -> new RuntimeException("Veículo não cadastrado!"));
        RegistroEntradaSaida registro = new RegistroEntradaSaida();
        registro.setVeiculo(veiculo);               // aqui passa  a placa do veículo que deu a entrada
        registro.setDataEntrada(LocalDateTime.now()); // passa a data que o veículo deu entrada
        
        return registroRepository.save(registro); 
    }

    // Registra a saída do veículo, mas so libera a saida se o pagamento for feito
    public RegistroEntradaSaida registrarSaida(String placa) {
        RegistroEntradaSaida registro = registroRepository.findFirstByVeiculoPlacaAndDataSaidaIsNull(placa)
                .orElseThrow(() -> new RuntimeException("Registro de entrada não encontrado para esse veículo"));

        if (!Boolean.TRUE.equals(registro.getPagamentoConfirmado())) {
            throw new RuntimeException("Pagamento não confirmado. Saída não liberada.");
        }

        registro.setDataSaida(LocalDateTime.now()); // data de saida do veiculo
        long minutos = Duration.between(registro.getDataEntrada(), registro.getDataSaida()).toMinutes();     //calculo do valor a ser pago
        double horas = Math.ceil(minutos / 60.0);
        registro.setValorPago(horas * tarifaHora);
        
        return registroRepository.save(registro);
    }

    // Confirma o pagamento do cliente, atribuindo verdadeiro ao campo de pagamento
    public RegistroEntradaSaida confirmarPagamento(String placa) {
        RegistroEntradaSaida registro = registroRepository.findFirstByVeiculoPlacaAndDataSaidaIsNull(placa.trim().toUpperCase())
                .orElseThrow(() -> new RuntimeException("Registro de entrada não encontrado para esse veículo"));

        registro.setPagamentoConfirmado(true);
        return registroRepository.save(registro);
    }

    // Retorna o tempo e valor para o cliente pagar
    public ConsultaRegistroDTO consultarRegistro(String placa) {
        RegistroEntradaSaida registro = registroRepository.findFirstByVeiculoPlacaAndDataSaidaIsNull(placa)
                .orElseThrow(() -> new RuntimeException("Registro de entrada não encontrado para o veículo"));

        long minutos = Duration.between(registro.getDataEntrada(), LocalDateTime.now()).toMinutes();
        double horas = Math.ceil(minutos / 60.0);
        double valor = horas * tarifaHora;
        
        return new ConsultaRegistroDTO(
            registro.getId(),
            registro.getVeiculo().getPlaca(),
            registro.getDataEntrada(), 
            minutos,
            valor);
    }

}
