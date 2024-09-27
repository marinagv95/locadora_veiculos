package principal.principalVeiculo;

import exception.veiculoException.PlacaDuplicadaException;
import modelo.veiculo.Caminhao;
import modelo.veiculo.Carro;
import modelo.veiculo.Moto;
import modelo.veiculo.Veiculo;
import servico.veiculoServico.VeiculoServico;
import util.leitura.Leitor;
import visual.MenuVeiculos;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class PrincipalVeiculo {
    MenuVeiculos menuVeiculos = new MenuVeiculos();
    private VeiculoServico<Veiculo> veiculoServico;

    private Scanner leitura;

    public PrincipalVeiculo(VeiculoServico<Veiculo> veiculoServico) {
        this.veiculoServico = veiculoServico;
        this.leitura = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao = 0;
        while (opcao != 5) {
            menuVeiculos.exibirMenuVeiculos();
            opcao = Integer.parseInt(Leitor.ler(leitura, "🎬 Escolha uma opção: "));

            switch (opcao) {
                case 1:
                    cadastrarVeiculo();
                    break;
                case 2:
                    alterarVeiculo();
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
        int tipoVeiculo = Integer.parseInt(Leitor.ler(leitura, "Opção: "));

        Veiculo veiculo = null;

        String placa = Leitor.ler(leitura, "Informe a placa do veículo: ");
        Optional<Veiculo> veiculoExistente = veiculoServico.buscarVeiculoPorPlaca(placa);
        if (veiculoExistente.isPresent()) {
            Leitor.erro("❌ Erro: A placa " + placa + " já está cadastrada.");
            return;
        }

        String marca = Leitor.ler(leitura, "Informe a marca do veículo: ");
        String modelo = Leitor.ler(leitura, "Informe o modelo do veículo: ");
        boolean disponivel = Boolean.parseBoolean(Leitor.ler(leitura, "O veículo está disponível? (true/false): "));
        BigDecimal valorDiaria = new BigDecimal(Leitor.ler(leitura, "Informe o valor da diária (em R$): "));

        switch (tipoVeiculo) {
            case 1:
                String capacidadeCarga = Leitor.ler(leitura, "Informe a capacidade de carga (em toneladas): ");
                veiculo = new Caminhao(placa, modelo, marca, disponivel, valorDiaria, capacidadeCarga);
                break;
            case 2:
                int numeroPortas = Integer.parseInt(Leitor.ler(leitura, "Informe o número de portas: "));
                String tipoCombustivel = Leitor.ler(leitura, "Informe o tipo de combustível: ");
                veiculo = new Carro(placa, modelo, marca, disponivel, numeroPortas, valorDiaria, tipoCombustivel);
                break;
            case 3:
                String cilindrada = Leitor.ler(leitura, "Informe a cilindrada da moto: ");
                veiculo = new Moto(placa, modelo, marca, disponivel, valorDiaria, cilindrada);
                break;
            default:
                Leitor.erro("❌ Tipo de veículo inválido.");
                return;
        }
        try {
            veiculoServico.cadastrarVeiculo(veiculo);
            Leitor.escrever("✅ Veículo cadastrado com sucesso!");
        } catch (InputMismatchException e) {
            System.out.println("❌ Erro: Tipo de dado incorreto. Tente novamente.");
            leitura.nextLine();
        } catch (PlacaDuplicadaException e) {
            System.out.println("❌ Erro: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Erro: " + e.getMessage());
        } finally {
            Leitor.aguardarContinuacao(leitura);
        }
    }

    private void alterarVeiculo() {
        String placa = Leitor.ler(leitura, "Informe a placa do veículo que deseja alterar: ");

        Optional<Veiculo> veiculoExistente = veiculoServico.buscarVeiculoPorPlaca(placa);

        if (!veiculoExistente.isPresent()) {
            Leitor.erro("❌ Veículo não encontrado com a placa: " + placa);
            return;
        }

        Veiculo veiculo = veiculoExistente.get();


        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║             VEÍCULO ATUAL             ║");
        System.out.println("╠═══════════════════════════════════════╣");
        System.out.printf(" ║ Placa:          %s%n", veiculo.getPlaca());
        System.out.printf(" ║ Marca:          %s%n", veiculo.getMarca());
        System.out.printf(" ║ Modelo:         %s%n", veiculo.getModelo());
        System.out.printf(" ║ Disponível:      %s%n", (veiculo.getDisponivel() ? "Sim" : "Não"));
        System.out.printf(" ║ Valor da diária: R$ %.2f%n", veiculo.getValorDiaria());
        System.out.println("╚═══════════════════════════════════════╝");

        if (veiculo instanceof Carro) {
            Carro carro = (Carro) veiculo;
            Leitor.escrever("Número de portas: " + carro.getNumeroPortas());
            Leitor.escrever("Tipo de combustível: " + carro.getTipoCombustivel());
        } else if (veiculo instanceof Caminhao) {
            Caminhao caminhao = (Caminhao) veiculo;
            Leitor.escrever("Capacidade de carga: " + caminhao.getCapacidadeCarga() + " toneladas");
        } else if (veiculo instanceof Moto) {
            Moto moto = (Moto) veiculo;
            Leitor.escrever("Cilindrada: " + moto.getCilindrada() + " cc");
        }


        boolean dadosValidos = false;
        while (!dadosValidos) {
            try {
                String marca = Leitor.ler(leitura, "Informe a nova marca do veículo: ");
                String modelo = Leitor.ler(leitura, "Informe o novo modelo do veículo: ");
                boolean disponivel = Boolean.parseBoolean(Leitor.ler(leitura, "O veículo está disponível? (true/false): "));
                BigDecimal valorDiaria = new BigDecimal(Leitor.ler(leitura, "Informe o novo valor da diária (em R$): "));

                veiculo.setMarca(marca);
                veiculo.setModelo(modelo);
                veiculo.setDisponivel(disponivel);
                veiculo.setValorDiaria(valorDiaria);


                if (veiculo instanceof Carro) {
                    int numeroPortas = Integer.parseInt(Leitor.ler(leitura, "Informe o novo número de portas (2 ou 4): "));
                    String tipoCombustivel = Leitor.ler(leitura, "Informe o novo tipo de combustível (gasolina, álcool ou flex): ");
                    ((Carro) veiculo).setNumeroPortas(numeroPortas);
                    ((Carro) veiculo).setTipoCombustivel(tipoCombustivel);
                } else if (veiculo instanceof Caminhao) {
                    String capacidadeCarga = Leitor.ler(leitura, "Informe a nova capacidade de carga (em toneladas): ");
                    ((Caminhao) veiculo).setCapacidadeCarga(capacidadeCarga);
                } else if (veiculo instanceof Moto) {
                    String cilindrada = Leitor.ler(leitura, "Informe a nova cilindrada da moto: ");
                    ((Moto) veiculo).setCilindrada(cilindrada);
                }
                dadosValidos = true;

            } catch (InputMismatchException e) {
                System.out.println("❌ Entrada inválida. Por favor, insira os dados corretamente.");
                leitura.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Erro: " + e.getMessage());
            }
        }
        veiculoServico.alterarVeiculo(veiculo);
        Leitor.escrever("✅ Veículo alterado com sucesso!");
        Leitor.aguardarContinuacao(leitura);
    }

    private void buscarVeiculoPorPlaca() {
        String placa = Leitor.ler(leitura, "Informe a placa do veículo que deseja buscar: ");

        Optional<Veiculo> veiculo = veiculoServico.buscarVeiculoPorPlaca(placa);

        if (veiculo.isPresent()) {
            Leitor.escrever("Veículo encontrado:");
            Leitor.escrever(veiculo.get().toString());
        } else {
            Leitor.erro("❌ Veículo não encontrado com a placa: " + placa);
        }
        Leitor.aguardarContinuacao(leitura);
    }

    public void removerVeiculoPorPlaca() {
        String placa = Leitor.ler(leitura, "Informe a placa do veículo que deseja remover: ");

        Optional<Veiculo> veiculoOpt = veiculoServico.buscarVeiculoPorPlaca(placa);

        if (!veiculoOpt.isPresent()) {
            Leitor.erro("❌ Veículo não encontrado com a placa informada.");
            return;
        }

        Veiculo veiculo = veiculoOpt.get();
        Leitor.escrever("Dados do veículo a ser removido: " + veiculo);

        String confirmacao = Leitor.ler(leitura, "Tem certeza que deseja remover este veículo? (digite 's' para sim): ");

        if (confirmacao.equalsIgnoreCase("s")) {
            try {
                veiculoServico.removerVeiculo(placa);
                Leitor.escrever("✅ Veículo removido com sucesso!");
            } catch (Exception e) {
                Leitor.erro("❌ Erro ao remover veículo: " + e.getMessage());
            }
        } else {
            Leitor.escrever("🚫 Remoção cancelada.");
        }
        Leitor.aguardarContinuacao(leitura);
    }
}
