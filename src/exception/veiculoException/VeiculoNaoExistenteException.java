package exception.veiculoException;

public class VeiculoNaoExistenteException extends Exception{
    public VeiculoNaoExistenteException(){
        super("Veiculo não existe");
    }

    public VeiculoNaoExistenteException(String msg){
        super(msg);
    }
}