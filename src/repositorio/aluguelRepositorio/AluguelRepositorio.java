package repositorio.aluguelRepositorio;

import modelo.aluguel.Aluguel;
import modelo.pessoa.Pessoa;
import modelo.veiculo.Veiculo;


import java.util.List;


public abstract class AluguelRepositorio<T extends Aluguel> {
    public abstract void adicionarAluguel(T aluguel);
    public abstract void removerAluguel(T aluguel);
    public abstract List<Aluguel> buscarAluguelPorVeiculo(Veiculo veiculo);
    public abstract List<Aluguel> buscarAluguelPorPessoa(String identificador);
    public abstract List<T> listarAlugueis();
    public abstract void alterarAluguel(T aluguel);
}
