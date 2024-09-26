package principal.principalPessoa;

import exception.pessoaException.CNPJInvalidoException;
import exception.pessoaException.CPFInvalidoException;
import exception.pessoaException.EmailInvalidoException;
import modelo.pessoa.Pessoa;
import modelo.pessoa.PessoaFisica;
import modelo.pessoa.PessoaJuridica;
import servico.pessoaServico.PessoaServico;
import visual.MenuPessoa;

import java.util.Scanner;

public class PrincipalPessoa {
    MenuPessoa menuPessoa = new MenuPessoa();
    private PessoaServico<Pessoa> pessoaServico;
    private Scanner leitura;

    public PrincipalPessoa(PessoaServico<Pessoa> pessoaServico) {
        this.pessoaServico = pessoaServico;
        this.leitura = new Scanner(System.in);
    }

    public void exibirMenuPessoa() throws CPFInvalidoException, CNPJInvalidoException, EmailInvalidoException {

        int opcao = 0;
        while (opcao != 5) {
            menuPessoa.exibirMenuPessoa();
            System.out.print("🎬 Escolha uma opção: ");
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarPessoa();
                    break;
                case 2:
                    alterarPessoa();
                    break;
                case 3:
                    buscarPessoaPorIdentificador();
                    break;
                case 4:
                    removerPessoa();
                    break;
                case 5:
                    System.out.println("🔙 Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("❌ Opção inválida, tente novamente.");
            }
        }
    }


    private void cadastrarPessoa() throws CPFInvalidoException, CNPJInvalidoException, EmailInvalidoException {

        System.out.println("\n==== Escolha o tipo de Pessoa ====");
        System.out.println("1. Pessoa Física");
        System.out.println("2. Pessoa Jurídica");
        System.out.print("Opção: ");
        int tipoPessoa = leitura.nextInt();
        leitura.nextLine();

        Pessoa pessoa = null;

        System.out.print("Informe o nome da pessoa: ");
        String nome = leitura.nextLine();
        System.out.print("Informe o telefone: ");
        String telefone = leitura.nextLine();
        System.out.print("Informe o email: ");
        String email = leitura.nextLine();

        if (tipoPessoa == 1) {
            System.out.print("Informe o CPF: ");
            String cpf = leitura.nextLine();
            pessoa = new PessoaFisica(nome, telefone, email, null, cpf);
        } else if (tipoPessoa == 2) {
            System.out.print("Informe o CNPJ: ");
            String cnpj = leitura.nextLine();
            pessoa = new PessoaJuridica(nome, telefone, email, null, cnpj);
        } else {
            System.out.println("❌ Tipo de pessoa inválido.");
            return;
        }

        try {
            pessoaServico.adicionar(pessoa);
            System.out.println("✅ Pessoa cadastrada com sucesso!");
        } catch (Exception e) {
            System.out.println("❌ Erro ao cadastrar pessoa: " + e.getMessage());
        }
    }

    private void alterarPessoa() {
        System.out.print("Informe o CPF ou CNPJ da pessoa que deseja alterar: ");
        String identificador = leitura.nextLine();

        try {
            Pessoa pessoa = pessoaServico.buscarPorIdenficador(identificador);

            System.out.println("Pessoa atual:");
            System.out.println(pessoa);

            System.out.print("Informe o novo nome da pessoa: ");
            String nome = leitura.nextLine();
            System.out.print("Informe o novo telefone: ");
            String telefone = leitura.nextLine();
            System.out.print("Informe o novo email: ");
            String email = leitura.nextLine();

            pessoa.setNomePessoa(nome);
            pessoa.setTelefone(telefone);
            pessoa.setEmail(email);

            System.out.println("✅ Pessoa alterada com sucesso!");
        } catch (Exception e) {
            System.out.println("❌ Erro ao alterar pessoa: " + e.getMessage());
        }
    }

    private void buscarPessoaPorIdentificador() {
        System.out.print("Informe o CPF ou CNPJ da pessoa que deseja buscar: ");
        String identificador = leitura.nextLine();

        try {
            Pessoa pessoa = pessoaServico.buscarPorIdenficador(identificador);
            System.out.println("Pessoa encontrada:");
            System.out.println(pessoa);
        } catch (Exception e) {
            System.out.println("❌ Pessoa não encontrada: " + e.getMessage());
        }
    }

    private void removerPessoa() {
        System.out.print("Informe o CPF ou CNPJ da pessoa que deseja remover: ");
        String identificador = leitura.nextLine();

        try {
            Pessoa pessoa = pessoaServico.buscarPorIdenficador(identificador);

            pessoaServico.remover(pessoa);
            System.out.println("✅ Pessoa removida com sucesso!");
        } catch (Exception e) {
            System.out.println("❌ Erro ao remover pessoa: " + e.getMessage());
        }
    }
}
