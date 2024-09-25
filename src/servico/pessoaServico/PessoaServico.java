package servico.pessoaServico;

import modelo.pessoa.Pessoa;

import java.util.List;

public interface PessoaServico <T extends Pessoa> {
    public T adicionar(T pessoa) throws Exception;
    public void remover(T pessoa) throws Exception;
    public T buscarPorIdenficador(String identificador) throws Exception;
    public List<T> listarTodos();

}