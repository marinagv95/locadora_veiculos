package repositorio.aluguelRepositorio;

import modelo.agencia.Agencia;
import modelo.aluguel.Aluguel;
import modelo.pessoa.Pessoa;
import modelo.pessoa.PessoaFisica;
import modelo.pessoa.PessoaJuridica;
import modelo.veiculo.Veiculo;

import java.util.ArrayList;
import java.util.List;

public class AluguelRepositorioImplementacao <T extends Aluguel> extends AluguelRepositorio<T> {

    public List<T> bancoDados;
    private List<Veiculo> veiculos;
    private List<Pessoa> pessoas;
    private List<Agencia> agencias;

    public AluguelRepositorioImplementacao() {
        this.bancoDados = new ArrayList<>();
        this.bancoDados = new ArrayList<>();
        this.veiculos = new ArrayList<>();
        this.pessoas = new ArrayList<>();
        this.agencias = new ArrayList<>();
    }


    @Override
    public Pessoa buscarPessoa(String identificador) {
        for (Pessoa pessoa : pessoas) {
            if (pessoa instanceof PessoaFisica && ((PessoaFisica) pessoa).getCpf().equals(identificador)) {
                return pessoa;
            } else if (pessoa instanceof PessoaJuridica && ((PessoaJuridica) pessoa).getCnpj().equals(identificador)) {
                return pessoa;
            }
        }
        return null;
    }

    @Override
    public void adicionarVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
    }

    @Override
    public List<Veiculo> listarVeiculosDisponiveis() {
        List<Veiculo> veiculosDisponiveis = new ArrayList<>();
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getDisponivel()) {
                veiculosDisponiveis.add(veiculo);
            }
        }
        return veiculosDisponiveis;
    }

    @Override
    public void buscarAgencia(Agencia agencia) {
        if (!agencias.contains(agencia)) {
            agencias.add(agencia);
        }
    }

    @Override
    public T adicionarAluguel(T aluguel) {
        bancoDados.add(aluguel);
        return aluguel;
    }

    @Override
    public List<T> listarAlugueis() {
        return bancoDados;
    }

    @Override
    public void adicionarPessoa(Pessoa pessoa) {
        pessoas.add(pessoa);
    }

    @Override
    public void alterarAluguel(T aluguel) {
        Pessoa pessoa = buscarPessoa(aluguel.getPessoa() instanceof PessoaFisica ?
                ((PessoaFisica) aluguel.getPessoa()).getCpf() :
                ((PessoaJuridica) aluguel.getPessoa()).getCnpj());

        if (pessoa != null) {
            for (int i = 0; i < bancoDados.size(); i++) {
                T alug = bancoDados.get(i);
                if (alug.getPessoa() instanceof PessoaFisica &&
                        pessoa instanceof PessoaFisica &&
                        ((PessoaFisica) alug.getPessoa()).getCpf().equals(((PessoaFisica) pessoa).getCpf())) {
                    bancoDados.set(i, aluguel);
                    break;
                } else if (alug.getPessoa() instanceof PessoaJuridica &&
                        pessoa instanceof PessoaJuridica &&
                        ((PessoaJuridica) alug.getPessoa()).getCnpj().equals(((PessoaJuridica) pessoa).getCnpj())) {
                    bancoDados.set(i, aluguel);
                    break;
                }
            }
        }
    }

    @Override
    public void removerAluguel(T aluguel) {
        Pessoa pessoa = buscarPessoa(aluguel.getPessoa() instanceof PessoaFisica ?
                ((PessoaFisica) aluguel.getPessoa()).getCpf() :
                ((PessoaJuridica) aluguel.getPessoa()).getCnpj());

        if (pessoa != null) {
            for (int i = 0; i < bancoDados.size(); i++) {
                T alug = bancoDados.get(i);
                if (alug.getPessoa() instanceof PessoaFisica &&
                        pessoa instanceof PessoaFisica &&
                        ((PessoaFisica) alug.getPessoa()).getCpf().equals(((PessoaFisica) pessoa).getCpf())) {
                    bancoDados.remove(i);
                    break;
                } else if (alug.getPessoa() instanceof PessoaJuridica &&
                        pessoa instanceof PessoaJuridica &&
                        ((PessoaJuridica) alug.getPessoa()).getCnpj().equals(((PessoaJuridica) pessoa).getCnpj())) {
                    bancoDados.remove(i);
                    break;
                }
            }
        }
    }
}
