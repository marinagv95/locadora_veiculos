package servico.aluguelServico;

import exception.aluguelException.AluguelNaoEncontradoException;
import exception.veiculoException.VeiculoNaoExistenteException;
import modelo.aluguel.Aluguel;
import modelo.pessoa.Pessoa;
import modelo.pessoa.PessoaFisica;
import modelo.pessoa.PessoaJuridica;
import modelo.veiculo.Veiculo;
import repositorio.aluguelRepositorio.AluguelRepositorio;
import servico.veiculoServico.VeiculoServico;

import java.math.BigDecimal;
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
        BigDecimal total = calcularTotalAluguel(aluguel);
        System.out.println("Total do aluguel após edição: " + total);
    }

    private BigDecimal calcularTotalAluguel(T aluguel) {
        Veiculo veiculo = aluguel.getVeiculo();
        if (veiculo == null || veiculo.getValorDiaria() == null || aluguel.getDiasAlugados() < 0) {
            throw new IllegalArgumentException("Veículo ou valor da diária não pode ser nulo, e dias alugados não podem ser negativos.");
        }
        return veiculo.getValorDiaria().multiply(BigDecimal.valueOf(aluguel.getDiasAlugados()));
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
    public T buscarPorPessoa(T identificador) throws AluguelNaoEncontradoException {
        for (T aluguel : aluguelRepositorio.listarAlugueis()) {
            Pessoa pessoa = aluguel.getPessoa();

            if (pessoa instanceof PessoaFisica) {
                if (((PessoaFisica) pessoa).getCpf().equals(identificador)) {
                    return aluguel;
                }
            }
            else if (pessoa instanceof PessoaJuridica) {
                if (((PessoaJuridica) pessoa).getCnpj().equals(identificador)) {
                    return aluguel;
                }
            }
        }
        throw new AluguelNaoEncontradoException("Aluguel não encontrado para a pessoa com identificador: " + identificador);
    }
}
