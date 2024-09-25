package repositorio.aluguelRepositorio;


import exception.aluguelException.AluguelNaoEncontradoException;
import modelo.aluguel.Aluguel;


import java.util.List;


public abstract class AluguelRepositorio<T extends Aluguel> {
    public abstract T salvar(T aluguel) ;
    public abstract List<T> listarAlugueis();
    public abstract T alterarAluguel(T aluguel) throws AluguelNaoEncontradoException;
    public abstract T cancelarAluguel(String placa);
    public abstract boolean existeAluguel(String placa);
    public abstract T buscarAluguel(String placa) throws AluguelNaoEncontradoException;
}