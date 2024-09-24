package modelo.veiculo;

import java.math.BigDecimal;

public class Caminhao extends Veiculo{
    private String capacidadeCarga;

    public Caminhao(String placa, String modelo, String marca, Boolean disponivel,String capacidadeCarga ) {
        super(placa, modelo, marca, disponivel);
        this.capacidadeCarga = capacidadeCarga;
    }

    public String getCapacidadeCarga() {return capacidadeCarga;}
    public void setCapacidadeCarga(String capacidadeCarga) {this.capacidadeCarga = capacidadeCarga;}


    @Override
    public String toString() {
        return "Caminhao{" +
                "placa='" + placa + '\'' +
                ", capacidadeCarga='" + capacidadeCarga + '\'' +
                ", modelo='" + modelo + '\'' +
                ", marca='" + marca + '\'' +
                ", disponivel=" + disponivel +
                '}';
    }
}
