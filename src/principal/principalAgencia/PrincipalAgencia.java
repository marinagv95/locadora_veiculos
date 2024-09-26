package principal.principalAgencia;

import exception.enderecoException.CEPInvalidoException;
import modelo.agencia.Agencia;
import modelo.endereco.Endereco;
import servico.agenciaServico.AgenciaServico;
import visual.MenuAgencia;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PrincipalAgencia {
    MenuAgencia menuAgencia = new MenuAgencia();
    private AgenciaServico<Agencia> agenciaServico;
    private Scanner leitura;

    public PrincipalAgencia(AgenciaServico<Agencia> agenciaServico) {
        this.agenciaServico = agenciaServico;
        this.leitura = new Scanner(System.in);
    }

    public void exibirMenuAgencia() {
        int opcao = 0;
        while (opcao != 5) {
            try {
                menuAgencia.exibirMenuAgencia();
                System.out.print("🎬 Escolha uma opção: ");
                opcao = leitura.nextInt();
                leitura.nextLine();

                switch (opcao) {
                    case 1:
                        cadastrarAgencia();
                        break;
                    case 2:
                        alterarAgencia();
                        break;
                    case 3:
                        buscarAgenciaPorNomeOuLogradouro();
                        break;
                    case 4:
                        removerAgenciaPorID();
                        break;
                    case 5:
                        System.out.println("🔙 Voltando ao menu principal...");
                        break;
                    default:
                        System.out.println("❌ Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("❌ Erro: Entrada inválida. Por favor, insira um número.");
                leitura.nextLine();
            }
        }
    }

    private void cadastrarAgencia() {
        System.out.println("\n==== Cadastro de Agência ====");
        try {
            System.out.print("Informe o nome da agência: ");
            String nome = leitura.nextLine();

            System.out.print("Informe o logradouro da agência: ");
            String logradouro = leitura.nextLine();
            System.out.print("Informe o número da agência: ");
            String numero = leitura.nextLine();
            System.out.print("Informe o bairro da agência: ");
            String bairro = leitura.nextLine();
            System.out.print("Informe a cidade da agência: ");
            String cidade = leitura.nextLine();
            System.out.print("Informe o estado da agência: ");
            String estado = leitura.nextLine();
            System.out.print("Informe o CEP da agência: ");
            String cep = leitura.nextLine();

            Endereco endereco = new Endereco(logradouro, numero, cep, bairro, cidade, estado);
            Agencia agencia = new Agencia(nome, endereco);

            agenciaServico.cadastrar(agencia);
            System.out.println("✅ Agência cadastrada com sucesso!");
        } catch (CEPInvalidoException e) {
            System.out.println("❌ Erro ao cadastrar agência: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Erro inesperado ao cadastrar agência: " + e.getMessage());
        }
    }

    private void alterarAgencia() {
        try {
            System.out.print("Informe o CEP da agência que deseja alterar: ");
            String cep = leitura.nextLine();

            Endereco enderecoBusca = new Endereco("", "", cep, "", "", "");
            Agencia agencia = agenciaServico.buscarPorEndereco(enderecoBusca);
            if (agencia == null) {
                System.out.println("❌ Agência com CEP " + cep + " não encontrada.");
                return;
            }

            System.out.println("Agência atual:");
            System.out.println(agencia);

            System.out.print("Informe o novo nome da agência: ");
            String novoNome = leitura.nextLine();

            System.out.print("Informe o novo logradouro da agência: ");
            String logradouro = leitura.nextLine();
            System.out.print("Informe o novo número da agência: ");
            String numero = leitura.nextLine();
            System.out.print("Informe o novo bairro da agência: ");
            String bairro = leitura.nextLine();
            System.out.print("Informe a nova cidade da agência: ");
            String cidade = leitura.nextLine();
            System.out.print("Informe o novo estado da agência: ");
            String estado = leitura.nextLine();
            System.out.print("Informe o novo CEP da agência: ");
            String novoCEP = leitura.nextLine();

            Endereco novoEndereco = new Endereco(logradouro, numero, novoCEP, bairro, cidade, estado);
            agencia.setNomeAgencia(novoNome);
            agencia.setEndereco(novoEndereco);

            agenciaServico.atualizar(agencia);
            System.out.println("✅ Agência alterada com sucesso!");
        } catch (CEPInvalidoException e) {
            System.out.println("❌ Erro ao alterar agência: CEP inválido.");
        } catch (Exception e) {
            System.out.println("❌ Erro ao alterar agência: " + e.getMessage());
        }
    }

    private void buscarAgenciaPorNomeOuLogradouro() {
        try {
            System.out.print("Informe parte do nome ou logradouro da agência que deseja buscar: ");
            String termoBusca = leitura.nextLine().toLowerCase();

            List<Agencia> agenciasEncontradas = agenciaServico.buscarTodos().stream()
                    .filter(agencia -> agencia.getNomeAgencia().toLowerCase().contains(termoBusca) ||
                            agencia.getEndereco().getLogradouro().toLowerCase().contains(termoBusca))
                    .toList();

            if (agenciasEncontradas.isEmpty()) {
                System.out.println("❌ Nenhuma agência encontrada com o critério de busca.");
            } else {
                System.out.println("✅ Agências encontradas:");
                agenciasEncontradas.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao buscar agências: " + e.getMessage());
        }
    }

    private void removerAgenciaPorID() {
        try {
            System.out.print("Informe o ID da agência que deseja remover: ");
            Long id = leitura.nextLong();
            leitura.nextLine();

            Agencia agencia = agenciaServico.buscarPorId(id);
            if (agencia == null) {
                System.out.println("❌ Agência com ID " + id + " não encontrada.");
                return;
            }

            System.out.println("Agência encontrada:");
            System.out.println(agencia);

            System.out.print("Tem certeza que deseja remover esta agência? (S/N): ");
            String confirmacao = leitura.nextLine();

            if (confirmacao.equalsIgnoreCase("S")) {
                agenciaServico.remover(agencia);
                System.out.println("✅ Agência removida com sucesso!");
            } else {
                System.out.println("❌ Operação de remoção cancelada.");
            }
        } catch (InputMismatchException e) {
            System.out.println("❌ Erro: Entrada inválida. Por favor, insira um número válido.");
            leitura.nextLine();
        } catch (Exception e) {
            System.out.println("❌ Erro ao remover agência: " + e.getMessage());
        }
    }
}
