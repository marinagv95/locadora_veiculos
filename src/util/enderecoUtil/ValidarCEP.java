package util.enderecoUtil;

public class ValidarCEP {
    public static boolean validarCEP(String cep) {
        String cepSemEspacos = cep.replaceAll("\\s+", "");
        return cepSemEspacos.matches("\\d{5}-?\\d{3}") || cepSemEspacos.matches("\\d{8}");
    }
}
