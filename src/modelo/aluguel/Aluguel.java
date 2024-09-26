package modelo.aluguel;

import modelo.agencia.Agencia;
import modelo.pessoa.Pessoa;
import modelo.veiculo.Veiculo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Aluguel {
    private Veiculo veiculo;
    private Pessoa pessoa;
    private Agencia agenciaRetirada;
    private LocalDate dataInicio;
    private LocalTime horaInicio;

    public Aluguel(Veiculo veiculo, Pessoa pessoa, Agencia agenciaRetirada,
                   LocalDate dataInicio, LocalTime horaInicio) {
        this.veiculo = veiculo;
        this.pessoa = pessoa;
        this.agenciaRetirada = agenciaRetirada;
        this.dataInicio = dataInicio;
        this.horaInicio = horaInicio;
    }

    public Veiculo getVeiculo() { return veiculo; }
    public Pessoa getPessoa() { return pessoa;}
    public Agencia getAgenciaRetirada() { return agenciaRetirada; }
    public LocalDate getDataInicio() { return dataInicio; }
    public LocalTime getHoraInicio() { return horaInicio; }




    @Override
    public String toString() {
        return "===== Dados do Aluguel =====\n" +
                "Cliente: " + pessoa.getNomePessoa() + "\n" +
                "Veículo: " + veiculo.getModelo() + "\n" +
                "Data de Retirada: " + dataInicio + " às " + horaInicio + "\n" +
                "Agência de Retirada: " + agenciaRetirada.getNomeAgencia() + "\n" +
                "=================================";
    }
}
