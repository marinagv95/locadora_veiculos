package exception.enderecoException;

public class CEPInvalidoException extends Exception{
    public CEPInvalidoException(){
        super("CEP inválido");
    }

    public CEPInvalidoException(String msg){
        super(msg);
    }
}
