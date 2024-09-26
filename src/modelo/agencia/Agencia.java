package modelo.agencia;

import modelo.endereco.Endereco;

public class Agencia {
    private static long contadorId = 0;

    private Long idAgencia;
    private String nomeAgencia;
    private Endereco endereco;

    public Agencia(String nomeAgencia, Endereco endereco) {
        this.idAgencia = gerarIdUnico();
        this.nomeAgencia = nomeAgencia;
        this.endereco = endereco;
    }

    private synchronized Long gerarIdUnico() {
        return ++contadorId;
    }


    public String getNomeAgencia() {return nomeAgencia;}
    public void setNomeAgencia(String nomeAgencia) {this.nomeAgencia = nomeAgencia;}

    public Endereco getEndereco() {return endereco;}
    public void setEndereco(Endereco endereco) {this.endereco = endereco;}

    public Long getIdAgencia() {return idAgencia;}

    @Override
    public String toString() {
        return "Agencia{" +
                "idAgencia=" + idAgencia +
                ", nomeAgencia='" + nomeAgencia + '\'' +
                ", endereco=" + endereco +
                '}';
    }
}
