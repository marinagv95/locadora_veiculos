package modelo.veiculo;

import java.math.BigDecimal;

public class Moto extends Veiculo{
    private String cilindrada;

    public Moto(String placa, String modelo, String marca, Boolean disponivel, BigDecimal valorDiaria, String cilindrada) {
        super(placa, modelo, marca, disponivel, valorDiaria);
        this.cilindrada = cilindrada;
    }

    public String getCilindrada() {return cilindrada;}
    public void setCilindrada(String cilindrada) {this.cilindrada = cilindrada;}


    @Override
    public String toString() {
        return "╔══════════════════════════════════════════════╗\n" +
                "║                D E T A L H E S               ║\n" +
                "║                   D A   M O T O              ║\n" +
                "╠══════════════════════════════════════════════╣\n" +
                "  Placa:                " + getPlaca() + "\n" +
                "  Modelo:               " + getModelo() + "\n" +
                "  Marca:                " + getMarca() + "\n" +
                "  Disponível:           " + (getDisponivel() ? "Sim" : "Não") + "\n" +
                "  Valor da Diária:      R$ " + String.format("%.2f", getValorDiaria()) + "\n" +
                "  Cilindrada:           " + cilindrada + " cc\n" +
                "╚══════════════════════════════════════════════╝";
    }

}
