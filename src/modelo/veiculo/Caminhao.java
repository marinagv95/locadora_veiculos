package modelo.veiculo;

import java.math.BigDecimal;

public class Caminhao extends Veiculo{
    private String capacidadeCarga;

    public Caminhao(String placa, String modelo, String marca, Boolean disponivel, BigDecimal valorDiaria, String capacidadeCarga) {
        super(placa, modelo, marca, disponivel,valorDiaria);
        this.capacidadeCarga = capacidadeCarga;
    }

    public String getCapacidadeCarga() {return capacidadeCarga;}
    public void setCapacidadeCarga(String capacidadeCarga) {this.capacidadeCarga = capacidadeCarga;}


    @Override
    public String toString() {
        return "╔══════════════════════════════════════════════╗\n" +
                "║              D E T A L H E S   D O           ║\n" +
                "║                 C A M I N H Ã O              ║\n" +
                "╠══════════════════════════════════════════════╣\n" +
                "  Placa:                " + getPlaca() + "\n" +
                "  Modelo:               " + getModelo() + "\n" +
                "  Marca:                " + getMarca() + "\n" +
                "  Disponível:           " + (getDisponivel() ? "Sim" : "Não") + "\n" +
                "  Valor da Diária:      R$ " + String.format("%.2f", getValorDiaria()) + "\n" +
                "  Capacidade de Carga:  " + capacidadeCarga + " toneladas\n" +
                "╚══════════════════════════════════════════════╝";
    }

}
