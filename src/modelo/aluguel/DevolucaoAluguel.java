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
    private Veiculo veiculo;
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

    public Aluguel getAluguel() {
        return aluguel;
    }

    public void setAluguel(Aluguel aluguel) {
        this.aluguel = aluguel;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Agencia getAgenciaDevolucao() {
        return agenciaDevolucao;
    }

    public void setAgenciaDevolucao(Agencia agenciaDevolucao) {
        this.agenciaDevolucao = agenciaDevolucao;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }

    public int getQuantidadeDias() {
        return quantidadeDias;
    }

    public void setQuantidadeDias(int quantidadeDias) {
        this.quantidadeDias = quantidadeDias;
    }

    public BigDecimal getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(BigDecimal valorFinal) {
        this.valorFinal = valorFinal;
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
