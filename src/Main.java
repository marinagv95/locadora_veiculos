
import exception.pessoaException.CNPJInvalidoException;
import exception.pessoaException.CPFInvalidoException;
import exception.pessoaException.EmailInvalidoException;
import principal.principalAgencia.PrincipalAgencia;
import principal.principalVeiculo.PrincipalVeiculo;
import principal.principalPessoa.PrincipalPessoa;
import repositorio.agenciaRepositorio.AgenciaRepositorioImplementacao;
import repositorio.veiculoRepositorio.VeiculoRepositorioImplementacao;

import repositorio.pessoaRepositorio.PessoaRepositorioImplementacao;

import servico.agenciaServico.AgenciaServicoImplementacao;
import servico.pessoaServico.PessoaServicoImplementacao;

import servico.veiculoServico.VeiculoServicoImplementacao;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws EmailInvalidoException, CPFInvalidoException, CNPJInvalidoException {

        Scanner leitura = new Scanner(System.in);
        VeiculoRepositorioImplementacao veiculoRepositorio = new VeiculoRepositorioImplementacao();
        VeiculoServicoImplementacao veiculoServico = new VeiculoServicoImplementacao(veiculoRepositorio);
        PrincipalVeiculo principalVeiculo = new PrincipalVeiculo(veiculoServico);

        PessoaRepositorioImplementacao pessoaRepositorio = new PessoaRepositorioImplementacao();
        PessoaServicoImplementacao pessoaServico = new PessoaServicoImplementacao(pessoaRepositorio);
        PrincipalPessoa principalPessoa = new PrincipalPessoa(pessoaServico);

        AgenciaRepositorioImplementacao agenciaRepositorio = new AgenciaRepositorioImplementacao();
        AgenciaServicoImplementacao agenciaServico = new AgenciaServicoImplementacao(agenciaRepositorio);
        PrincipalAgencia principalAgencia = new PrincipalAgencia(agenciaServico);


        int opcao;
        do {
            System.out.println("\n==== 🎬 MENU PRINCIPAL ====");
            System.out.println("1️⃣  Gerenciar Veículos");
            System.out.println("2️⃣  Gerenciar Pessoas");
            System.out.println("3️⃣  Gerenciar Agências");
            System.out.println("0️⃣  Sair");
            System.out.print("Escolha uma opção: ");
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
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
                    System.out.println("👋 Saindo... Até a próxima!");
                    break;
                default:
                    System.out.println("❌ Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        leitura.close();
    }
}
