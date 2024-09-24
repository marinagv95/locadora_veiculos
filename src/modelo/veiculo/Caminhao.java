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
        return "Caminhao{" +
                "capacidadeCarga='" + capacidadeCarga + '\'' +
                ", placa='" + placa + '\'' +
                ", modelo='" + modelo + '\'' +
                ", marca='" + marca + '\'' +
                ", disponivel=" + disponivel +
                ", valorDiaria=" + valorDiaria +
                '}';
    }
}
