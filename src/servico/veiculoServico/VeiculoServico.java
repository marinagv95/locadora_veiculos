package servico.veiculoServico;

import exception.veiculoException.PlacaDuplicadaException;
import exception.veiculoException.VeiculoNaoExistenteException;
import modelo.veiculo.Veiculo;

import java.util.List;
import java.util.Optional;

public interface VeiculoServico <T extends Veiculo> {
    public T cadastrarVeiculo(T veiculo) throws PlacaDuplicadaException;
    public T alterarVeiculo(T veiculo);
    public void removerVeiculo(String placao) throws VeiculoNaoExistenteException;

    public Optional<T> buscarVeiculoPorPlaca(String Placa);
    List<T> listarVeiculos();
    List<Veiculo> estaDisponivel();
}
