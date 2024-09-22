package modelo.veiculo;

import java.math.BigDecimal;

public class Veiculo {
    protected String placa;
    protected String modelo;
    protected String marca;
    protected Boolean disponivel;
    protected BigDecimal valorDiaria;


    public String getPlaca() {return placa;}
    public void setPlaca(String placa) {this.placa = placa;}

    public String getModelo() {return modelo;}
    public void setModelo(String modelo) {this.modelo = modelo;}

    public String getMarca() {return marca;}
    public void setMarca(String marca) {this.marca = marca;}

    public Boolean getDisponivel() {return disponivel;}
    public void setDisponivel(Boolean disponivel) {this.disponivel = disponivel;}

    public BigDecimal getValorDiaria() {return valorDiaria;}
    public void setValorDiaria(BigDecimal valorDiaria) {this.valorDiaria = valorDiaria;}
}
