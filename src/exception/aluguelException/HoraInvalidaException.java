package exception.aluguelException;

public class HoraInvalidaException extends Exception{
    public HoraInvalidaException(){
        super("Hora informada inválida");
    }

    public HoraInvalidaException(String msg){
        super(msg);
    }
}
