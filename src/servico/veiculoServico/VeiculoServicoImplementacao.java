package servico.veiculoServico;

import modelo.veiculo.Veiculo;
import repositorio.veiculoRepositorio.VeiculoRepositorio;
import repositorio.veiculoRepositorio.VeiculoRepositorioImplementacao;

import java.util.List;

public class VeiculoServicoImplementacao<T extends Veiculo> implements VeiculoServico<T> {

    private VeiculoRepositorio<T> veiculoRepository;

    public VeiculoServicoImplementacao() {
        this.veiculoRepository = new VeiculoRepositorioImplementacao<>();
    }

    @Override
    public T cadastrarVeiculo(T veiculo) {
        return this.veiculoRepository.salvar(veiculo);
    }

    @Override
    public T alterarVeiculo(T veiculo) {
        return this.veiculoRepository.alterarVeiculo(veiculo);
    }

    @Override
    public void removerVeiculo(T veiculo) {

    }

    @Override
    public T buscarVeiculoPorNome(String nome) {
        return this.veiculoRepository.listarVeiculos().stream()
                .filter(veiculo -> veiculo.getModelo().toLowerCase()
                        .contains(nome.toLowerCase())).findFirst()
                .orElse(null);
    }

    @Override
    public List<T> listarVeiculos() {
        return this.veiculoRepository.listarVeiculos();
    }
}
