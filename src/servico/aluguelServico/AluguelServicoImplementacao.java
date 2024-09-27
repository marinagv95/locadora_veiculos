package servico.aluguelServico;

import exception.aluguelException.AluguelNaoEncontradoException;
import exception.veiculoException.VeiculoNaoExistenteException;
import modelo.aluguel.Aluguel;
import modelo.aluguel.DevolucaoAluguel;
import modelo.veiculo.Veiculo;
import repositorio.aluguelRepositorio.AluguelRepositorio;
import servico.veiculoServico.VeiculoServico;

import java.util.List;
import java.util.Optional;

public class AluguelServicoImplementacao <T extends Aluguel> implements AluguelServico<Aluguel> {

    private AluguelRepositorio<Aluguel> aluguelRepositorio;
    private VeiculoServico<Veiculo> veiculoServico;

    public AluguelServicoImplementacao(AluguelRepositorio<Aluguel> aluguelRepositorio) {
        this.aluguelRepositorio = aluguelRepositorio;
        this.veiculoServico = veiculoServico;
    }


    @Override
    public void alugarVeiculo(Aluguel aluguel) throws VeiculoNaoExistenteException {
        Veiculo veiculo = aluguel.getVeiculo();
        if (!veiculoServico.estaDisponivel(String.valueOf(veiculo))) {
            throw new VeiculoNaoExistenteException("Veículo não disponível para aluguel.");
        }
        aluguelRepositorio.adicionarAluguel(aluguel);
    }

    @Override
    public void retiradaVeiculo(Aluguel aluguel) throws AluguelNaoEncontradoException {
        Optional<Aluguel> aluguelExistente = aluguelRepositorio.buscarAluguelPorVeiculo(aluguel.getVeiculo()).stream().findFirst();
        if (!aluguelExistente.isPresent()) {
            throw new AluguelNaoEncontradoException("Aluguel não encontrado para retirada.");
        }
    }

    @Override
    public void devolverVeiculo(DevolucaoAluguel devolucaoAluguel) throws AluguelNaoEncontradoException {
        Optional<Aluguel> aluguelOptional = aluguelRepositorio.buscarAluguelPorVeiculo(devolucaoAluguel.getVeiculo()).stream().findFirst();
        if (!aluguelOptional.isPresent()) {
            throw new AluguelNaoEncontradoException("Aluguel não encontrado para devolução.");
        }
        aluguelRepositorio.removerAluguel(aluguelOptional.get());
    }

    @Override
    public Aluguel buscarAluguelPorVeiculo(Veiculo veiculo) throws AluguelNaoEncontradoException {
        List<Aluguel> alugueis = aluguelRepositorio.buscarAluguelPorVeiculo(veiculo);
        if (alugueis.isEmpty()) {
            throw new AluguelNaoEncontradoException("Nenhum aluguel encontrado para o veículo informado.");
        }
        return alugueis.get(0);
    }

    @Override
    public List<Aluguel> buscarAluguelPorPessoa(String identificador) {
        // Implementação para buscar no repositório
        return aluguelRepositorio.buscarAluguelPorPessoa(identificador);
    }

    @Override
    public void alterarAluguel(Aluguel aluguel) throws AluguelNaoEncontradoException {
        Optional<Aluguel> aluguelExistente = aluguelRepositorio.buscarAluguelPorVeiculo(aluguel.getVeiculo()).stream().findFirst();
        if (!aluguelExistente.isPresent()) {
            throw new AluguelNaoEncontradoException("Aluguel não encontrado para alteração.");
        }
        aluguelRepositorio.alterarAluguel(aluguel);
    }

    @Override
    public void removerAluguel(Aluguel aluguel) throws AluguelNaoEncontradoException {
        Optional<Aluguel> aluguelExistente = aluguelRepositorio.buscarAluguelPorVeiculo(aluguel.getVeiculo()).stream().findFirst();
        if (!aluguelExistente.isPresent()) {
            throw new AluguelNaoEncontradoException("Aluguel não encontrado para remoção.");
        }
        aluguelRepositorio.removerAluguel(aluguel);
    }
}
