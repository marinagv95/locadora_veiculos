package repositorio.aluguelRepositorio;

import exception.pessoaException.PessoaNaoEncontradaException;
import modelo.agencia.Agencia;
import modelo.aluguel.Aluguel;
import modelo.pessoa.Pessoa;
import modelo.veiculo.Veiculo;

import java.util.List;
import java.util.Optional;


public abstract class AluguelRepositorio<T extends Aluguel> {
    public abstract List<T> alugueis();
    public abstract T salvarAluguel(Aluguel aluguel) throws Exception;
    public abstract void removerAluguel(T aluguel) throws Exception;
    public abstract Optional<T> buscarPorIdentificador(String identificador) throws PessoaNaoEncontradaException;
    public abstract Optional<Veiculo> buscarVeiculoDisponivel(String placa);
    public abstract Optional<Pessoa> buscarCliente(String identificador) throws PessoaNaoEncontradaException;
    public abstract List<Agencia> buscarAgenciasDisponiveis();
}
