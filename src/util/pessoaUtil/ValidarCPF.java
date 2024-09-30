package util.pessoaUtil;

public class ValidarCPF {
    public static boolean validarCPF(String cpf) {
        cpf = cpf.replaceAll("[^\\d]", "");

        return cpf.length() == 11;
    }
}
