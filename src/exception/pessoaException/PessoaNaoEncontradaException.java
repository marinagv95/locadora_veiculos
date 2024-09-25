package exception.pessoaException;

public class PessoaNaoEncontradaException extends Exception{
    public PessoaNaoEncontradaException(){
        super("Cliente não encontrado");
    }

    public PessoaNaoEncontradaException(String msg){
        super(msg);
    }
}
