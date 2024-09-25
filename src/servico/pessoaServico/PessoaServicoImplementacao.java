package servico.pessoaServico;

import exception.pessoaException.PessoaNaoEncontradaException;
import modelo.pessoa.Pessoa;
import modelo.pessoa.PessoaFisica;
import modelo.pessoa.PessoaJuridica;
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
        if (pessoa instanceof PessoaFisica) {
            pessoaRepositorio.removerPessoa(((PessoaFisica) pessoa).getCpf());
        } else if (pessoa instanceof PessoaJuridica) {
            pessoaRepositorio.removerPessoa(((PessoaJuridica) pessoa).getCnpj());
        } else {
            throw new Exception("Tipo de pessoa desconhecido.");
        }
    }
    @Override
    public T buscarPorIdenficador(String identificador) throws Exception {
        return pessoaRepositorio.buscarPorIdentificador(identificador)
                .orElseThrow(() -> new PessoaNaoEncontradaException());
    }

    @Override
    public List<T> listarTodos() {
        return pessoaRepositorio.listarPessoas();
    }
}
