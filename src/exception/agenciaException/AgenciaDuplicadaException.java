package exception.agenciaException;

public class AgenciaDuplicadaException extends Exception{
    public AgenciaDuplicadaException(){
        super("Agencia já existe, tente novamente");
    }

    public AgenciaDuplicadaException(String message){
        super(message);
    }
}
