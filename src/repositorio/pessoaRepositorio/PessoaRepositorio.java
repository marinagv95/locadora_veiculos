package repositorio.pessoaRepositorio;

import exception.pessoaException.CNPJInvalidoException;
import exception.pessoaException.CPFInvalidoException;
import exception.pessoaException.PessoaDuplicadaException;
import exception.pessoaException.PessoaNaoEncontradaException;
import modelo.pessoa.Pessoa;
import modelo.pessoa.PessoaFisica;
import modelo.pessoa.PessoaJuridica;

import java.util.List;
import java.util.Optional;

public abstract class PessoaRepositorio<T extends Pessoa> {
    public abstract T salvar(T pessoa) throws PessoaDuplicadaException, PessoaNaoEncontradaException, CPFInvalidoException, CNPJInvalidoException;
    public abstract List<T> listarPessoas();
    public abstract T alterarPessoa(T pessoa) throws PessoaNaoEncontradaException;
    public abstract Optional<T> buscarPorIdentificador(String idenficador) throws PessoaNaoEncontradaException;
    public abstract T removerPessoa(String identificador) throws PessoaNaoEncontradaException;
    public abstract Optional<PessoaFisica> buscarPorCpf(String cpf) throws PessoaNaoEncontradaException;
    public abstract Optional<PessoaJuridica> buscarPorCnpj(String cnpj) throws PessoaNaoEncontradaException;
}
