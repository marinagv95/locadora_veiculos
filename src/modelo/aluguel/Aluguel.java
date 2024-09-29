package modelo.aluguel;

import modelo.agencia.Agencia;
import modelo.pessoa.Pessoa;
import modelo.veiculo.Veiculo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

public class Aluguel {
    private static Set<String> protocolosExistentes = new HashSet<>();
    private String protocolo;
    private Veiculo veiculo;
    private Pessoa pessoa;
    private Agencia agenciaRetirada;
    private LocalDate dataInicio;
    private LocalTime horaInicio;
    private int diasAlugados;

    public Aluguel(Veiculo veiculo, Pessoa pessoa, Agencia agenciaRetirada,
                   LocalDate dataInicio, LocalTime horaInicio) {
        this.veiculo = veiculo;
        this.pessoa = pessoa;
        this.agenciaRetirada = agenciaRetirada;
        this.dataInicio = dataInicio;
        this.horaInicio = horaInicio;
        this.diasAlugados = 0;
        this.protocolo = gerarProtocolo();
    }

    public String getProtocolo() {return protocolo;}
    public Veiculo getVeiculo() { return veiculo; }
    public Pessoa getPessoa() { return pessoa;}
    public Agencia getAgenciaRetirada() { return agenciaRetirada; }
    public LocalDate getDataInicio() { return dataInicio; }
    public LocalTime getHoraInicio() { return horaInicio; }
    public int getDiasAlugados() {return diasAlugados;}
    public void setDiasAlugados(int diasAlugados) {
        this.diasAlugados = diasAlugados;
    }

    public BigDecimal calcularCustoAluguel(BigDecimal precoPorDia) {
        return precoPorDia.multiply(BigDecimal.valueOf(diasAlugados));
    }

    private String gerarProtocolo() {
        String novoProtocolo;
        do {
            novoProtocolo = "PROTOCOLO-" + System.currentTimeMillis();
        } while (protocolosExistentes.contains(novoProtocolo));
        protocolosExistentes.add(novoProtocolo);
        return novoProtocolo;
    }

    @Override
    public String toString() {
        return "===== Dados do Aluguel =====\n" +
                "Protocolo: " + protocolo + "\n" +
                "Cliente: " + pessoa.getNomePessoa() + "\n" +
                "Veículo: " + veiculo.getModelo() + "\n" +
                "Data de Retirada: " + dataInicio + " às " + horaInicio + "\n" +
                "Agência de Retirada: " + agenciaRetirada.getNomeAgencia() + "\n" +
                "Dias Alugados: " + diasAlugados + "\n" +
                "=================================";
    }
}
