package exception.pessoaException;

public class PessoaDuplicadaException extends Exception{
    public PessoaDuplicadaException(){
        super("Cliente já possui cadastro");
    }

    public PessoaDuplicadaException(String msg){
        super(msg);
    }
}
