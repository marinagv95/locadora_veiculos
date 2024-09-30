package modelo.veiculo;

import java.math.BigDecimal;
import java.util.List;

public class Veiculo {
    protected String placa;
    protected String modelo;
    protected String marca;
    protected boolean disponivel;
    protected BigDecimal valorDiaria;

    public Veiculo(String placa, String modelo, String marca, boolean disponivel, BigDecimal valorDiaria) {
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.disponivel = disponivel;
        this.valorDiaria = valorDiaria;
    }

    public void alugarVeiculo() {
        if (!disponivel) {
            throw new IllegalStateException("Veículo não está disponível para aluguel.");
        }
        this.disponivel = false;
    }

    public void devolverVeiculo() {
        this.disponivel = true;
    }

    public boolean estaDisponivel() {
        return disponivel;
    }

    public String getPlaca() {return placa;}
    public void setPlaca(String placa) {this.placa = placa;}

    public String getModelo() {return modelo;}
    public void setModelo(String modelo) {this.modelo = modelo;}

    public String getMarca() {return marca;}
    public void setMarca(String marca) {this.marca = marca;}
    
    public void setDisponivel(boolean disponivel) {this.disponivel = disponivel;}

    public BigDecimal getValorDiaria() {return valorDiaria;}
    public void setValorDiaria(BigDecimal valorDiaria) {this.valorDiaria = valorDiaria;}


    @Override
    public String toString() {
        return "===== Detalhes do Veículo =====\n" +
                "Placa: " + placa + "\n" +
                "Modelo: " + modelo + "\n" +
                "Marca: " + marca + "\n" +
                "Disponível: " + (disponivel ? "Sim" : "Não") + "\n" +
                "Valor da Diária: R$ " + valorDiaria + "\n" +
                "===============================";
    }

}
