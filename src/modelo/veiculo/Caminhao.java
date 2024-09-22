package modelo.veiculo;

import java.math.BigDecimal;

public class Caminhao extends Veiculo{
    private String capacidadeCarga;

    public Caminhao(String placa, String modelo, String marca, Boolean disponivel, BigDecimal valorDiaria) {
        super(placa, modelo, marca, disponivel, valorDiaria);
        this.capacidadeCarga = capacidadeCarga;
    }

    public String getCapacidadeCarga() {return capacidadeCarga;}
    public void setCapacidadeCarga(String capacidadeCarga) {this.capacidadeCarga = capacidadeCarga;}
}
