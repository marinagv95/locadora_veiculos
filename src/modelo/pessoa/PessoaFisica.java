package modelo.pessoa;

import modelo.endereco.Endereco;

public class PessoaFisica extends Pessoa {
    private String cpf;

    public PessoaFisica(String nomePessoa, String telefone, String email, Endereco endereco, String cpf) {
        super(nomePessoa, telefone, email, endereco);
        this.cpf = cpf;
    }

    public String getCpf() {return cpf;}
    public void setCpf(String cpf) {this.cpf = cpf;}

    @Override
    public String toString() {
        return "===== Detalhes da Pessoa Física =====\n" +
                "Nome: " + nomePessoa + "\n" +
                "Telefone: " + telefone + "\n" +
                "Email: " + email + "\n" +
                "CPF: " + cpf + "\n" +
                "======================================";
    }
}
