package repositorio.pessoaRepositorio;

import exception.pessoaException.PessoaDuplicadaException;
import exception.pessoaException.PessoaNaoEncontradaException;
import modelo.pessoa.Pessoa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PessoaRepositorioImplementacao <T extends Pessoa> extends PessoaRepositorio<T> {
    public List<T> bancoDados;

    public PessoaRepositorioImplementacao() {
        this.bancoDados = new ArrayList<T>();
    }

    @Override
    public T salvar(T pessoa) throws PessoaDuplicadaException {
        if (bancoDados.stream().anyMatch(p -> p.getNomePessoa().equalsIgnoreCase(pessoa.getNomePessoa()))) {
            throw new PessoaDuplicadaException("Pessoa com nome " + pessoa.getNomePessoa() + " já cadastrada.");
        }
        bancoDados.add(pessoa);
        return pessoa;
    }

    @Override
    public List<T> listarPessoas() {
        return new ArrayList<>(bancoDados);
    }

    @Override
    public T alterarPessoa(T pessoa) throws PessoaNaoEncontradaException {
        Optional<T> optionalPessoa = buscarPorNome(pessoa.getNomePessoa());
        if (optionalPessoa.isPresent()) {
            int index = bancoDados.indexOf(optionalPessoa.get());
            bancoDados.set(index, pessoa);
            return pessoa;
        } else {
            throw new PessoaNaoEncontradaException("Pessoa não encontrada.");
        }
    }

    @Override
    public Optional<T> buscarPorNome(String nome) {
        return bancoDados.stream()
                .filter(p -> p.getNomePessoa().equalsIgnoreCase(nome))
                .findFirst();
    }

    @Override
    public T removerPessoa(String nome) throws PessoaNaoEncontradaException {
        Optional<T> optionalPessoa = buscarPorNome(nome);
        if (optionalPessoa.isPresent()) {
            bancoDados.remove(optionalPessoa.get());
            return optionalPessoa.get();
        } else {
            throw new PessoaNaoEncontradaException("Pessoa não encontrada.");
        }
    }
}
