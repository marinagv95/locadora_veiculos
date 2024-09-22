package servico.veiculoServico;

import modelo.veiculo.Veiculo;

import java.util.List;

//contrato
public interface VeiculoServico <T extends Veiculo> {
    public T cadastrarVeiculo(T veiculo);
    public T alterarVeiculo(T veiculo);
    public void removerVeiculo(T veiculo);

    public T buscarVeiculoPorNome(String nome);
    List<T> listarVeiculos();
}
