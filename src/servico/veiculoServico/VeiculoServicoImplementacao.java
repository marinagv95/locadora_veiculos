package servico.veiculoServico;

import exception.veiculoException.PlacaDuplicadaException;
import exception.veiculoException.VeiculoNaoExistenteException;
import modelo.veiculo.Veiculo;
import repositorio.veiculoRepositorio.VeiculoRepositorio;
import repositorio.veiculoRepositorio.VeiculoRepositorioImplementacao;

import java.util.List;
import java.util.Optional;

public class VeiculoServicoImplementacao<T extends Veiculo> implements VeiculoServico<T> {

    private VeiculoRepositorio<T> veiculoRepositorio;

    public VeiculoServicoImplementacao() {
        this.veiculoRepositorio = new VeiculoRepositorioImplementacao<>();
    }

    @Override
    public T cadastrarVeiculo(T veiculo) throws PlacaDuplicadaException {
        return this.veiculoRepositorio.salvar(veiculo);
    }

    @Override
    public T alterarVeiculo(T veiculo) {
        return this.veiculoRepositorio.alterarVeiculo(veiculo);
    }


    @Override
    public void removerVeiculo(String placa) throws VeiculoNaoExistenteException {
        Optional<T> veiculo = veiculoRepositorio.buscarPorPlaca(placa);
        if (!veiculo.isPresent()) {
            throw new VeiculoNaoExistenteException("Veículo com a placa " + placa + " não encontrado.");
        }
        veiculoRepositorio.removerVeiculo(placa);
    }

    @Override
    public Optional<T> buscarVeiculoPorPlaca(String placa) {
        return this.veiculoRepositorio.listarVeiculos().stream()
                .filter(veiculo -> veiculo.getPlaca().equalsIgnoreCase(placa))
                .findFirst();
    }


    @Override
    public List<T> listarVeiculos() {
        return this.veiculoRepositorio.listarVeiculos();
    }
}
