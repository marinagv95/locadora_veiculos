package util.pessoaUtil;

public class ValidarCNPJ {

    public static boolean validarCNPJ(String cnpj) {
        cnpj = cnpj.replaceAll("[^\\d]", "");
        return cnpj.length() == 14;
    }
}
