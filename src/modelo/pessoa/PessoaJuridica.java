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
        return "╔══════════════════════════════════════════════╗\n" +
                "║          P E S S O A   J U R Í D I C A       ║\n" +
                "╠══════════════════════════════════════════════╣\n" +
                "  Nome:         " + nomePessoa + "\n" +
                "  Telefone:     " + telefone + "\n" +
                "  Email:        " + email + "\n" +
                "  CNPJ:         " + cnpj + "\n" +
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
