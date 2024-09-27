import principal.principalAgencia.PrincipalAgencia;
import principal.principalPessoa.PrincipalPessoa;
import principal.principalVeiculo.PrincipalVeiculo;
import repositorio.agenciaRepositorio.AgenciaRepositorioImplementacao;
import repositorio.pessoaRepositorio.PessoaRepositorioImplementacao;
import repositorio.veiculoRepositorio.VeiculoRepositorioImplementacao;
import servico.agenciaServico.AgenciaServicoImplementacao;
import servico.pessoaServico.PessoaServicoImplementacao;
import servico.veiculoServico.VeiculoServicoImplementacao;
import visual.MenuPrincipal;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        VeiculoRepositorioImplementacao veiculoRepositorio = new VeiculoRepositorioImplementacao();
        VeiculoServicoImplementacao veiculoServico = new VeiculoServicoImplementacao(veiculoRepositorio);
        PrincipalVeiculo principalVeiculo = new PrincipalVeiculo(veiculoServico);

        PessoaRepositorioImplementacao pessoaRepositorio = new PessoaRepositorioImplementacao();
        PessoaServicoImplementacao pessoaServico = new PessoaServicoImplementacao(pessoaRepositorio);
        PrincipalPessoa principalPessoa = new PrincipalPessoa(pessoaServico);

        AgenciaRepositorioImplementacao agenciaRepositorio = new AgenciaRepositorioImplementacao();
        AgenciaServicoImplementacao agenciaServico = new AgenciaServicoImplementacao(agenciaRepositorio);
        PrincipalAgencia principalAgencia = new PrincipalAgencia(agenciaServico);

        int opcao = 0;
        try {
            do {
                menuPrincipal.menuPrincipal();

                boolean opcaoInvalida = true;

                while (opcaoInvalida) {
                    System.out.print("Escolha uma opção: ");
                    if (leitura.hasNextInt()) {
                        opcao = leitura.nextInt();
                        leitura.nextLine();

                        switch (opcao) {
                            case 1:
                                principalVeiculo.exibirMenu();
                                opcaoInvalida = false;
                                break;
                            case 2:
                                principalPessoa.exibirMenuPessoa();
                                opcaoInvalida = false;
                                break;
                            case 3:
                                principalAgencia.exibirMenuAgencia();
                                opcaoInvalida = false;
                                break;
                            case 0:
                                System.out.println("👋 Saindo... Até a próxima!");
                                opcaoInvalida = false;
                                break;
                            default:
                                System.out.println("❌ Opção inválida. Tente novamente.");
                                break;
                        }
                    } else {
                        System.out.println("❌ Entrada inválida. Digite um número.");
                        leitura.nextLine();
                    }
                }

            } while (opcao != 0);
        } catch (Exception e) {
            System.err.println("Ocorreu um erro: " + e.getMessage());
        } finally {
            leitura.close();
        }
    }
}
