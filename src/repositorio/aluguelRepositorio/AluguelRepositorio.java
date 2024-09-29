package repositorio.aluguelRepositorio;

import modelo.agencia.Agencia;
import modelo.aluguel.Aluguel;
import modelo.pessoa.Pessoa;
import modelo.veiculo.Veiculo;


import java.util.List;


public abstract class AluguelRepositorio<T extends Aluguel> {
    public abstract Pessoa buscarPessoa(String identificador);
    public abstract void adicionarVeiculo(Veiculo veiculo);
    public abstract List<Veiculo> listarVeiculosDisponiveis();
    public abstract void buscarAgencia(Agencia agencia);

    public abstract T adicionarAluguel(T aluguel);
    public abstract List<T> listarAlugueis();
    public abstract void adicionarPessoa(Pessoa pessoa);

    public abstract void alterarAluguel(T aluguel);
    public abstract void removerAluguel(T aluguel);

}
