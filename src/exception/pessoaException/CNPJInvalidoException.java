package exception.pessoaException;

public class CNPJInvalidoException extends Exception{
    public CNPJInvalidoException(){
        super("CNPJ inválido");
    }

    public CNPJInvalidoException(String msg){
        super(msg);
    }
}
