package modelo.aluguel;

import modelo.veiculo.Veiculo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Aluguel {
    private Veiculo veiculo;
    private LocalDate dataInicio;
    private LocalTime horaInicio;
    private LocalDate dataFim;
    private LocalTime horaFim;
    private int quantidadeDias;


    public Aluguel(Veiculo veiculo, LocalDate dataInicio, LocalTime horaInicio, LocalDate dataFim, LocalTime horaFim) {
        this.veiculo = veiculo;
        this.dataInicio = dataInicio;
        this.horaInicio = horaInicio;
        this.dataFim = dataFim;
        this.horaFim = horaFim;
        this.quantidadeDias = calcularQuantidadeDias();
    }

    public Veiculo getVeiculo() {return veiculo;}

    public LocalDate getDataInicio() {return dataInicio;}
    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
        this.quantidadeDias = calcularQuantidadeDias();
    }

    public LocalTime getHoraInicio() {return horaInicio;}
    public void setHoraInicio(LocalTime horaInicio) {this.horaInicio = horaInicio;}

    public LocalDate getDataFim() {return dataFim;}
    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
        this.quantidadeDias = calcularQuantidadeDias();
    }

    public LocalTime getHoraFim() {return horaFim;}
    public void setHoraFim(LocalTime horaFim) {this.horaFim = horaFim;}

    private int calcularQuantidadeDias() {
        return (int) ChronoUnit.DAYS.between(dataInicio, dataFim);
    }

    public BigDecimal calcularTotalAluguel() {
        return veiculo.getValorDiaria().multiply(BigDecimal.valueOf(quantidadeDias));
    }
}
