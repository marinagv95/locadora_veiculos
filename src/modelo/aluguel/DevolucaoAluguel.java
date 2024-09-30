package modelo.aluguel;

import modelo.agencia.Agencia;
import modelo.pessoa.Pessoa;
import modelo.pessoa.PessoaFisica;
import modelo.pessoa.PessoaJuridica;
import modelo.veiculo.Caminhao;
import modelo.veiculo.Carro;
import modelo.veiculo.Moto;
import modelo.veiculo.Veiculo;
import util.aluguelUtil.ValidarData;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DevolucaoAluguel {
    private Aluguel aluguel;
    private Agencia agenciaDevolucao;
    private LocalDate dataFim;

    public DevolucaoAluguel(Aluguel aluguel, Agencia agenciaDevolucao, LocalDate dataFim) {
        if (aluguel == null) {
            throw new IllegalArgumentException("O aluguel não pode ser nulo.");
        }
        if (dataFim.isBefore(aluguel.getDataInicio()) ||
                dataFim.isEqual(aluguel.getDataInicio())) {
            throw new IllegalArgumentException("A data de devolução deve ser posterior à data e hora de retirada.");
        }
        this.aluguel = aluguel;
        this.agenciaDevolucao = agenciaDevolucao;
        this.dataFim = dataFim;
    }


    public DevolucaoAluguel() {}

    private int calcularQuantidadeDias() {
        return (int) ChronoUnit.DAYS.between(aluguel.getDataInicio(), dataFim);
    }


    public BigDecimal calcularMulta() {
        if (aluguel == null) {
            throw new IllegalArgumentException("O aluguel não pode ser nulo.");
        }
        LocalDate dataEsperadaDevolucao = aluguel.getDataInicio().plusDays(aluguel.getDiasAlugados());
        int diasAtraso = (int) ChronoUnit.DAYS.between(dataEsperadaDevolucao, dataFim);
        BigDecimal multaPorDia = BigDecimal.valueOf(50.00);
        BigDecimal multaTotal = BigDecimal.ZERO;
        if (diasAtraso > 0) {
            BigDecimal valorTotal = aluguel.calcularTotalAluguel();
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
        BigDecimal valorTotal = aluguel.calcularTotalAluguel();
        BigDecimal multa = calcularMulta();
        BigDecimal valorFinal = valorTotal.add(multa);
        DecimalFormat df = new DecimalFormat("#,##0.00");

        StringBuilder comprovante = new StringBuilder();
        comprovante.append("╔═════════════════════════════════════════════════════════════╗\n")
                    .append("                    Comprovante de Devolução                  ║\n")
                    .append("╠═════════════════════════════════════════════════════════════╣\n")
                          .append(" Protocolo: ").append(aluguel.getProtocolo()).append("\n")
                          .append(" Cliente: ").append(aluguel.getPessoa().getNomePessoa()).append("\n")
                          .append(" Tipo de Cliente: ").append(aluguel.getPessoa() instanceof PessoaFisica ? "Pessoa Física" : "Pessoa Jurídica").append("\n")
                          .append(" Veículo: ").append(aluguel.getVeiculo().getMarca()).append(" ").append(aluguel.getVeiculo().getModelo()).append(" (")
                                .append(aluguel.getVeiculo() instanceof Carro ? "Carro" :
                                aluguel.getVeiculo() instanceof Moto ? "Moto" :
                                aluguel.getVeiculo() instanceof Caminhao ? "Caminhão" : "Outro Veículo").append(")\n")
                          .append(" Placa do Veículo: ").append(aluguel.getVeiculo().getPlaca()).append("\n")
                          .append(" Data de Retirada: ").append(ValidarData.formatarData(aluguel.getDataInicio())).append(" às ").append(aluguel.getHoraInicio()).append("\n")
                          .append(" Data de Devolução: ").append(ValidarData.formatarData(dataFim)).append("\n")
                          .append(" Valor Total (com desconto): R$ ").append(df.format(valorTotal)).append("\n")
                          .append(" Multa por atraso: R$ ").append(df.format(multa)).append("\n")
                          .append(" Valor Final: R$ ").append(df.format(valorFinal)).append("\n")
                          .append(" Agência de Retirada: ").append(aluguel.getAgenciaRetirada().getNomeAgencia()).append("\n")
                          .append(" Agência de Devolução: ").append(agenciaDevolucao.getNomeAgencia()).append("\n")
                    .append("╚═════════════════════════════════════════════════════════════╝");

        return comprovante.toString();
    }
}
