package modelo.veiculo;

import java.math.BigDecimal;

public class Carro extends Veiculo {
    private int numeroPortas;
    private String tipoCombustivel;

    public Carro(String placa, String modelo, String marca, Boolean disponivel, int numeroPortas,BigDecimal valorDiaria, String tipoCombustivel) {
        super(placa, modelo, marca, disponivel, valorDiaria);
        setNumeroPortas(numeroPortas);
        setTipoCombustivel(tipoCombustivel);
    }

    public int getNumeroPortas() {return numeroPortas;}
    public void setNumeroPortas(int numeroPortas) {
        if (numeroPortas == 2 || numeroPortas == 4) {
            this.numeroPortas = numeroPortas;
        } else {
            throw new IllegalArgumentException("Número de portas deve ser 2 ou 4.");
        }
    }

    public String getTipoCombustivel() {return tipoCombustivel;}
    public void setTipoCombustivel(String tipoCombustivel) {
        if (tipoCombustivel.equalsIgnoreCase("gasolina") ||
                tipoCombustivel.equalsIgnoreCase("alcool") ||
                tipoCombustivel.equalsIgnoreCase("flex")) {
            this.tipoCombustivel = tipoCombustivel;
        } else {
            throw new IllegalArgumentException("Tipo de combustível deve ser gasolina, álcool ou flex.");
        }
    }



    @Override
    public String toString() {
        return "Carro{" +
                "numeroPortas=" + numeroPortas +
                ", tipoCombustivel='" + tipoCombustivel + '\'' +
                ", placa='" + placa + '\'' +
                ", modelo='" + modelo + '\'' +
                ", marca='" + marca + '\'' +
                ", disponivel=" + disponivel +
                ", valorDiaria=" + valorDiaria +
                '}';
    }
}