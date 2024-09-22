package repositorio.veiculoRepositorio;

import modelo.veiculo.Veiculo;

import java.util.List;
import java.util.Optional;

public abstract class VeiculoRepositorio<T extends Veiculo> {
    public abstract T salvar(T veiculo);
    public abstract List<T> listarVeiculos();
    public abstract T alterarVeiculo(T veiculo);
    public abstract Optional<T> buscarPorPlaca(String placa);

}
