package repositorio.pessoaRepositorio;

import exception.pessoaException.PessoaDuplicadaException;
import exception.pessoaException.PessoaNaoEncontradaException;
import modelo.pessoa.Pessoa;

import java.util.List;
import java.util.Optional;

public abstract class PessoaRepositorio<T extends Pessoa> {
    public abstract T salvar(T pessoa) throws PessoaDuplicadaException;
    public abstract List<T> listarPessoas();
    public abstract T alterarPessoa(T pessoa) throws PessoaNaoEncontradaException;
    public abstract Optional<T> buscarPorNome(String nome) throws PessoaNaoEncontradaException;
    public abstract T removerPessoa(String nome) throws PessoaNaoEncontradaException;
}
