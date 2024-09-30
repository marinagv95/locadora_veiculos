package principal.principalPessoa;

import exception.pessoaException.CNPJInvalidoException;
import exception.pessoaException.CPFInvalidoException;
import exception.pessoaException.EmailInvalidoException;
import exception.pessoaException.PessoaNaoEncontradaException;
import modelo.endereco.Endereco;
import modelo.pessoa.Pessoa;
import modelo.pessoa.PessoaFisica;
import modelo.pessoa.PessoaJuridica;
import servico.pessoaServico.PessoaServico;
import util.leitura.Leitor;
import visual.MenuPessoa;

import java.util.Optional;
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


    public void cadastrarPessoa() {
        try {
            Leitor.escrever("\n╔══════════════════════════════════╗");
            Leitor.escrever("║     ESCOLHA O TIPO DE CLIENTE    ║");
            Leitor.escrever("╠══════════════════════════════════╣");
            Leitor.escrever("║  1. 👤 Pessoa Física             ║");
            Leitor.escrever("║  2. 🏢 Pessoa Jurídica           ║");
            Leitor.escrever("╚══════════════════════════════════╝");
            int tipoPessoa = Integer.parseInt(Leitor.ler(leitura, "Opção: "));

            Pessoa pessoa = null;

            String nome = Leitor.ler(leitura, "Informe o nome da pessoa: ");
            String telefone = Leitor.ler(leitura, "Informe o telefone: ");
            String email = Leitor.ler(leitura, "Informe o email: ");

            String logradouro = Leitor.ler(leitura, "Informe o logradouro do endereço: ");
            String numero = Leitor.ler(leitura, "Informe o número do endereço: ");
            String cep = Leitor.ler(leitura, "Informe o CEP do endereço: ");
            String bairro = Leitor.ler(leitura, "Informe o bairro do endereço: ");
            String cidade = Leitor.ler(leitura, "Informe a cidade do endereço: ");
            String estado = Leitor.ler(leitura, "Informe o estado do endereço: ");
            Endereco endereco = new Endereco(logradouro, numero, cep, bairro, cidade, estado);

            switch (tipoPessoa) {
                case 1:
                    String cpf = Leitor.ler(leitura, "Informe o CPF: ");
                    pessoa = new PessoaFisica(nome, telefone, email, endereco, cpf);
                    break;
                case 2:
                    String cnpj = Leitor.ler(leitura, "Informe o CNPJ: ");
                    pessoa = new PessoaJuridica(nome, telefone, email, endereco, cnpj);
                    break;
                default:
                    Leitor.erro("❌ Tipo de pessoa inválido.");
                    return;
            }

            pessoaServico.adicionar(pessoa);
            Leitor.escrever("✅ Pessoa cadastrada com sucesso!");

        } catch (CPFInvalidoException | CNPJInvalidoException | EmailInvalidoException e) {
            Leitor.erro("❌ Erro ao cadastrar pessoa: " + e.getMessage());
        } catch (NumberFormatException e) {
            Leitor.erro("❌ Erro: Entrada inválida. Por favor, digite um número.");
        } catch (Exception e) {
            Leitor.erro("❌ Erro inesperado: " + e.getMessage());
        } finally {
            Leitor.aguardarContinuacao(leitura);
        }
    }



    private void alterarPessoa() {
        String identificador = Leitor.ler(leitura, "Informe o CPF ou CNPJ da pessoa que deseja alterar: ");

        try {
            Pessoa pessoa = pessoaServico.buscarPorIdenficador(identificador);

            Leitor.escrever("Pessoa atual:");
            Leitor.escrever(pessoa.toString());

            String nome = Leitor.ler(leitura, "Informe o novo nome da pessoa: ");
            String telefone = Leitor.ler(leitura, "Informe o novo telefone: ");
            String email = Leitor.ler(leitura, "Informe o novo email: ");

            pessoa.setNomePessoa(nome);
            pessoa.setTelefone(telefone);
            pessoa.setEmail(email);

            Leitor.escrever("✅ Pessoa alterada com sucesso!");

        } catch (CPFInvalidoException e) {
            Leitor.erro("❌ Erro: CPF inválido. " + e.getMessage());
        } catch (CNPJInvalidoException e) {
            Leitor.erro("❌ Erro: CNPJ inválido. " + e.getMessage());
        } catch (EmailInvalidoException e) {
            Leitor.erro("❌ Erro: Email inválido. " + e.getMessage());
        } catch (Exception e) {
            Leitor.erro("❌ Erro inesperado ao alterar pessoa: " + e.getMessage());
        }finally {
            Leitor.aguardarContinuacao(leitura);
        }
    }

    private void buscarPessoaPorIdentificador() {
        String identificador = Leitor.ler(leitura, "Informe o CPF ou CNPJ da pessoa que deseja buscar: ").trim();
        System.out.println("Buscando pessoa com identificador: " + identificador);
        try {
            Pessoa pessoa = pessoaServico.buscarPorIdenficador(identificador);

            if (pessoa != null) {
                Leitor.escrever("Pessoa encontrada:");
                Leitor.escrever(pessoa.toString());
            } else {
                Leitor.erro("❌ Pessoa não encontrada com o identificador: " + identificador);
            }
        } catch (Exception e) {
            Leitor.erro("❌ Erro ao buscar pessoa: " + e.getMessage());
        }finally {
            Leitor.aguardarContinuacao(leitura);
        }
    }

    private void removerPessoa() {
        String identificador = Leitor.ler(leitura, "Informe o CPF ou CNPJ da pessoa que deseja remover: ");
        System.out.println("Buscando pessoa com identificador: " + identificador);
        try {
            Pessoa pessoa = pessoaServico.buscarPorIdenficador(identificador);

            if (pessoa != null) {
                Leitor.escrever("Dados da pessoa a ser removida:");
                Leitor.escrever(pessoa.toString());

                String confirmacao = Leitor.ler(leitura, "Tem certeza que deseja remover esta pessoa? (digite 's' para sim): ");
                if (confirmacao.equalsIgnoreCase("s")) {
                    pessoaServico.remover(pessoa);
                    Leitor.escrever("✅ Pessoa removida com sucesso!");
                } else {
                    Leitor.escrever("🚫 Remoção cancelada.");
                }
            } else {
                Leitor.erro("❌ Pessoa não encontrada com o identificador: " + identificador);
            }
        } catch (Exception e) {
            Leitor.erro("❌ Erro ao remover pessoa: " + e.getMessage());
        }finally {
            Leitor.aguardarContinuacao(leitura);
        }
    }
}