package modelo.veiculo;

import java.math.BigDecimal;

public class Carro extends Veiculo {
    private int numeroPortas;
    private String tipoCombustivel;

    public Carro(String placa, String modelo, String marca, Boolean disponivel, BigDecimal valorDiaria) {
        super(placa, modelo, marca, disponivel, valorDiaria);
        this.tipoCombustivel = tipoCombustivel;
        this.numeroPortas = numeroPortas;
    }

    public int getNumeroPortas() {return numeroPortas;}
    public void setNumeroPortas(int numeroPortas) {this.numeroPortas = numeroPortas;}

    public String getTipoCombustivel() {return tipoCombustivel;}
    public void setTipoCombustivel(String tipoCombustivel) {this.tipoCombustivel = tipoCombustivel;}
}
