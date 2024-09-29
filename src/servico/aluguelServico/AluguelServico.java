package servico.aluguelServico;

import exception.aluguelException.AluguelNaoEncontradoException;
import exception.veiculoException.VeiculoNaoExistenteException;
import modelo.aluguel.Aluguel;

import modelo.aluguel.DevolucaoAluguel;
import modelo.veiculo.Veiculo;

import java.util.List;


public interface AluguelServico<T extends Aluguel> {
    public void alugarVeiculo(T aluguel) throws VeiculoNaoExistenteException;
    public void retiradaVeiculo(T aluguel) throws AluguelNaoEncontradoException;
    public void devolverVeiculo(DevolucaoAluguel devolucaoAluguel);
    public T buscarAluguelPorVeiculo(Veiculo veiculo) throws AluguelNaoEncontradoException;
    public List<Aluguel> buscarAluguelPorPessoa(String identificador);
    public void alterarAluguel(T aluguel) throws AluguelNaoEncontradoException;
    public void removerAluguel(T aluguel) throws AluguelNaoEncontradoException;

}
