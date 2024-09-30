package principal.principalAgencia;

import exception.enderecoException.CEPInvalidoException;
import modelo.agencia.Agencia;
import modelo.endereco.Endereco;
import servico.agenciaServico.AgenciaServico;
import util.leitura.Leitor;
import util.pessoaUtil.ValidarCNPJ;
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
        Leitor.escrever("\n==== Cadastro de Locadora ====");
        String cnpj = Leitor.ler(leitura, "Informe o CNPJ da locadora: ");
        String nome = Leitor.ler(leitura, "Informe o nome da locadora: ");
        String cep = Leitor.ler(leitura, "Informe o CEP da locadora: ");
        String logradouro = Leitor.ler(leitura, "Informe o endereço da locadora: ");
        String numero = Leitor.ler(leitura, "Informe o número da locadora: ");
        String bairro = Leitor.ler(leitura, "Informe o bairro da locadora: ");
        String cidade = Leitor.ler(leitura, "Informe a cidade da locadora: ");
        String estado = Leitor.ler(leitura, "Informe o estado da locadora: ");

        try {
            if (!ValidarCNPJ.validarCNPJ(cnpj)) {
                throw new IllegalArgumentException("CNPJ inválido.");
            }
            Endereco endereco = new Endereco(logradouro, numero, cep, bairro, cidade, estado);
            Agencia agencia = new Agencia(cnpj, nome, endereco);

            agenciaServico.cadastrar(agencia);
            System.out.println("✅ Locadora cadastrada com sucesso!");
        } catch (CEPInvalidoException e) {
            System.out.println("❌ Erro ao cadastrar locadora: CEP inválido.");
        } catch (Exception e) {
            System.out.println("❌ Erro ao cadastrar locadora: " + e.getMessage());
        }
        finally {
            Leitor.aguardarContinuacao(leitura);
        }
    }

    private void alterarAgencia() {
        String cnpj = Leitor.ler(leitura, "Informe o CNPJ da locadora que deseja alterar: ");

        try {
            Agencia agencia = agenciaServico.buscarPorCNPJ(cnpj);
            if (agencia == null) {
                Leitor.erro("❌ Locadora com CNPJ " + cnpj + " não encontrada.");
                Leitor.aguardarContinuacao(leitura);
                return;
            }

            Leitor.escrever("Locadora atual:");
            Leitor.escrever(agencia.toString());


            String novoNome = Leitor.ler(leitura, "Informe o novo nome da locadora: ");
            String novoCEP = Leitor.ler(leitura, "Informe o novo CEP da locadora: ");
            String logradouro = Leitor.ler(leitura, "Informe o novo endereço da locadora: ");
            String numero = Leitor.ler(leitura, "Informe o novo número da locadora: ");
            String bairro = Leitor.ler(leitura, "Informe o novo bairro da locadora: ");
            String cidade = Leitor.ler(leitura, "Informe a nova cidade da locadora: ");
            String estado = Leitor.ler(leitura, "Informe o novo estado da locadora: ");


            List<Agencia> agencias = agenciaServico.buscarTodos();
            for (Agencia ag : agencias) {
                if (!ag.getCnpj().equals(agencia.getCnpj())) {
                    if (ag.getNomeAgencia().equalsIgnoreCase(novoNome)) {
                        Leitor.erro("❌ Erro: Outra locadora com o nome '" + novoNome + "' já existe.");
                        return;
                    }
                    if (ag.getEndereco().getCEP().equals(novoCEP)) {
                        Leitor.erro("❌ Erro: Outra locadora com o CEP '" + novoCEP + "' já existe.");
                        return;
                    }
                }
            }

            Endereco novoEndereco = new Endereco(logradouro, numero, novoCEP, bairro, cidade, estado);
            agencia.setNomeAgencia(novoNome);
            agencia.setEndereco(novoEndereco);

            agenciaServico.atualizar(agencia);
            Leitor.escrever("✅ Locadora alterada com sucesso!");
        } catch (CEPInvalidoException e) {
            Leitor.erro("❌ Erro ao alterar locadora: CEP inválido.");
        } catch (Exception e) {
            Leitor.erro("❌ Erro ao alterar locadora: " + e.getMessage());
        } finally {
            Leitor.aguardarContinuacao(leitura);
        }
    }



    private void buscarAgenciaPorNomeOuLogradouro() {
        try {
        String termoBusca = Leitor.ler(leitura, "Informe parte do nome ou endereço da locadora que deseja buscar: ").toLowerCase();

        List<Agencia> agenciasEncontradas = agenciaServico.buscarTodos().stream()
                .filter(agencia -> agencia.getNomeAgencia().toLowerCase().contains(termoBusca) ||
                        agencia.getEndereco().getLogradouro().toLowerCase().contains(termoBusca))
                .toList();

        if (agenciasEncontradas.isEmpty()) {
            Leitor.erro("❌ Nenhuma locadora encontrada com o critério de busca.");
        } else {
            Leitor.escrever("✅ Locadora encontradas:");
            agenciasEncontradas.forEach(agencia -> Leitor.escrever(agencia.toString()));
        }
        } catch (Exception e) {
            System.out.println("❌ Erro ao buscar locadoras: " + e.getMessage());
        } finally {
            Leitor.aguardarContinuacao(leitura);
        }
    }


    private void removerAgenciaPorID() {
        String cnpj = Leitor.ler(leitura, "Informe o CNPJ da locadora que deseja remover: ");

        try {
            Agencia agencia = agenciaServico.buscarPorCNPJ(cnpj);
            if (agencia == null) {
                Leitor.erro("❌ Locadora com CNPJ " + cnpj + " não encontrada.");
                return;
            }

            Leitor.escrever("Locadora encontrada:");
            Leitor.escrever(agencia.toString());

            String confirmacao = Leitor.ler(leitura, "Tem certeza que deseja remover esta locadora? (Sim/Nao): ");

            if (confirmacao.equalsIgnoreCase("Sim")) {
                agenciaServico.remover(agencia);
                Leitor.escrever("✅ Locadora removida com sucesso!");
            } else {
                Leitor.escrever("❌ Operação de remoção cancelada.");
            }
        } catch (InputMismatchException e) {
            System.out.println("❌ Erro: Entrada inválida. Por favor, insira um número válido.");
            leitura.nextLine();
        } catch (Exception e) {
            System.out.println("❌ Erro ao remover locadora: " + e.getMessage());
        } finally {
            Leitor.aguardarContinuacao(leitura);
        }
    }
}
