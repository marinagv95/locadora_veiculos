import modelo.veiculo.Veiculo;
import modelo.veiculo.Caminhao;
import modelo.veiculo.Carro;
import modelo.veiculo.Moto;
import principal.principalVeiculo.PrincipalVeiculo;
import repositorio.veiculoRepositorio.VeiculoRepositorioImplementacao;
import servico.veiculoServico.VeiculoServicoImplementacao;

public class Main {
    public static void main(String[] args) throws Exception {

        VeiculoRepositorioImplementacao<Veiculo> veiculoRepositorio = new VeiculoRepositorioImplementacao<>();

        VeiculoServicoImplementacao<Veiculo> veiculoServico = new VeiculoServicoImplementacao<>(veiculoRepositorio);

        PrincipalVeiculo principalVeiculo = new PrincipalVeiculo(veiculoServico);

        principalVeiculo.exibirMenu();
    }
}
