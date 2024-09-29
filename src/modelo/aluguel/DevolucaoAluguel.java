package modelo.aluguel;

import modelo.agencia.Agencia;
import modelo.pessoa.Pessoa;
import modelo.pessoa.PessoaFisica;
import modelo.pessoa.PessoaJuridica;
import modelo.veiculo.Veiculo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class DevolucaoAluguel {
    private Aluguel aluguel;
    private Agencia agenciaDevolucao;
    private LocalDate dataFim;
    private LocalTime horaFim;

    public DevolucaoAluguel(Aluguel aluguel, Agencia agenciaDevolucao, LocalDate dataFim, LocalTime horaFim) {
        this.aluguel = aluguel;
        this.agenciaDevolucao = agenciaDevolucao;
        this.dataFim = dataFim;
        this.horaFim = horaFim;
    }

    private int calcularQuantidadeDias() {
        return (int) ChronoUnit.DAYS.between(aluguel.getDataInicio(), dataFim);
    }

    public BigDecimal calcularTotalAluguel() {
        int quantidadeDias = calcularQuantidadeDias();
        BigDecimal total = aluguel.getVeiculo().getValorDiaria().multiply(BigDecimal.valueOf(quantidadeDias));

        Pessoa cliente = aluguel.getPessoa();
        if (cliente instanceof PessoaFisica && quantidadeDias > 5) {
            total = total.multiply(BigDecimal.valueOf(0.95)); // 5% de desconto
        } else if (cliente instanceof PessoaJuridica && quantidadeDias > 3) {
            total = total.multiply(BigDecimal.valueOf(0.90)); // 10% de desconto
        }
        return total;
    }

    public BigDecimal calcularMulta() {
        int quantidadeDias = calcularQuantidadeDias();
        BigDecimal multaPorDia = BigDecimal.valueOf(50.00);
        return quantidadeDias > 0 ? multaPorDia.multiply(BigDecimal.valueOf(quantidadeDias)) : BigDecimal.ZERO;
    }


    public Aluguel getAluguel() {return aluguel;}

    public Agencia getAgenciaDevolucao() {return agenciaDevolucao;}
    public void setAgenciaDevolucao(Agencia agenciaDevolucao) {this.agenciaDevolucao = agenciaDevolucao;}

    public LocalDate getDataFim() {return dataFim;}
    public void setDataFim(LocalDate dataFim) {this.dataFim = dataFim;}

    public LocalTime getHoraFim() {return horaFim;}
    public void setHoraFim(LocalTime horaFim) {this.horaFim = horaFim;}


    public String gerarComprovante() {
        BigDecimal valorTotal = calcularTotalAluguel();
        BigDecimal multa = calcularMulta();
        BigDecimal valorFinal = valorTotal.add(multa);

        return "===== Comprovante de Devolução =====\n" +
                "Protocolo: " + aluguel.getProtocolo() + "\n" +
                "Cliente: " + aluguel.getPessoa().getNomePessoa() + "\n" +
                "Veículo: " + aluguel.getVeiculo().getModelo() + "\n" +
                "Data de Retirada: " + aluguel.getDataInicio() + " às " + aluguel.getHoraInicio() + "\n" +
                "Data de Devolução: " + dataFim + " às " + horaFim + "\n" +
                "Valor Total: R$ " + valorTotal + "\n" +
                "Multa: R$ " + multa + "\n" +
                "Valor Final: R$ " + valorFinal + "\n" +
                "Agência de Retirada: " + aluguel.getAgenciaRetirada().getNomeAgencia() + "\n" +
                "Agência de Devolução: " + agenciaDevolucao.getNomeAgencia() + "\n" +
                "=================================";
    }
}
