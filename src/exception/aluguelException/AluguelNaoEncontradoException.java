package exception.aluguelException;

public class AluguelNaoEncontradoException extends Exception {
    public AluguelNaoEncontradoException() {
        super("Aluguel não encontrado");
    }

    public AluguelNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}