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
        return "Moto{" +
                "cilindrada='" + cilindrada + '\'' +
                ", placa='" + placa + '\'' +
                ", modelo='" + modelo + '\'' +
                ", marca='" + marca + '\'' +
                ", disponivel=" + disponivel +
                ", valorDiaria=" + valorDiaria +
                '}';
    }
}
