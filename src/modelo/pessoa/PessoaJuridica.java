package modelo.pessoa;

import exception.pessoaException.CNPJInvalidoException;
import exception.pessoaException.EmailInvalidoException;
import modelo.endereco.Endereco;
import util.pessoaUtil.ValidarCNPJ;

public class PessoaJuridica extends Pessoa {
    private String cnpj;

    public PessoaJuridica(String nomePessoa, String telefone, String email, Endereco endereco, String cnpj) throws CNPJInvalidoException, EmailInvalidoException {
        super(nomePessoa, telefone, email, endereco);
        setCnpj(cnpj);
    }


    public String getCnpj() {return cnpj;}
    public void setCnpj(String cnpj) throws CNPJInvalidoException {
        if (!ValidarCNPJ.validarCNPJ(cnpj)) {
            throw new CNPJInvalidoException();
        }
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        return "===== Detalhes da Pessoa Jurídica =====\n" +
                "Nome: " + nomePessoa + "\n" +
                "Telefone: " + telefone + "\n" +
                "Email: " + email + "\n" +
                "CNPJ: " + cnpj + "\n" +
                "========================================";
    }
}
