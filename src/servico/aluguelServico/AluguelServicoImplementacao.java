package servico.aluguelServico;

import exception.aluguelException.AluguelNaoEncontradoException;
import exception.veiculoException.VeiculoNaoExistenteException;
import modelo.aluguel.Aluguel;
import modelo.aluguel.DevolucaoAluguel;
import modelo.pessoa.Pessoa;
import modelo.pessoa.PessoaFisica;
import modelo.pessoa.PessoaJuridica;
import modelo.veiculo.Veiculo;
import repositorio.aluguelRepositorio.AluguelRepositorio;
import servico.veiculoServico.VeiculoServico;

import java.util.List;
import java.util.Optional;

public class AluguelServicoImplementacao <T extends Aluguel> implements AluguelServico<Aluguel> {

    private AluguelRepositorio<Aluguel> aluguelRepositorio;

    public AluguelServicoImplementacao(AluguelRepositorio<Aluguel> aluguelRepositorio) {
        this.aluguelRepositorio = aluguelRepositorio;
    }


    @Override
    public void alugarVeiculo(Aluguel aluguel) throws VeiculoNaoExistenteException {
        Veiculo veiculo = aluguel.getVeiculo();
        if (!veiculo.getDisponivel()) {
            throw new VeiculoNaoExistenteException("Veículo não disponível para aluguel.");
        }
        aluguelRepositorio.adicionarAluguel(aluguel);
        veiculo.setDisponivel(false);
        aluguelRepositorio.adicionarVeiculo(veiculo);
    }

    @Override
    public void retiradaVeiculo(Aluguel aluguel) throws AluguelNaoEncontradoException {
        Optional<Aluguel> aluguelOptional = aluguelRepositorio.listarAlugueis().stream()
                .filter(a -> a.getProtocolo().equals(aluguel.getProtocolo())).findFirst();

        if (!aluguelOptional.isPresent()) {
            throw new AluguelNaoEncontradoException("Aluguel não encontrado.");
        }
    }

    @Override
    public void devolverVeiculo(DevolucaoAluguel devolucaoAluguel) {

    }

    @Override
    public Aluguel buscarAluguelPorVeiculo(Veiculo veiculo) throws AluguelNaoEncontradoException {
        return aluguelRepositorio.listarAlugueis().stream()
                .filter(a -> a.getVeiculo().equals(veiculo))
                .findFirst()
                .orElseThrow(() -> new AluguelNaoEncontradoException("Aluguel não encontrado para o veículo."));
    }

    @Override
    public List<Aluguel> buscarAluguelPorPessoa(String identificador) {
        return aluguelRepositorio.listarAlugueis().stream()
                .filter(a -> {
                    Pessoa pessoa = a.getPessoa();
                    if (pessoa instanceof PessoaFisica) {
                        return ((PessoaFisica) pessoa).getCpf().equals(identificador);
                    }
                    else if (pessoa instanceof PessoaJuridica) {
                        return ((PessoaJuridica) pessoa).getCnpj().equals(identificador);
                    }
                    return false;
                })
                .toList();
    }

    @Override
    public void alterarAluguel(Aluguel aluguel) throws AluguelNaoEncontradoException {
        Optional<Aluguel> aluguelOptional = aluguelRepositorio.listarAlugueis().stream()
                .filter(a -> a.getProtocolo().equals(aluguel.getProtocolo())).findFirst();

        if (!aluguelOptional.isPresent()) {
            throw new AluguelNaoEncontradoException("Aluguel não encontrado.");
        }
        aluguelRepositorio.alterarAluguel(aluguel);
    }

    @Override
    public void removerAluguel(Aluguel aluguel) throws AluguelNaoEncontradoException {
        Optional<Aluguel> aluguelOptional = aluguelRepositorio.listarAlugueis().stream()
                .filter(a -> a.getProtocolo().equals(aluguel.getProtocolo())).findFirst();

        if (!aluguelOptional.isPresent()) {
            throw new AluguelNaoEncontradoException("Aluguel não encontrado.");
        }

        aluguelRepositorio.removerAluguel(aluguel);
    }
}
