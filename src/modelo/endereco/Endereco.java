package modelo.endereco;

import exception.enderecoException.CEPInvalidoException;
import util.enderecoUtil.ValidarCEP;

public class Endereco {
    private String logradouro;
    private String numero;
    private String cep;
    private String bairro;
    private String cidade;
    private String estado;

    public Endereco(String logradouro, String numero, String cep, String bairro, String cidade, String estado) throws CEPInvalidoException {
        this.logradouro = logradouro;
        setCEP(cep);
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getLogradouro() {return logradouro;}
    public void setLogradouro(String logradouro) {this.logradouro = logradouro;}

    public String getNumero() {return numero;}
    public void setNumero(String numero) {this.numero = numero;}

    public String getCEP() {return cep;}
    public void setCEP(String cep) throws CEPInvalidoException {
        if (!ValidarCEP.validarCEP(cep)) {
            throw new CEPInvalidoException("CEP inválido");
        }
        this.cep = cep;
    }

    public String getBairro() {return bairro;}
    public void setBairro(String bairro) {this.bairro = bairro;}

    public String getCidade() {return cidade;}
    public void setCidade(String cidade) {this.cidade = cidade;}

    public String getEstado() {return estado;}
    public void setEstado(String estado) {this.estado = estado;}

    @Override
    public String toString() {
        return "Endereço: " + logradouro + "\n" +
                "número: " + numero + "\n" +
                "CEP: " + cep + "\n" +
                "Bairro: " + bairro + "\n" +
                "Cidade: " + cidade + "\n" +
                "Estado: " + estado +
                "====================================";
    }
}
