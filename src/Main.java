import exception.pessoaException.CNPJInvalidoException;
import exception.pessoaException.CPFInvalidoException;
import exception.pessoaException.EmailInvalidoException;
import principal.principalAgencia.PrincipalAgencia;
import principal.principalAluguel.PrincipalAluguel;
import principal.principalAluguel.PrincipalConsultaAluguel;
import principal.principalPessoa.PrincipalPessoa;
import principal.principalVeiculo.PrincipalVeiculo;
import repositorio.agenciaRepositorio.AgenciaRepositorioImplementacao;
import repositorio.aluguelRepositorio.AluguelRepositorioImplementacao;
import repositorio.pessoaRepositorio.PessoaRepositorioImplementacao;
import repositorio.veiculoRepositorio.VeiculoRepositorioImplementacao;
import servico.agenciaServico.AgenciaServicoImplementacao;
import servico.aluguelServico.AluguelServicoImplementacao;
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

        AluguelRepositorioImplementacao aluguelRepositorio = new AluguelRepositorioImplementacao();
        AluguelServicoImplementacao aluguelServico = new AluguelServicoImplementacao(aluguelRepositorio);
        PrincipalAluguel principalAluguel = new PrincipalAluguel(aluguelServico, veiculoServico, pessoaServico);
        PrincipalConsultaAluguel principalConsultaAluguel = new PrincipalConsultaAluguel(aluguelServico, veiculoServico);

        int opcao = -1;
        try {
            do {
                menuPrincipal.menuPrincipalGeral();
                System.out.print("Escolha uma opção: ");

                if (leitura.hasNextInt()) {
                    opcao = leitura.nextInt();
                    leitura.nextLine();
                    switch (opcao) {
                        case 1:
                            boolean submenuValido = true;
                            while (submenuValido) {
                                menuPrincipal.menuPrincipal();
                                System.out.print("Escolha uma opção: ");

                                if (leitura.hasNextInt()) {
                                    int opcaoSubmenu = leitura.nextInt();
                                    leitura.nextLine();

                                    switch (opcaoSubmenu) {
                                        case 1:
                                            principalVeiculo.exibirMenu();
                                            break;
                                        case 2:
                                            principalPessoa.exibirMenuPessoa();
                                            break;
                                        case 3:
                                            principalAgencia.exibirMenuAgencia();
                                            break;
                                        case 0:
                                            submenuValido = false;
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
                            break;

                        case 2:
                            principalAluguel.exibirMenuAluguel();
                            break;
                        case 0:
                            System.out.println("👋 Saindo... Até a próxima!");
                            break;
                        default:
                            System.out.println("❌ Opção inválida. Tente novamente.");
                            break;
                    }
                } else {
                    System.out.println("❌ Entrada inválida. Digite um número.");
                    leitura.nextLine();
                }

            } while (opcao != 0);
        } catch (Exception e) {
            System.err.println("Ocorreu um erro: " + e.getMessage());
        } finally {
            leitura.close();
        }
    }
}
