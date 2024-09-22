package modelo.veiculo;

import java.math.BigDecimal;

public class Moto extends Veiculo{
    private String cilindrada;

    public Moto(String placa, String modelo, String marca, Boolean disponivel, BigDecimal valorDiaria) {
        super(placa, modelo, marca, disponivel, valorDiaria);
        this.cilindrada = cilindrada;
    }

    public String getCilindrada() {return cilindrada;}
    public void setCilindrada(String cilindrada) {this.cilindrada = cilindrada;}
}
