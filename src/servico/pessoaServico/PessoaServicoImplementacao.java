package servico.pessoaServico;

import exception.pessoaException.PessoaNaoEncontradaException;
import modelo.pessoa.Pessoa;
import repositorio.pessoaRepositorio.PessoaRepositorio;

import java.util.List;

public class PessoaServicoImplementacao<T extends Pessoa> implements PessoaServico<T> {

    private PessoaRepositorio<T> pessoaRepositorio;

    public PessoaServicoImplementacao(PessoaRepositorio<T> pessoaRepositorio) {
        this.pessoaRepositorio = pessoaRepositorio;
    }

    @Override
    public T adicionar(T pessoa) throws Exception {
        return pessoaRepositorio.salvar(pessoa);
    }

    @Override
    public void remover(T pessoa) throws Exception {
        pessoaRepositorio.removerPessoa(pessoa.getNomePessoa());
    }

    @Override
    public T buscarPorNome(String nome) throws Exception {
        return pessoaRepositorio.buscarPorNome(nome)
                .orElseThrow(() -> new PessoaNaoEncontradaException("Pessoa com nome " + nome + " não encontrada."));
    }

    @Override
    public List<T> listarTodos() {
        return pessoaRepositorio.listarPessoas();
    }
}
