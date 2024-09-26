package modelo.aluguel;

import modelo.agencia.Agencia;
import modelo.pessoa.Pessoa;
import modelo.pessoa.PessoaFisica;
import modelo.pessoa.PessoaJuridica;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class DevolucaoAluguel {
    private Aluguel aluguel;
    private Agencia agenciaDevolucao;
    private LocalDate dataFim;
    private LocalTime horaFim;
    private int quantidadeDias;
    private BigDecimal valorFinal;

    public DevolucaoAluguel(Aluguel aluguel, Agencia agenciaDevolucao, LocalDate dataFim, LocalTime horaFim) {
        this.aluguel = aluguel;
        this.agenciaDevolucao = agenciaDevolucao;
        this.dataFim = dataFim;
        this.horaFim = horaFim;
        this.quantidadeDias = calcularQuantidadeDias();
        this.valorFinal = calcularTotalAluguel();
    }

    private int calcularQuantidadeDias() {
        return (int) ChronoUnit.DAYS.between(aluguel.getDataInicio(), dataFim);
    }

    public BigDecimal calcularTotalAluguel() {
        BigDecimal total = aluguel.getVeiculo().getValorDiaria().multiply(BigDecimal.valueOf(quantidadeDias));

        Pessoa cliente = aluguel.getPessoa();
        if (cliente instanceof PessoaFisica && quantidadeDias > 5) {
            total = total.multiply(BigDecimal.valueOf(0.95)); // 5% de desconto
        } else if (cliente instanceof PessoaJuridica && quantidadeDias > 3) {
            total = total.multiply(BigDecimal.valueOf(0.90)); // 10% de desconto
        }
        return total;
    }

    public String gerarComprovante() {
        return "===== Comprovante de Aluguel =====\n" +
                "Cliente: " + aluguel.getPessoa().getNomePessoa() + "\n" +
                "Veículo: " + aluguel.getVeiculo().getModelo() + "\n" +
                "Data de Retirada: " + aluguel.getDataInicio() + " às " + aluguel.getHoraInicio() + "\n" +
                "Data de Devolução: " + dataFim + " às " + horaFim + "\n" +
                "Valor Total: R$ " + valorFinal + "\n" +
                "Agência de Retirada: " + aluguel.getAgenciaRetirada().getNomeAgencia() + "\n" +
                "Agência de Devolução: " + agenciaDevolucao.getNomeAgencia() + "\n" +
                "=================================";
    }
}
