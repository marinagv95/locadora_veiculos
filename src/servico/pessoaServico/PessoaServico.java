package servico.pessoaServico;

import exception.pessoaException.PessoaNaoEncontradaException;
import modelo.pessoa.Pessoa;
import modelo.pessoa.PessoaFisica;
import modelo.pessoa.PessoaJuridica;

import java.util.List;
import java.util.Optional;

public interface PessoaServico <T extends Pessoa> {
    public T adicionar(T pessoa) throws Exception;
    public void remover(T pessoa) throws Exception;
    public T buscarPorIdenficador(String identificador) throws Exception;
    public Optional<PessoaFisica> buscarPorCpf(String cpf) throws PessoaNaoEncontradaException;
    public Optional<PessoaJuridica> buscarPorCnpj(String cnpj) throws PessoaNaoEncontradaException;
    public List<T> listarTodos();
    public T alterarPessoa(T pessoa) throws PessoaNaoEncontradaException;
    public boolean possuiAluguelAtivo(Pessoa pessoa);
}

