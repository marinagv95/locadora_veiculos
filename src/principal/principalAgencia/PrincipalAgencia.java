package principal.principalAgencia;

import exception.enderecoException.CEPInvalidoException;
import modelo.agencia.Agencia;
import modelo.endereco.Endereco;
import servico.agenciaServico.AgenciaServico;
import util.leitura.Leitor;
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
        }
    }

    private void cadastrarAgencia() {
        Leitor.escrever("\n==== Cadastro de Agência ====");
        String nome = Leitor.ler(leitura, "Informe o nome da agência: ");
        String logradouro = Leitor.ler(leitura, "Informe o logradouro da agência: ");
        String numero = Leitor.ler(leitura, "Informe o número da agência: ");
        String bairro = Leitor.ler(leitura, "Informe o bairro da agência: ");
        String cidade = Leitor.ler(leitura, "Informe a cidade da agência: ");
        String estado = Leitor.ler(leitura, "Informe o estado da agência: ");
        String cep = Leitor.ler(leitura, "Informe o CEP da agência: ");
        try {
            Endereco endereco = new Endereco(logradouro, numero, cep, bairro, cidade, estado);
            Agencia agencia = new Agencia(nome, endereco);

            agenciaServico.cadastrar(agencia);
            System.out.println("✅ Agência cadastrada com sucesso!");
        } catch (CEPInvalidoException e) {
            System.out.println("❌ Erro ao cadastrar agência: CEP inválido.");
        } catch (Exception e) {
            System.out.println("❌ Erro ao cadastrar agência: " + e.getMessage());
        }
        finally {
            Leitor.aguardarContinuacao(leitura);
        }
    }

    private void alterarAgencia() {
        String cep = Leitor.ler(leitura, "Informe o CEP da agência que deseja alterar: ");

        try {
            Endereco enderecoBusca = new Endereco("", "", cep, "", "", "");
            Agencia agencia = agenciaServico.buscarPorEndereco(enderecoBusca);
            if (agencia == null) {
                Leitor.erro("❌ Agência com CEP " + cep + " não encontrada.");
                Leitor.aguardarContinuacao(leitura);
                return;
            }

            Leitor.escrever("Agência atual:");
            Leitor.escrever(agencia.toString());

            String novoNome = Leitor.ler(leitura, "Informe o novo nome da agência: ");
            String logradouro = Leitor.ler(leitura, "Informe o novo logradouro da agência: ");
            String numero = Leitor.ler(leitura, "Informe o novo número da agência: ");
            String bairro = Leitor.ler(leitura, "Informe o novo bairro da agência: ");
            String cidade = Leitor.ler(leitura, "Informe a nova cidade da agência: ");
            String estado = Leitor.ler(leitura, "Informe o novo estado da agência: ");
            String novoCEP = Leitor.ler(leitura, "Informe o novo CEP da agência: ");

            Endereco novoEndereco = new Endereco(logradouro, numero, novoCEP, bairro, cidade, estado);
            agencia.setNomeAgencia(novoNome);
            agencia.setEndereco(novoEndereco);

            agenciaServico.atualizar(agencia);
            Leitor.escrever("✅ Agência alterada com sucesso!");
        } catch (CEPInvalidoException e) {
            Leitor.erro("❌ Erro ao alterar agência: CEP inválido.");
        } catch (Exception e) {
            Leitor.erro("❌ Erro ao alterar agência: " + e.getMessage());
        } finally {
            Leitor.aguardarContinuacao(leitura);
        }
    }



    private void buscarAgenciaPorNomeOuLogradouro() {
        try {
        String termoBusca = Leitor.ler(leitura, "Informe parte do nome ou logradouro da agência que deseja buscar: ").toLowerCase();

        List<Agencia> agenciasEncontradas = agenciaServico.buscarTodos().stream()
                .filter(agencia -> agencia.getNomeAgencia().toLowerCase().contains(termoBusca) ||
                        agencia.getEndereco().getLogradouro().toLowerCase().contains(termoBusca))
                .toList();

        if (agenciasEncontradas.isEmpty()) {
            Leitor.erro("❌ Nenhuma agência encontrada com o critério de busca.");
        } else {
            Leitor.escrever("✅ Agências encontradas:");
            agenciasEncontradas.forEach(agencia -> Leitor.escrever(agencia.toString()));
        }
        } catch (Exception e) {
            System.out.println("❌ Erro ao buscar agências: " + e.getMessage());
        } finally {
            Leitor.aguardarContinuacao(leitura);
        }
    }


    private void removerAgenciaPorID() {
        Long id = Long.parseLong(Leitor.ler(leitura, "Informe o ID da agência que deseja remover: "));

        try {
            Agencia agencia = agenciaServico.buscarPorId(id);
            if (agencia == null) {
                Leitor.erro("❌ Agência com ID " + id + " não encontrada.");
                return;
            }

            Leitor.escrever("Agência encontrada:");
            Leitor.escrever(agencia.toString());

            String confirmacao = Leitor.ler(leitura, "Tem certeza que deseja remover esta agência? (S/N): ");

            if (confirmacao.equalsIgnoreCase("S")) {
                agenciaServico.remover(agencia);
                Leitor.escrever("✅ Agência removida com sucesso!");
            } else {
                Leitor.escrever("❌ Operação de remoção cancelada.");
            }
        } catch (InputMismatchException e) {
            System.out.println("❌ Erro: Entrada inválida. Por favor, insira um número válido.");
            leitura.nextLine();
        } catch (Exception e) {
            System.out.println("❌ Erro ao remover agência: " + e.getMessage());
        } finally {
            Leitor.aguardarContinuacao(leitura);
        }
    }
}
