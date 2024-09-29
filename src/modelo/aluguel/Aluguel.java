package modelo.aluguel;

import modelo.agencia.Agencia;
import modelo.pessoa.Pessoa;
import modelo.pessoa.PessoaFisica;
import modelo.pessoa.PessoaJuridica;
import modelo.veiculo.Veiculo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
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

    public BigDecimal calcularCustoAluguel() {
        return veiculo.getValorDiaria().multiply(BigDecimal.valueOf(diasAlugados));
    }

    public LocalDate calcularDataDevolucao() {
        return dataInicio.plusDays(diasAlugados);
    }

    private String gerarProtocolo() {
        String novoProtocolo;
        do {
            novoProtocolo = "PROTOCOLO-" + System.currentTimeMillis();
        } while (protocolosExistentes.contains(novoProtocolo));
        protocolosExistentes.add(novoProtocolo);
        return novoProtocolo;
    }

    public BigDecimal calcularTotalAluguel() {
        if (this == null) {
            throw new IllegalArgumentException("O aluguel não pode ser nulo.");
        }

        BigDecimal total = veiculo.getValorDiaria().multiply(BigDecimal.valueOf(diasAlugados));

        if (pessoa instanceof PessoaFisica && diasAlugados > 5) {
            total = total.multiply(BigDecimal.valueOf(0.95)); // 5% de desconto
        } else if (pessoa instanceof PessoaJuridica && diasAlugados > 3) {
            total = total.multiply(BigDecimal.valueOf(0.90)); // 10% de desconto
        }

        return total;
    }

    @Override
    public String toString() {
        BigDecimal custoTotal = calcularCustoAluguel();

        StringBuilder sb = new StringBuilder();
        sb.append("╔════════════════════════════════════════════════════════════════╗\n");
        sb.append("                      Comprovante de Aluguel                     ║\n");
        sb.append("╠════════════════════════════════════════════════════════════════╣\n");
        sb.append(" Protocolo: " + protocolo + "\n");
        sb.append(" Cliente: " + pessoa.getNomePessoa() + "\n");
        sb.append(" Veículo: " + veiculo.getModelo() + "\n");
        sb.append(" Data de Retirada: " + dataInicio + " às " + horaInicio + "\n");
        sb.append(" Agência de Retirada: " + agenciaRetirada.getNomeAgencia() + "\n");
        sb.append(" Dias Alugados: " + diasAlugados + "\n");
        sb.append(" Custo Total: R$ " + custoTotal + "\n");
        sb.append(" Data de Devolução: " + calcularDataDevolucao() + "\n");
        sb.append(" ⚠️ O valor pode ser modificado caso haja atraso na devolução!  \n");
        sb.append("╚════════════════════════════════════════════════════════════════╝");

        return sb.toString();
    }


}
