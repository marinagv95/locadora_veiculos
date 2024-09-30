package servico.aluguelServico;

import exception.aluguelException.AluguelNaoEncontradoException;
import exception.pessoaException.PessoaNaoEncontradaException;
import modelo.agencia.Agencia;
import modelo.aluguel.Aluguel;
import modelo.aluguel.DevolucaoAluguel;
import modelo.pessoa.Pessoa;
import modelo.veiculo.Veiculo;
import repositorio.aluguelRepositorio.AluguelRepositorio;
import servico.agenciaServico.AgenciaServico;
import servico.pessoaServico.PessoaServico;
import servico.veiculoServico.VeiculoServico;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AluguelServicoImplementacao <T extends Aluguel> implements AluguelServico<T> {

    private final PessoaServico<Pessoa> pessoaServico;
    private final VeiculoServico<Veiculo> veiculoServico;
    private final AgenciaServico<Agencia> agenciaServico;
    private final AluguelRepositorio<T> aluguelRepositorio;
    private List<DevolucaoAluguel> todosAlugueis;


    public AluguelServicoImplementacao(PessoaServico<Pessoa> pessoaServico, VeiculoServico<Veiculo> veiculoServico,
                                       AgenciaServico<Agencia> agenciaServico, AluguelRepositorio<T> aluguelRepositorio) {
        this.pessoaServico = pessoaServico;
        this.veiculoServico = veiculoServico;
        this.agenciaServico = agenciaServico;
        this.aluguelRepositorio = aluguelRepositorio;
        this.todosAlugueis = new ArrayList<>();
    }

    @Override
    public void cadastrarAluguel(T aluguel) throws Exception {
        aluguelRepositorio.salvarAluguel(aluguel);
    }

    @Override
    public T buscarAluguelPorIdentificador(String identificador) throws AluguelNaoEncontradoException, PessoaNaoEncontradaException {
        return aluguelRepositorio.buscarPorIdentificador(identificador).orElseThrow(()
                -> new AluguelNaoEncontradoException("Aluguel não encontrado!"));
    }

    @Override
    public List<T> listarAlugueis() {
        return aluguelRepositorio.alugueis();
    }

    @Override
    public void devolverVeiculo(T aluguel, DevolucaoAluguel devolucao) throws Exception {
        if (aluguel == null) {
            throw new IllegalArgumentException("Aluguel não pode ser nulo.");
        }

        Veiculo veiculo = aluguel.getVeiculo();
        veiculo.setDisponivel(true);
        veiculoServico.alterarVeiculo(veiculo);
        devolucao.getAgenciaDevolucao();
        aluguelRepositorio.salvarAluguel(aluguel);
    }

    @Override
    public List<Veiculo> listarVeiculosDisponiveis() {
        List<Veiculo> todosVeiculos = veiculoServico.listarVeiculos();

        List<Veiculo> veiculosDisponiveis = todosVeiculos.stream()
                .filter(Veiculo::estaDisponivel)
                .collect(Collectors.toList());

        return veiculosDisponiveis;
    }

    @Override
    public List<Aluguel> buscarAlugueisPorPessoa(Pessoa pessoa) {
        return aluguelRepositorio.alugueis().stream()
                .filter(aluguel -> aluguel.getPessoa().equals(pessoa))
                .collect(Collectors.toList());
    }
}
