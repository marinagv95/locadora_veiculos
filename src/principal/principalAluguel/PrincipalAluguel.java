package principal.principalAluguel;

import modelo.agencia.Agencia;
import modelo.aluguel.Aluguel;
import modelo.aluguel.DevolucaoAluguel;
import modelo.pessoa.Pessoa;
import modelo.veiculo.Veiculo;
import principal.principalPessoa.PrincipalPessoa;
import servico.agenciaServico.AgenciaServico;
import servico.aluguelServico.AluguelServico;
import servico.pessoaServico.PessoaServico;
import servico.veiculoServico.VeiculoServico;
import util.aluguelUtil.ValidarData;
import util.leitura.Leitor;
import visual.MenuAluguel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class PrincipalAluguel {
    private final MenuAluguel menuAluguel;
    private final AluguelServico<Aluguel> aluguelServico;
    private final PessoaServico<Pessoa> pessoaServico;
    private final AgenciaServico<Agencia> agenciaServico;
    private final VeiculoServico<Veiculo> veiculoServico;
    private DevolucaoAluguel devolucaoAluguel;
    private final Scanner leitura;

    public PrincipalAluguel(AluguelServico aluguelServico, PessoaServico pessoaServico,
                            AgenciaServico agenciaServico, VeiculoServico veiculoServico) {
        this.aluguelServico = aluguelServico;
        this.pessoaServico = pessoaServico;
        this.agenciaServico = agenciaServico;
        this.veiculoServico = veiculoServico;
        this.devolucaoAluguel = new DevolucaoAluguel();
        this.menuAluguel = new MenuAluguel();
        this.leitura = new Scanner(System.in);
    }

    public void exibirMenuAluguel() throws Exception {
        int opcao = 0;
        while (opcao != 5) {
            menuAluguel.exibirMenuAluguel();
            System.out.print("🎬 Escolha uma opção: ");
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    efetuarAluguel();
                    break;
                case 2:
                    realizarDevolucao();
                    break;
                case 5:
                    System.out.println("🔙 Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("❌ Opção inválida, tente novamente.");
            }
        }
    }

    private void efetuarAluguel() {
        try {
            Pessoa pessoa = buscarClienteParaAlugar();
            if (pessoa != null) {
                Veiculo veiculoSelecionado = selecionarVeiculoParaAlugar();
                if (veiculoSelecionado != null) {
                    Agencia agenciaSelecionada = escolherAgenciaParaRetirada();
                    if (agenciaSelecionada != null) {
                        Agencia agenciaDevolucao = escolherAgenciaDevolucao(agenciaSelecionada);
                        if (agenciaDevolucao != null) {
                            informarDadosDeRetirada(pessoa, veiculoSelecionado, agenciaSelecionada);
                            veiculoServico.marcarComoIndisponivel(veiculoSelecionado.getPlaca());
                        } else {
                            Leitor.erro("❌ Locadora de devolução não encontrada ou é a mesma da retirada!");
                        }
                    } else {
                        Leitor.erro("❌ Locadora de retirada não encontrada!");
                    }
                }
            }
        } catch (Exception e) {
            Leitor.erro("❌ Ocorreu um erro ao efetuar o aluguel: " + e.getMessage());
        }
    }


    private Pessoa buscarClienteParaAlugar() {
        String identificador = Leitor.ler(leitura, "Informe o CPF ou CNPJ do cliente que deseja buscar: ").trim();
        System.out.println("Buscando cliente com identificador: " + identificador);
        try {
            Pessoa pessoa = pessoaServico.buscarPorIdenficador(identificador);
            if (pessoa != null) {
                Leitor.escrever("Cliente identificado: \n" + pessoa);
                return pessoa;
            } else {
                Leitor.erro("❌ Cliente não encontrada com o identificador: " + identificador);
            }
        } catch (Exception e) {
            Leitor.erro("❌ Erro ao buscar pessoa: " + e.getMessage());
        } finally {
            Leitor.aguardarContinuacao(leitura);
        }
        return null;
    }


    private Veiculo selecionarVeiculoParaAlugar() {
        List<Veiculo> veiculosDisponiveis = veiculoServico.estaDisponivel();
        if (veiculosDisponiveis.isEmpty()) {
            Leitor.erro("❌ Não há veículos disponíveis para aluguel.");
            return null;
        }

        System.out.printf("%-5s %-20s %-20s %-15s %-10s %-10s%n", "Opção", "Modelo", "Marca", "Valor Diária", "Placa", "Disponível");
        System.out.println("---------------------------------------------------------------------");

        for (int i = 0; i < veiculosDisponiveis.size(); i++) {
            Veiculo veiculo = veiculosDisponiveis.get(i);
            String disponibilidade = veiculo.estaDisponivel() ? "Sim" : "Não";
            System.out.printf("%-5d %-20s %-20s R$ %-12.2f %-10s %-10s%n",
                    (i + 1),
                    veiculo.getModelo(),
                    veiculo.getMarca(),
                    veiculo.getValorDiaria(),
                    veiculo.getPlaca(),
                    disponibilidade);
        }

        int opcaoVeiculo = 0;
        while (opcaoVeiculo < 1 || opcaoVeiculo > veiculosDisponiveis.size()) {
            System.out.print("Selecione o número do veículo que deseja alugar: ");
            opcaoVeiculo = leitura.nextInt();
            leitura.nextLine();

            if (opcaoVeiculo < 1 || opcaoVeiculo > veiculosDisponiveis.size()) {
                Leitor.erro("❌ Opção inválida, por favor, selecione um número válido.");
            }
        }

        Veiculo veiculoSelecionado = veiculosDisponiveis.get(opcaoVeiculo - 1);
        System.out.printf("Você selecionou: %s - %s | Placa: %s. Deseja confirmar? (1 - Sim, 0 - Não): ",
                veiculoSelecionado.getModelo(),
                veiculoSelecionado.getMarca(),
                veiculoSelecionado.getPlaca());

        int confirmacao = leitura.nextInt();
        if (confirmacao != 1) {
            Leitor.erro("❌ Aluguel cancelado.");
            return null;
        }

        return veiculoSelecionado;
    }


    private Agencia escolherAgenciaParaRetirada() {
        List<Agencia> agencias = agenciaServico.buscarTodos();
        if (agencias.isEmpty()) {
            Leitor.erro("❌ Não há locadoras disponíveis para retirada.");
            return null;
        }

        System.out.println();
        System.out.printf("%-5s %-30s%n", "Opção", "Nome da Locadora");
        System.out.println("-------------------------------------------");
        for (int i = 0; i < agencias.size(); i++) {
            System.out.printf("%-5d %-30s%n", (i + 1), agencias.get(i).getNomeAgencia());
        }
        System.out.println();

        int opcaoAgencia = 0;
        while (opcaoAgencia < 1 || opcaoAgencia > agencias.size()) {
            System.out.println();
            System.out.println("A locadora de retirada é obrigada ser diferente da devolução");
            System.out.print("Selecione o número da locadora para retirada: ");
            opcaoAgencia = leitura.nextInt();
            leitura.nextLine();

            if (opcaoAgencia < 1 || opcaoAgencia > agencias.size()) {
                Leitor.erro("❌ Opção inválida, por favor, selecione um número válido.");
            }
        }
        return agencias.get(opcaoAgencia - 1);
    }


    private void informarDadosDeRetirada(Pessoa pessoa, Veiculo veiculoSelecionado, Agencia agenciaSelecionada) {
        try {
            System.out.println("🔔 Vamos registrar os dados de retirada do veículo.");
            String entradaData = Leitor.ler(leitura, "Informe a data de retirada (DD/MM/YYYY): ").trim();
            LocalDate dataInicio = ValidarData.validarData(entradaData);
            System.out.println("Data para retirada do Veículo: " + ValidarData.formatarData(dataInicio));

            LocalTime horaInicio = LocalTime.parse(Leitor.ler(leitura, "Informe a hora de retirada (HH:mm): ").trim());
            System.out.println("Hora para retirada do Veículo: " + horaInicio);

            int diasAluguel = Integer.parseInt(Leitor.ler(leitura, "Informe a quantidade de dias para o aluguel: ").trim());
            if (diasAluguel <= 0) {
                Leitor.erro("❌ A quantidade de dias deve ser um número positivo.");
                return;
            }

            if (diasAluguel < 1) {
                Leitor.erro("❌ A quantidade de dias deve ser maior que 0 para permitir devolução.");
                return;
            }

            LocalDate dataDevolucao = dataInicio.plusDays(diasAluguel);
            System.out.println("Data de devolução: " + dataDevolucao);

            if (dataDevolucao.isEqual(dataInicio)) {
                Leitor.erro("❌ A devolução não pode ser no mesmo dia da retirada.");
                return;
            }

            Aluguel aluguel = new Aluguel(veiculoSelecionado, pessoa, agenciaSelecionada, dataInicio, horaInicio);
            aluguel.setDiasAlugados(diasAluguel);
            aluguelServico.cadastrarAluguel(aluguel);

            gerarComprovanteAluguel(aluguel);
            Leitor.escrever("✅ Aluguel cadastrado com sucesso!");

        } catch (DateTimeParseException e) {
            Leitor.erro("❌ Entrada inválida para data ou hora. Por favor, insira no formato correto.");
        } catch (NumberFormatException e) {
            Leitor.erro("❌ Entrada inválida. Por favor, insira um número inteiro.");
        } catch (Exception e) {
            Leitor.erro("❌ Ocorreu um erro ao cadastrar o aluguel: " + e.getMessage());
        } finally {
            Leitor.aguardarContinuacao(leitura);
        }
    }

    private Agencia escolherAgenciaDevolucao(Agencia agenciaRetirada) {
        Agencia agenciaSelecionada = escolherAgenciaParaRetirada();
        if (agenciaSelecionada != null && agenciaSelecionada.equals(agenciaRetirada)) {
            Leitor.erro("❌ A locadora de devolução não pode ser a mesma da retirada!");
            return escolherAgenciaDevolucao(agenciaRetirada);
        }
        return agenciaSelecionada;
    }

    private void gerarComprovanteAluguel(Aluguel aluguel) {
        System.out.println(aluguel.toString());
    }


    private void realizarDevolucao() throws Exception {
        try {
            String cpfCnpj = Leitor.ler(leitura, "Informe o CPF ou CNPJ do cliente: ").trim();
            Pessoa cliente = pessoaServico.buscarPorIdenficador(cpfCnpj);

            if (cliente != null) {
                List<Aluguel> alugueis = aluguelServico.buscarAlugueisPorPessoa(cliente);
                if (alugueis != null && !alugueis.isEmpty()) {

                    String entradaDataEntrega = Leitor.ler(leitura, "Informe a data da devolução do veículo (DD/MM/YYYY): ").trim();
                    LocalDate dataEntrega = ValidarData.validarData(entradaDataEntrega);

                    Aluguel aluguel = alugueis.get(0);

                    Agencia agenciaDevolucao = escolherAgenciaDevolucao(aluguel.getAgenciaRetirada());
                    if (agenciaDevolucao == null) {
                        Leitor.erro("❌ Agência de devolução não encontrada.");
                        return;
                    }

                    DevolucaoAluguel devolucao = new DevolucaoAluguel(aluguel, agenciaDevolucao, dataEntrega);
                    BigDecimal multa = devolucao.calcularMulta();
                    BigDecimal valorAluguel = aluguel.calcularTotalAluguel();

                    gerarComprovanteDevolucao(devolucao);

                    if (multa.compareTo(BigDecimal.ZERO) > 0) {
                        Leitor.escrever("🔴 A devolução está fora do prazo. Multa: R$ " + multa);
                    } else {
                        Leitor.escrever("✅ A devolução foi realizada dentro do prazo!");
                    }

                } else {
                    Leitor.erro("❌ Não há aluguel encontrado para o CPF ou CNPJ informado.");
                }
            } else {
                Leitor.erro("❌ Cliente não encontrado com o identificador: " + cpfCnpj);
            }
        } catch (DateTimeParseException e) {
            Leitor.erro("❌ Data inválida. Por favor, insira a data no formato correto (DD/MM/YYYY).");
        } catch (Exception e) {
            e.printStackTrace();
            Leitor.erro("❌ Ocorreu um erro ao realizar a devolução: " + e.getMessage());
        }
    }


    private void gerarComprovanteDevolucao(DevolucaoAluguel devolucao) {
        if (devolucao == null) {
            System.out.println("Erro: A devolução deve ser válida.");
            return;
        }

        try {
            System.out.println("Gerando comprovante de devolução...");
            String comprovante = devolucao.gerarComprovante();
            System.out.println(comprovante);
        } catch (Exception e) {
            System.out.println("Erro ao gerar comprovante: " + e.getMessage());
        }
    }


}