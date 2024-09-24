
import modelo.veiculo.Veiculo;
import principal.principalVeiculo.PrincipalVeiculo;
import servico.veiculoServico.VeiculoServicoImplementacao;


public class Main {
    public static void main(String[] args) throws Exception{
        VeiculoServicoImplementacao<Veiculo> veiculoServico = new VeiculoServicoImplementacao<>();
        PrincipalVeiculo principalVeiculo = new PrincipalVeiculo(veiculoServico);

        principalVeiculo.exibirMenu();



    }
}
