package servico.aluguelServico;

import exception.aluguelException.AluguelNaoEncontradoException;
import exception.veiculoException.VeiculoNaoExistenteException;
import modelo.aluguel.Aluguel;

public interface AluguelServico<T extends Aluguel> {
    public void salvar(T aluguel);
    public void excluir(T aluguel);
    public void editar(T aluguel) throws AluguelNaoEncontradoException;
    public T buscarPorPlaca(String placa) throws AluguelNaoEncontradoException,VeiculoNaoExistenteException;
    public T buscarPorPessoa(String nomePessoa);
}
