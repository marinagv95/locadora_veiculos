package modelo.pessoa;

import modelo.endereco.Endereco;

public class Pessoa {

    protected String nomePessoa;
    protected String telefone;
    protected String email;
    protected Endereco endereco;
    public Pessoa(String nomePessoa, String telefone, String email, Endereco endereco) {
        this.nomePessoa = nomePessoa;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }


    public String getNomePessoa() {return nomePessoa;}
    public void setNomePessoa(String nomePessoa) {this.nomePessoa = nomePessoa;}

    public String getTelefone() {return telefone;}
    public void setTelefone(String telefone) {this.telefone = telefone;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public Endereco getEndereco() {return endereco;}
    public void setEndereco(Endereco endereco) {this.endereco = endereco;}

    @Override
    public String toString() {
        return "Nome: " + nomePessoa + "\n" +
                "Telefone: " + telefone + "\n" +
                "Email: " + email + "\n" +
                "====================================";
    }
}
