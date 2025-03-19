package com.estacionamento.dto;

import java.time.LocalDateTime;

public class ConsultaRegistroDTO {
    
    private Long id;
    private String placa;
    private LocalDateTime dataEntrada;
    private long tempoMinutos;
    private double valorPago;
    

    public ConsultaRegistroDTO(Long id, String placa,  LocalDateTime dataEntrada, long tempoMinutos, double valorPago ) {
        this.tempoMinutos = tempoMinutos;
        this.valorPago = valorPago;
        this.id = id;
        this.placa = placa;
        this.dataEntrada = dataEntrada;
    }

    public long getTempoMinutos() {
        return tempoMinutos;
    }

    public void setTempoMinutos(long tempoMinutos) {
        this.tempoMinutos = tempoMinutos;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public Long getId() {
        return id;
    }

    public String getPlaca() {
        return placa;
    }

    public LocalDateTime getDataEntrada() {
        return dataEntrada;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setDataEntrada(LocalDateTime dataEntrada) {
        this.dataEntrada = dataEntrada;
    }
}
