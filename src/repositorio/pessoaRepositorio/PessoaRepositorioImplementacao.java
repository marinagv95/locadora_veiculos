package repositorio.pessoaRepositorio;

import exception.pessoaException.CNPJInvalidoException;
import exception.pessoaException.CPFInvalidoException;
import exception.pessoaException.PessoaDuplicadaException;
import exception.pessoaException.PessoaNaoEncontradaException;
import modelo.pessoa.Pessoa;
import modelo.pessoa.PessoaFisica;
import modelo.pessoa.PessoaJuridica;
import util.pessoaUtil.ValidarCNPJ;
import util.pessoaUtil.ValidarCPF;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PessoaRepositorioImplementacao extends PessoaRepositorio<Pessoa> {
    private List<Pessoa> bancoDados;

    public PessoaRepositorioImplementacao() {
        this.bancoDados = new ArrayList<>();
    }

    @Override
    public Pessoa salvar(Pessoa pessoa) throws PessoaDuplicadaException, PessoaNaoEncontradaException, CPFInvalidoException, CNPJInvalidoException {
        if (pessoa instanceof PessoaFisica) {
            String cpf = ((PessoaFisica) pessoa).getCpf();

            if (!ValidarCPF.validarCPF(cpf)) {
                throw new CPFInvalidoException("CPF inválido: " + cpf);
            }
            if (buscarPorCpf(cpf).isPresent()) {
                throw new PessoaDuplicadaException("Pessoa física com CPF " + cpf + " já cadastrada.");
            }
        } else if (pessoa instanceof PessoaJuridica) {
            String cnpj = ((PessoaJuridica) pessoa).getCnpj();

            if (!ValidarCNPJ.validarCNPJ(cnpj)) {
                throw new CNPJInvalidoException("CNPJ inválido: " + cnpj);
            }

            if (buscarPorCnpj(cnpj).isPresent()) {
                throw new PessoaDuplicadaException("Pessoa jurídica com CNPJ " + cnpj + " já cadastrada.");
            }
        }
        bancoDados.add(pessoa);
        return pessoa;
    }


    @Override
    public List<Pessoa> listarPessoas() {
        return new ArrayList<>(bancoDados);
    }

    @Override
    public Pessoa alterarPessoa(Pessoa pessoa) throws PessoaNaoEncontradaException {
        Optional<Pessoa> optionalPessoa = buscarPorIdentificador(
                pessoa instanceof PessoaFisica ? ((PessoaFisica) pessoa).getCpf() : ((PessoaJuridica) pessoa).getCnpj()
        );

        if (optionalPessoa.isPresent()) {
            int index = bancoDados.indexOf(optionalPessoa.get());
            bancoDados.set(index, pessoa);
            return pessoa;
        } else {
            throw new PessoaNaoEncontradaException("Pessoa não encontrada.");
        }
    }

    @Override
    public Optional<Pessoa> buscarPorIdentificador(String identificador) throws PessoaNaoEncontradaException {
        return bancoDados.stream()
                .filter(p -> (p instanceof PessoaFisica && ((PessoaFisica) p).getCpf().equals(identificador)) ||
                        (p instanceof PessoaJuridica && ((PessoaJuridica) p).getCnpj().equals(identificador)))
                .findFirst();
    }


    @Override
    public Pessoa removerPessoa(String identificador) throws PessoaNaoEncontradaException {
        Optional<Pessoa> optionalPessoa = buscarPorIdentificador(identificador);
        if (optionalPessoa.isPresent()) {
            bancoDados.remove(optionalPessoa.get());
            System.out.println("Pessoa removida: " + optionalPessoa.get()); // Log da pessoa removida
            return optionalPessoa.get();
        } else {
            throw new PessoaNaoEncontradaException("Pessoa não encontrada.");
        }
    }

    @Override
    public Optional<PessoaFisica> buscarPorCpf(String cpf) throws PessoaNaoEncontradaException {
        return bancoDados.stream()
                .filter(p -> p instanceof PessoaFisica && ((PessoaFisica) p).getCpf().equals(cpf))
                .map(p -> (PessoaFisica) p)
                .findFirst();
    }

    @Override
    public Optional<PessoaJuridica> buscarPorCnpj(String cnpj) throws PessoaNaoEncontradaException {
        return bancoDados.stream()
                .filter(p -> p instanceof PessoaJuridica && ((PessoaJuridica) p).getCnpj().equals(cnpj))
                .map(p -> (PessoaJuridica) p)
                .findFirst();
    }
}
