package modelo.pessoa;

import exception.pessoaException.CPFInvalidoException;
import exception.pessoaException.EmailInvalidoException;
import modelo.endereco.Endereco;
import util.pessoaUtil.ValidarCPF;

public class PessoaFisica extends Pessoa {
    private String cpf;

    public PessoaFisica(String nomePessoa, String telefone, String email, Endereco endereco, String cpf) throws CPFInvalidoException, EmailInvalidoException {
        super(nomePessoa, telefone, email, endereco);
        setCpf(cpf); // Valida e define o CPF
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) throws CPFInvalidoException {
        if (!ValidarCPF.validarCPF(cpf)) {
            throw new CPFInvalidoException("CPF inválido.");
        }
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "╔══════════════════════════════════════════════╗\n" +
                "║            P E S S O A   F Í S I C A         ║\n" +
                "╠══════════════════════════════════════════════╣\n" +
                "  Nome:         " + nomePessoa + "\n" +
                "  Telefone:     " + telefone + "\n" +
                "  Email:        " + email + "\n" +
                "  CPF:          " + cpf + "\n" +
                "╠══════════════════════════════════════════════╣\n" +
                "║                  E N D E R E Ç O             ║\n" +
                "╠══════════════════════════════════════════════╣\n" +
                " Logradouro:       " + endereco.getLogradouro() + "\n" +
                " Número:           " + (endereco.getNumero() != null ? endereco.getNumero() : "Não informado") + "\n" +
                " CEP:              " + endereco.getCEP() + "\n" +
                " Bairro:           " + endereco.getBairro() + "\n" +
                " Cidade:           " + endereco.getCidade() + "\n" +
                " Estado:           " + endereco.getEstado() + "\n" +
                "╚══════════════════════════════════════════════╝";
    }

}
