package principal.principalAluguel;

import exception.aluguelException.DataInvalidaException;
import exception.aluguelException.HoraInvalidaException;
import modelo.aluguel.Aluguel;
import modelo.pessoa.Pessoa;
import modelo.veiculo.Veiculo;
import servico.aluguelServico.AluguelServico;
import servico.pessoaServico.PessoaServico;
import servico.veiculoServico.VeiculoServico;
import util.leitura.Leitor;
import visual.MenuAluguel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.Scanner;

public class PrincipalAluguel {
    private final MenuAluguel menuAluguel;
    private final AluguelServico<Aluguel> aluguelServico;
    private final VeiculoServico<Veiculo> veiculoServico;
    private final PessoaServico<Pessoa> pessoaServico;
    private final Scanner leitura;

    public PrincipalAluguel(AluguelServico<Aluguel> aluguelServico,
                            VeiculoServico<Veiculo> veiculoServico,
                            PessoaServico<Pessoa> pessoaServico) {
        this.menuAluguel = new MenuAluguel();
        this.aluguelServico = aluguelServico;
        this.veiculoServico = veiculoServico;
        this.pessoaServico = pessoaServico;
        this.leitura = new Scanner(System.in);
    }

    public void exibirMenuAluguel() {
        int opcao = 0;
        while (opcao != 5) {
            menuAluguel.exibirMenuAluguel();
            System.out.print("🎬 Escolha uma opção: ");
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    fazerAluguel();
                    break;
                case 5:
                    System.out.println("🔙 Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("❌ Opção inválida, tente novamente.");
            }
        }
    }

    private void fazerAluguel() {
        try {
            String identificadorPessoa = Leitor.ler(leitura, "Informe o CPF ou CNPJ da pessoa: ");
            System.out.println("Buscando pessoa com identificador: " + identificadorPessoa);
            Pessoa pessoa = pessoaServico.buscarPorIdenficador(identificadorPessoa);

            if (pessoa == null) {
                Leitor.erro("❌ Pessoa não encontrada. Vamos cadastrar uma nova pessoa.");
                // Aqui você pode chamar um método para cadastrar a nova pessoa
                pessoaServico.adicionar(pessoa);
                return; // Retorna para não prosseguir com o aluguel
            }

            boolean veiculoDisponivel = false;
            Veiculo veiculo = null;

            while (!veiculoDisponivel) {
                String identificadorVeiculo = Leitor.ler(leitura, "Informe a placa do veículo: ");
                Optional<Veiculo> optionalVeiculo = veiculoServico.buscarVeiculoPorPlaca(identificadorVeiculo);

                if (optionalVeiculo.isEmpty()) {
                    Leitor.erro("❌ Veículo não encontrado. Tente novamente.");
                    continue;
                }

                veiculo = optionalVeiculo.get();

                if (!veiculo.getDisponivel()) {
                    Leitor.erro("❌ Veículo não disponível. Tente outro veículo.");
                } else {
                    veiculoDisponivel = true;
                }
            }

            LocalDate dataInicio = LocalDate.parse(Leitor.ler(leitura, "Informe a data de início (YYYY-MM-DD): "));
            LocalTime horaInicio = LocalTime.parse(Leitor.ler(leitura, "Informe a hora de início (HH:MM): "));

            Aluguel aluguel = new Aluguel(veiculo, pessoa, null, dataInicio, horaInicio);
            aluguelServico.alugarVeiculo(aluguel);
            Leitor.escrever("✅ Aluguel finalizado com sucesso!");

        } catch (DataInvalidaException | HoraInvalidaException e) {
            Leitor.erro("❌ Erro ao cadastrar aluguel: " + e.getMessage());
        } catch (Exception e) {
            Leitor.erro("❌ Erro inesperado: " + e.getMessage());
        } finally {
            Leitor.aguardarContinuacao(leitura);
        }
    }

}
