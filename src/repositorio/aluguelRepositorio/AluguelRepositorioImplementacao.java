package repositorio.aluguelRepositorio;

import modelo.aluguel.Aluguel;
import modelo.pessoa.Pessoa;
import modelo.pessoa.PessoaFisica;
import modelo.pessoa.PessoaJuridica;
import modelo.veiculo.Veiculo;

import java.util.ArrayList;
import java.util.List;

public class AluguelRepositorioImplementacao <T extends Aluguel> extends AluguelRepositorio<T> {

    public List<T> bancoDados;

    public AluguelRepositorioImplementacao() {
        this.bancoDados = new ArrayList<>();
    }


    @Override
    public void adicionarAluguel(T aluguel) {
        bancoDados.add(aluguel);
    }

    @Override
    public void removerAluguel(T aluguel) {
        bancoDados.remove(aluguel);
    }

    @Override
    public List<Aluguel> buscarAluguelPorVeiculo(Veiculo veiculo) {
        List<Aluguel> result = new ArrayList<>();
        for (Aluguel aluguel : bancoDados) {
            if (aluguel.getVeiculo().equals(veiculo)) {
                result.add(aluguel);
            }
        }
        return result;
    }

    @Override
    public List<Aluguel> buscarAluguelPorPessoa(String identificador) {
        List<Aluguel> result = new ArrayList<>();
        for (Aluguel aluguel : bancoDados) {
            Pessoa pessoaAluguel = aluguel.getPessoa();

            if (pessoaAluguel instanceof PessoaFisica) {
                String cpf = ((PessoaFisica) pessoaAluguel).getCpf();
                if (cpf.equals(identificador)) {
                    result.add(aluguel);
                }
            }

            else if (pessoaAluguel instanceof PessoaJuridica) {
                String cnpj = ((PessoaJuridica) pessoaAluguel).getCnpj();
                if (cnpj.equals(identificador)) {
                    result.add(aluguel);
                }
            }
        }
        return result;
    }


    @Override
    public List<T> listarAlugueis() {
        return new ArrayList<>(bancoDados);
    }

    @Override
    public void alterarAluguel(T aluguel) {

    }
}
