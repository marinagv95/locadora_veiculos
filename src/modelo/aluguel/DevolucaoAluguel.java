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

    public DevolucaoAluguel(Aluguel aluguel, Agencia agenciaDevolucao, LocalDate dataFim) {
        if (dataFim.isBefore(aluguel.getDataInicio()) ||
                (dataFim.isEqual(aluguel.getDataInicio()))) {
            throw new IllegalArgumentException("A data de devolução devem ser posteriores à data e hora de retirada.");
        }
        this.aluguel = aluguel;
        this.agenciaDevolucao = agenciaDevolucao;
        this.dataFim = dataFim;
    }

    public DevolucaoAluguel() {}

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
        LocalDate dataEsperadaDevolucao = aluguel.getDataInicio().plusDays(aluguel.getDiasAlugados());
        int diasAtraso = (int) ChronoUnit.DAYS.between(dataEsperadaDevolucao, dataFim);
        BigDecimal multaPorDia = BigDecimal.valueOf(50.00);
        BigDecimal multaTotal = BigDecimal.ZERO;
        if (diasAtraso > 0) {
            BigDecimal valorTotal = calcularTotalAluguel();
            BigDecimal multaProporcional = valorTotal.multiply(BigDecimal.valueOf(0.10)); // 10% do total
            multaTotal = multaProporcional.add(multaPorDia.multiply(BigDecimal.valueOf(diasAtraso)));
        }
        return multaTotal;
    }


    public Aluguel getAluguel() {return aluguel;}

    public Agencia getAgenciaDevolucao() {return agenciaDevolucao;}
    public void setAgenciaDevolucao(Agencia agenciaDevolucao) {this.agenciaDevolucao = agenciaDevolucao;}

    public LocalDate getDataFim() {return dataFim;}
    public void setDataFim(LocalDate dataFim) {this.dataFim = dataFim;}
    public Pessoa getPessoa() {
        return aluguel.getPessoa();
    }



    public String gerarComprovante() {
        BigDecimal valorTotal = calcularTotalAluguel();
        BigDecimal multa = calcularMulta();
        BigDecimal valorFinal = valorTotal.add(multa);

        StringBuilder comprovante = new StringBuilder();
        comprovante.append("╔══════════════════════════════════════════════════╗\n")
                .append("              Comprovante de Devolução           ║\n")
                .append("╠══════════════════════════════════════════════════╣\n")
                .append(" Protocolo: ").append(aluguel.getProtocolo()).append("\n")
                .append(" Cliente: ").append(aluguel.getPessoa().getNomePessoa()).append("\n")
                .append(" Veículo: ").append(aluguel.getVeiculo().getModelo()).append("\n")
                .append(" Data de Retirada: ").append(aluguel.getDataInicio()).append(" às ").append(aluguel.getHoraInicio()).append("\n")
                .append(" Data de Devolução: ").append(dataFim).append("\n")
                .append(" Valor Total: R$ ").append(valorTotal).append("\n")
                .append(" Multa por atraso: R$ ").append(multa).append("\n")
                .append(" Valor Final: R$ ").append(valorFinal).append("\n")
                .append(" Agência de Retirada: ").append(aluguel.getAgenciaRetirada().getNomeAgencia()).append("\n")
                .append(" Agência de Devolução: ").append(agenciaDevolucao.getNomeAgencia()).append("\n")
                .append("╚══════════════════════════════════════════════════╝");

        return comprovante.toString();
    }
}
