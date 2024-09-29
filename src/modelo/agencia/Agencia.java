package modelo.agencia;

import modelo.endereco.Endereco;

public class Agencia {

    private String cnpj;
    private String nomeAgencia;
    private Endereco endereco;

    public Agencia(String cnpj, String nomeAgencia, Endereco endereco) {
        this.cnpj = cnpj;
        this.nomeAgencia = nomeAgencia;
        this.endereco = endereco;
    }


    public String getNomeAgencia() {return nomeAgencia;}
    public void setNomeAgencia(String nomeAgencia) {this.nomeAgencia = nomeAgencia;}

    public Endereco getEndereco() {return endereco;}
    public void setEndereco(Endereco endereco) {this.endereco = endereco;}

    public String getCnpj() {return cnpj;}
    public void setCnpj(String cnpj) {this.cnpj = cnpj;}

    @Override
    public String toString() {
        return "╔══════════════════════════════════════════╗\n" +
                "║             A G E N C I A                ║\n" +
                "╠══════════════════════════════════════════╣\n" +
                "║ CNPJ:    " + cnpj + "\n" +
                "║ Nome da Agência:  " + nomeAgencia + "\n" +
                "╠══════════════════════════════════════════╣\n" +
                "║             E N D E R E Ç O              ║\n" +
                "╠══════════════════════════════════════════╣\n" +
                "  Logradouro:       " + endereco.getLogradouro() + "\n" +
                "  Número:           " + (endereco.getNumero() != null ? endereco.getNumero() : "Não informado") + "\n" +
                "  CEP:              " + endereco.getCEP() + "\n" +
                "  Bairro:           " + endereco.getBairro() + "\n" +
                "  Cidade:           " + endereco.getCidade() + "\n" +
                "  Estado:           " + endereco.getEstado() + "\n" +
                "╚══════════════════════════════════════════╝";
    }



}
