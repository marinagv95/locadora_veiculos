package exception.pessoaException;

public class EmailInvalidoException extends Exception{
    public EmailInvalidoException(){
        super("Email inválido");
    }

    public EmailInvalidoException(String msg){
        super(msg);
    }
}
