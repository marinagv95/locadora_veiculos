package principal.principalVeiculo;

import exception.veiculoException.PlacaDuplicadaException;
import modelo.veiculo.Caminhao;
import modelo.veiculo.Carro;
import modelo.veiculo.Moto;
import modelo.veiculo.Veiculo;
import servico.veiculoServico.VeiculoServico;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Scanner;

public class PrincipalVeiculo {
    private VeiculoServico<Veiculo> veiculoServico;

    private Scanner leitura = new Scanner(System.in);

    public PrincipalVeiculo(VeiculoServico<Veiculo> veiculoServico) {
        this.veiculoServico = veiculoServico;
    }

    public void exibirMenu() {
        int opcao = 0;
        while (opcao != 5) {
            System.out.println("\n==== 🚗 Menu de Veículos ====");
            System.out.println("1. ➕ Cadastrar Veículo");
            System.out.println("2. ✏️ Alterar Veículo");
            System.out.println("3. 🔍 Buscar Veículo por placa");
            System.out.println("4. 🔍 Remover Veículo por placa");
            System.out.println("5. 🔙 Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarVeiculo();
                    break;
                case 2:
                    //alterarVeiculo();
                    break;
                case 3:
                    buscarVeiculoPorPlaca();
                    break;
                case 4:
                    removerVeiculoPorPlaca();
                    break;
                case 5:
                    System.out.println("🔙 Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("❌ Opção inválida, tente novamente.");
            }
        }
    }

    private void cadastrarVeiculo() {
        System.out.println("\n==== Escolha o tipo de veículo ====");
        System.out.println("1. Caminhão");
        System.out.println("2. Carro");
        System.out.println("3. Moto");
        System.out.print("Opção: ");
        int tipoVeiculo = leitura.nextInt();
        leitura.nextLine();

        Veiculo veiculo = null;

        System.out.print("Informe a placa do veículo: ");
        String placa = leitura.nextLine();
        Optional<Veiculo> veiculoExistente = veiculoServico.buscarVeiculoPorPlaca(placa);
        if (veiculoExistente.isPresent()) {
            System.out.println("❌ Erro: A placa " + placa + " já está cadastrada.");
            return;
        }

        System.out.print("Informe a marca do veículo: ");
        String marca = leitura.nextLine();
        System.out.print("Informe o modelo do veículo: ");
        String modelo = leitura.nextLine();
        System.out.print("O veículo está disponível? (true/false): ");
        boolean disponivel = leitura.nextBoolean();
        System.out.print("Informe o valor da diária (em R$): ");
        BigDecimal valorDiaria = leitura.nextBigDecimal();
        leitura.nextLine();

        switch (tipoVeiculo) {
            case 1:
                System.out.print("Informe a capacidade de carga (em toneladas): ");
                String capacidadeCarga = leitura.nextLine();
                veiculo = new Caminhao(placa, modelo, marca, disponivel, valorDiaria, capacidadeCarga);
                break;
            case 2:
                System.out.print("Informe o número de portas: ");
                int numeroPortas = leitura.nextInt();
                leitura.nextLine();
                System.out.print("Informe o tipo de combustível: ");
                String tipoCombustivel = leitura.nextLine();
                leitura.nextLine();
                veiculo = new Carro(placa, modelo, marca, disponivel, numeroPortas, valorDiaria, tipoCombustivel);
                break;
            case 3:
                System.out.print("Informe a cilindrada da moto: ");
                String cilindrada = leitura.nextLine();
                veiculo = new Moto(placa, marca, modelo, disponivel, valorDiaria, cilindrada);
                break;
            default:
                System.out.println("❌ Tipo de veículo inválido.");
                return;
        }
        try {
            veiculoServico.cadastrarVeiculo(veiculo);
            System.out.println("✅ Veículo cadastrado com sucesso!");
        } catch (PlacaDuplicadaException e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }

    private void buscarVeiculoPorPlaca() {
        System.out.print("Informe a placa do veículo que deseja buscar: ");
        String placa = leitura.nextLine();

        Optional<Veiculo> veiculo = veiculoServico.buscarVeiculoPorPlaca(placa);

        if (veiculo.isPresent()) {
            System.out.println("Veículo encontrado:");
            System.out.println(veiculo.toString());
        } else {
            System.out.println("❌ Veículo não encontrado com a placa: " + placa);
        }
    }

    public void removerVeiculoPorPlaca() {
        System.out.print("Informe a placa do veículo que deseja remover: ");
        String placa = leitura.nextLine();

        Optional<Veiculo> veiculoOpt = veiculoServico.buscarVeiculoPorPlaca(placa);

        if (!veiculoOpt.isPresent()) {
            System.out.println("❌ Veículo não encontrado com a placa informada.");
            return;
        }

        Veiculo veiculo = veiculoOpt.get();
        System.out.println("Dados do veículo a ser removido: " + veiculo);

        System.out.print("Tem certeza que deseja remover este veículo? (digite 's' para sim): ");
        String confirmacao = leitura.nextLine();

        if (confirmacao.equalsIgnoreCase("s")) {
            try {
                veiculoServico.removerVeiculo(placa);
                System.out.println("✅ Veículo removido com sucesso!");
            } catch (Exception e) {
                System.out.println("❌ Erro ao remover veículo: " + e.getMessage());
            }
        } else {
            System.out.println("🚫 Remoção cancelada.");
        }
    }
}
