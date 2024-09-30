package repositorio.aluguelRepositorio;

import exception.pessoaException.PessoaNaoEncontradaException;
import modelo.agencia.Agencia;
import modelo.aluguel.Aluguel;
import modelo.pessoa.Pessoa;
import modelo.veiculo.Veiculo;
import repositorio.agenciaRepositorio.AgenciaRepositorio;
import repositorio.pessoaRepositorio.PessoaRepositorio;
import repositorio.veiculoRepositorio.VeiculoRepositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AluguelRepositorioImplementacao <T extends Aluguel> extends AluguelRepositorio<T> {

    public List<T> bancoDados;
    private VeiculoRepositorio<Veiculo> veiculoRepositorio;
    private PessoaRepositorio<Pessoa> pessoaRepositorio;
    private AgenciaRepositorio<Agencia> agenciaRepositorio;

    public AluguelRepositorioImplementacao() {
        this.bancoDados = new ArrayList<>();
        this.veiculoRepositorio = veiculoRepositorio;
        this.pessoaRepositorio = pessoaRepositorio;
        this.agenciaRepositorio = agenciaRepositorio;
    }


    @Override
    public List<T> alugueis() {
        return new ArrayList<>(bancoDados);
    }

    @Override
    public T salvarAluguel(Aluguel aluguel) throws Exception {
        bancoDados.add((T) aluguel);
        return (T) aluguel;
    }

    @Override
    public void removerAluguel(T aluguel) throws Exception {
        bancoDados.remove(aluguel);
    }

    @Override
    public Optional<T> buscarPorIdentificador(String identificador) throws PessoaNaoEncontradaException {
        Optional<Pessoa> pessoaOpt = pessoaRepositorio.buscarPorIdentificador(identificador);
        if (pessoaOpt.isPresent()) {
            return bancoDados.stream()
                    .filter(aluguel -> aluguel.getPessoa().equals(pessoaOpt.get()))
                    .findFirst();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Veiculo> buscarVeiculoDisponivel(String placa) {
        Optional<Veiculo> veiculoOpt = veiculoRepositorio.buscarPorPlaca(placa);
        if (veiculoOpt.isPresent()) {
            Veiculo veiculo = veiculoOpt.get();
            if (veiculo.estaDisponivel()) {
                return veiculoOpt;
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Pessoa> buscarCliente(String identificador) throws PessoaNaoEncontradaException {
        return pessoaRepositorio.buscarPorIdentificador(identificador);
    }

    @Override
    public List<Agencia> buscarAgenciasDisponiveis() {
        return agenciaRepositorio.listar();
    }
}
