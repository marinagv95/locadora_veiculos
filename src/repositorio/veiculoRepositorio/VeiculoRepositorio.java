package repositorio.veiculoRepositorio;

import exception.veiculoException.PlacaDuplicadaException;
import exception.veiculoException.VeiculoNaoExistenteException;
import modelo.veiculo.Veiculo;

import java.util.List;
import java.util.Optional;

public abstract class VeiculoRepositorio<T extends Veiculo> {
    public abstract T salvar(T veiculo) throws PlacaDuplicadaException;
    public abstract List<T> listarVeiculos();
    public abstract T alterarVeiculo(T veiculo);
    public abstract Optional<T> buscarPorPlaca(String placa);
    public abstract T removerVeiculo(String placa) throws VeiculoNaoExistenteException;
    public abstract boolean estaDisponivel(String placa);
}
