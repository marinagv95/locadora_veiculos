import modelo.veiculo.Carro;
import modelo.veiculo.Moto;
import servico.veiculoServico.VeiculoServico;
import servico.veiculoServico.VeiculoServicoImplementacao;

public class Main {
    public static void main(String[] args) throws Exception{
        VeiculoServico<Carro> carroServico = new VeiculoServicoImplementacao();
        VeiculoServico<Moto> motoServico = new VeiculoServicoImplementacao();

        //Carro ferrari= new Carro("placa", "modelo", "marca",true);
        //carroServico.cadastrarVeiculo(ferrari);

        carroServico.listarVeiculos().stream().forEach(System.out::println); //fazer toString

//        ferrari.setDisponivel(true);
//        ferrari.setModelo("mopdelo");
//        carroServico.alterarVeiculo(ferrari);

    }
}
