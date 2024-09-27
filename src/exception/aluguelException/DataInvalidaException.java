package exception.aluguelException;

public class DataInvalidaException extends Exception{
    public DataInvalidaException(){
        super("Data informada inválida");
    }

    public DataInvalidaException(String message){
        super(message);
    }
}
