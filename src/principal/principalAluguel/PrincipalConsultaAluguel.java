package principal.principalAluguel;

import exception.aluguelException.AluguelNaoEncontradoException;
import modelo.aluguel.Aluguel;
import modelo.veiculo.Veiculo;
import servico.aluguelServico.AluguelServico;
import servico.veiculoServico.VeiculoServico;
import visual.MenuAluguel;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class PrincipalConsultaAluguel {
    private AluguelServico<Aluguel> aluguelServico;
    private VeiculoServico<Veiculo> veiculoServico;
    private Scanner leitura;

    public PrincipalConsultaAluguel(AluguelServico<Aluguel> aluguelServico, VeiculoServico<Veiculo> veiculoServico) {
        this.aluguelServico = aluguelServico;
        this.veiculoServico = veiculoServico;
        this.leitura = new Scanner(System.in);
    }

    public void exibirMenuConsultaAluguel() throws AluguelNaoEncontradoException {
        MenuAluguel menuConsulta = new MenuAluguel();
        int opcao = 0;

        while (opcao != 5) {
            menuConsulta.exibirMenuConsultarAluguel();
            System.out.print("🎬 Escolha uma opção: ");
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    consultarAluguelPorPessoa();
                    break;
                case 2:
                    consultarAluguelPorVeiculo();
                    break;
                case 3:
                    System.out.println("🔙 Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("❌ Opção inválida, tente novamente.");
            }
        }
    }

    private void consultarAluguelPorPessoa() {
        System.out.print("Informe o CPF ou CNPJ da pessoa: ");
        String identificadorPessoa = leitura.nextLine();
        List<Aluguel> alugueis = aluguelServico.buscarAluguelPorPessoa(identificadorPessoa);

        if (alugueis.isEmpty()) {
            System.out.println("❌ Nenhum aluguel encontrado para esta pessoa.");
        } else {
            System.out.println("📋 Alugueis encontrados:");
            for (Aluguel aluguel : alugueis) {
                System.out.println(aluguel);
            }
        }
    }

    private void consultarAluguelPorVeiculo() throws AluguelNaoEncontradoException {
        System.out.print("Informe a placa do veículo: ");
        String placaVeiculo = leitura.nextLine();
        Optional<Veiculo> optionalVeiculo  = veiculoServico.buscarVeiculoPorPlaca(placaVeiculo);

        if (optionalVeiculo == null) {
            System.out.println("❌ Veículo não encontrado.");
            return;
        }

        Veiculo veiculo = optionalVeiculo.get();

        List<Aluguel> alugueis = (List<Aluguel>) aluguelServico.buscarAluguelPorVeiculo(veiculo);
        if (alugueis.isEmpty()) {
            System.out.println("❌ Nenhum aluguel encontrado para o veículo informado.");
        } else {
            // Exibir os detalhes dos alugueis encontrados
            for (Aluguel aluguel : alugueis) {
                System.out.println(aluguel); // Ou implemente um método de exibição melhor
            }
        }
    }
}
