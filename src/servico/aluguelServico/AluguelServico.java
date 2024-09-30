package servico.aluguelServico;

import exception.aluguelException.AluguelNaoEncontradoException;
import exception.pessoaException.PessoaNaoEncontradaException;
import modelo.aluguel.Aluguel;

import modelo.aluguel.DevolucaoAluguel;
import modelo.pessoa.Pessoa;
import modelo.veiculo.Veiculo;

import java.util.List;


public interface AluguelServico<T extends Aluguel> {
   public void cadastrarAluguel(T aluguel) throws Exception;
   public T buscarAluguelPorIdentificador(String identificador) throws AluguelNaoEncontradoException, PessoaNaoEncontradaException;
   public List<T> listarAlugueis();
   public void devolverVeiculo(T aluguel, DevolucaoAluguel devolucao) throws Exception;
   public List<Veiculo> listarVeiculosDisponiveis();
   public List<Aluguel> buscarAlugueisPorPessoa(Pessoa pessoa);
}
