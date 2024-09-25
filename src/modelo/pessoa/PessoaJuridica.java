package modelo.pessoa;

import modelo.endereco.Endereco;

public class PessoaJuridica extends Pessoa {
    private String cnpj;

    public PessoaJuridica(String nomePessoa, String telefone, String email, Endereco endereco, String cnpj) {
        super(nomePessoa, telefone, email, endereco);
        this.cnpj = cnpj;
    }


    public String getCnpj() {return cnpj;}
    public void setCnpj(String cnpj) {this.cnpj = cnpj;}
}