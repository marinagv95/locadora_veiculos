package servico.aluguelServico;

import exception.aluguelException.AluguelNaoEncontradoException;
import exception.veiculoException.VeiculoNaoExistenteException;
import modelo.aluguel.Aluguel;
import modelo.veiculo.Veiculo;
import repositorio.aluguelRepositorio.AluguelRepositorio;
import servico.veiculoServico.VeiculoServico;

import java.util.Optional;

public class AluguelServicoImplementacao <T extends Aluguel> implements AluguelServico<T> {

    private AluguelRepositorio<T> aluguelRepositorio;
    private VeiculoServico<Veiculo> veiculoServico;

    public AluguelServicoImplementacao(AluguelRepositorio<T> aluguelRepositorio, VeiculoServico<Veiculo> veiculoServico) {
        this.aluguelRepositorio = aluguelRepositorio;
        this.veiculoServico = veiculoServico;
    }


    @Override
    public void salvar(T aluguel) {
        aluguelRepositorio.salvar(aluguel);
    }

    @Override
    public void excluir(T aluguel) {
        if (aluguelRepositorio.existeAluguel(aluguel.getVeiculo().getPlaca())) {
            aluguelRepositorio.cancelarAluguel(aluguel.getVeiculo().getPlaca());
        }
    }

    @Override
    public void editar(T aluguel) throws AluguelNaoEncontradoException {
        aluguelRepositorio.alterarAluguel(aluguel);
    }

    @Override
    public T buscarPorPlaca(String placa) throws AluguelNaoEncontradoException, VeiculoNaoExistenteException {
        Optional<Veiculo> veiculoOpt = veiculoServico.buscarVeiculoPorPlaca(placa);
        if (!veiculoOpt.isPresent()) {
            throw new VeiculoNaoExistenteException("Veículo com placa " + placa + " não encontrado.");
        }
        Veiculo veiculo = veiculoOpt.get();
        if (!veiculoServico.estaDisponivel(placa)) {
            throw new VeiculoNaoExistenteException("Veículo com placa " + placa + " não está disponível para aluguel.");
        }
        T aluguel = aluguelRepositorio.buscarAluguel(placa);
        if (aluguel == null) {
            throw new AluguelNaoEncontradoException("Aluguel não encontrado para a placa " + placa);
        }
        return aluguel;
    }

    @Override
    public T buscarPorPessoa(String nomePessoa) {
        return null;
    }
}