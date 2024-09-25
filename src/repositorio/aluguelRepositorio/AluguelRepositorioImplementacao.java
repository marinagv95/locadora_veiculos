package repositorio.aluguelRepositorio;

import exception.aluguelException.AluguelNaoEncontradoException;
import modelo.aluguel.Aluguel;

import java.util.ArrayList;
import java.util.List;

public class AluguelRepositorioImplementacao <T extends Aluguel> extends AluguelRepositorio<T> {

    public List<T> bancoDados;

    public AluguelRepositorioImplementacao() {
        this.bancoDados = new ArrayList<>();
    }


    @Override
    public T salvar(T aluguel) {
        bancoDados.add(aluguel);
        return aluguel;
    }

    @Override
    public List<T> listarAlugueis() {
        return new ArrayList<>(bancoDados);
    }

    @Override
    public T alterarAluguel(T aluguel) throws AluguelNaoEncontradoException {
        for (int i = 0; i < bancoDados.size(); i++) {
            if (bancoDados.get(i).getVeiculo().getPlaca().equals(aluguel.getVeiculo().getPlaca())) {
                bancoDados.set(i, aluguel);
                return aluguel;
            }
        }
        throw new AluguelNaoEncontradoException();
    }

    @Override
    public T cancelarAluguel(String placa) {
        T aluguelRemovido = null;
        for (int i = 0; i < bancoDados.size(); i++) {
            if (bancoDados.get(i).getVeiculo().getPlaca().equals(placa)) {
                aluguelRemovido = bancoDados.remove(i);
                break;
            }
        }
        return aluguelRemovido;
    }

    @Override
    public boolean existeAluguel(String placa) {
        return bancoDados.stream()
                .anyMatch(aluguel -> aluguel.getVeiculo().getPlaca().equals(placa));
    }

    @Override
    public T buscarAluguel(String placa) throws AluguelNaoEncontradoException {
        for (T aluguel : bancoDados) {
            if (aluguel.getVeiculo().getPlaca().equals(placa)) {
                return aluguel;
            }
        }
        throw new AluguelNaoEncontradoException();
    }
}