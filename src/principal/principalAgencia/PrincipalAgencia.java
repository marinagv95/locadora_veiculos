package principal.principalAgencia;

import exception.enderecoException.CEPInvalidoException;
import modelo.agencia.Agencia;
import modelo.endereco.Endereco;
import servico.agenciaServico.AgenciaServico;

import java.util.List;
import java.util.Scanner;

public class PrincipalAgencia {
    private AgenciaServico<Agencia> agenciaServico;
    private Scanner leitura;

    public PrincipalAgencia(AgenciaServico<Agencia> agenciaServico) {
        this.agenciaServico = agenciaServico;
        this.leitura = new Scanner(System.in);
    }

    public void exibirMenuAgencia() {
        int opcao = 0;
        while (opcao != 5) {
            System.out.println("\n╔═════════════════════════════🏢═══════ AGÊNCIAS ═══════🏢═════════════════════════════╗");
            System.out.println("║                                                                                     ║");
            System.out.println("║   1. ➕ Cadastrar Agência                                                            ║");
            System.out.println("║                                                                                     ║");
            System.out.println("║   2. ✏️  Alterar Agência                                                             ║");
            System.out.println("║                                                                                     ║");
            System.out.println("║   3. 🔍 Buscar Agência por nome ou logradouro                                                      ║");
            System.out.println("║                                                                                     ║");
            System.out.println("║   4. 🗑️  Remover Agência por CEP                                                     ║");
            System.out.println("║                                                                                     ║");
            System.out.println("║   5. 🔙 Voltar ao Menu Principal                                                    ║");
            System.out.println("║                                                                                     ║");
            System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════╝");
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
                    //removerAgenciaPorCEP();
                    break;
                case 5:
                    System.out.println("🔙 Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("❌ Opção inválida. Tente novamente.");
            }
        }
    }

    private void cadastrarAgencia() {
        System.out.println("\n==== Cadastro de Agência ====");
        System.out.print("Informe o nome da agência: ");
        String nome = leitura.nextLine();

        Long id;
        while (true) {
            try {
                System.out.print("Informe o ID da agência (número inteiro): ");
                id = leitura.nextLong();
                leitura.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("❌ ID inválido. Informe um número inteiro.");
                leitura.nextLine();
            }
        }

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

        try {
            Endereco endereco = new Endereco(logradouro, numero, cep, bairro, cidade, estado);
            Agencia agencia = new Agencia(id, nome, endereco);

            agenciaServico.cadastrar(agencia);
            System.out.println("✅ Agência cadastrada com sucesso!");
        } catch (CEPInvalidoException e) {
            System.out.println("❌ Erro ao cadastrar agência: CEP inválido.");
        } catch (Exception e) {
            System.out.println("❌ Erro ao cadastrar agência: " + e.getMessage());
        }
    }

    private void alterarAgencia() {
        System.out.print("Informe o CEP da agência que deseja alterar: ");
        String cep = leitura.nextLine();

        try {
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
            agenciasEncontradas.forEach(agencia -> {
                System.out.println(agencia);
            });
        }
    }





}
