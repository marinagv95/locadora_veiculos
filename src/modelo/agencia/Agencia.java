package modelo.agencia;

import modelo.endereco.Endereco;

public class Agencia {
    private Long idAgencia;
    private String nomeAgencia;
    private Endereco endereco;

    public Agencia(long idAgencia, String nomeAgencia, Endereco endereco) {
        this.idAgencia = idAgencia;
        this.nomeAgencia = nomeAgencia;
        this.endereco = endereco;
    }

    public String getNomeAgencia() {return nomeAgencia;}
    public void setNomeAgencia(String nomeAgencia) {this.nomeAgencia = nomeAgencia;}

    public Endereco getEndereco() {return endereco;}
    public void setEndereco(Endereco endereco) {this.endereco = endereco;}

    public Long getIdAgencia() {return idAgencia;}
    public void setIdAgencia(Long idAgencia) {this.idAgencia = idAgencia;}

    @Override
    public String toString() {
        return "Agencia{" +
                "idAgencia=" + idAgencia +
                ", nomeAgencia='" + nomeAgencia + '\'' +
                ", endereco=" + endereco +
                '}';
    }
}
