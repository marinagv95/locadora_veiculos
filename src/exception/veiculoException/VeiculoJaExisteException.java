package exception.veiculoException;

public class VeiculoJaExisteException extends Exception{
    public VeiculoJaExisteException() {
        super("Veiculo já está cadastrado");
    }

    public VeiculoJaExisteException(String message) {
        super(message);
    }

}
