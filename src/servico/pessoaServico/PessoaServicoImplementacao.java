package servico.pessoaServico;

import exception.pessoaException.PessoaNaoEncontradaException;
import modelo.pessoa.Pessoa;
import modelo.pessoa.PessoaFisica;
import modelo.pessoa.PessoaJuridica;
import repositorio.pessoaRepositorio.PessoaRepositorio;

import java.util.List;
import java.util.Optional;

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
            String cpf = ((PessoaFisica) pessoa).getCpf();
            pessoaRepositorio.removerPessoa(cpf);
        } else if (pessoa instanceof PessoaJuridica) {
            String cnpj = ((PessoaJuridica) pessoa).getCnpj();
            pessoaRepositorio.removerPessoa(cnpj);
        } else {
            throw new Exception("Pessoa não identificada.");
        }
    }

    @Override
    public T buscarPorIdenficador(String identificador) throws Exception {
        return pessoaRepositorio.buscarPorIdentificador(identificador)
                .orElseThrow(() -> new PessoaNaoEncontradaException());
    }

    @Override
    public Optional<PessoaFisica> buscarPorCpf(String cpf) throws PessoaNaoEncontradaException {
        return pessoaRepositorio.buscarPorCpf(cpf);
    }

    @Override
    public Optional<PessoaJuridica> buscarPorCnpj(String cnpj) throws PessoaNaoEncontradaException {
        return pessoaRepositorio.buscarPorCnpj(cnpj);
    }

    @Override
    public List<T> listarTodos() {
        return pessoaRepositorio.listarPessoas();
    }

    @Override
    public T alterarPessoa(T pessoa) throws PessoaNaoEncontradaException {
        return pessoaRepositorio.alterarPessoa(pessoa);
    }
}
