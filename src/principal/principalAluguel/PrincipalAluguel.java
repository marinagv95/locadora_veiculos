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

    public PrincipalAluguel(AluguelServico aluguelServico,
                            PessoaServico pessoaServico,
                            AgenciaServico agenciaServico,
                            VeiculoServico veiculoServico) {
        this.aluguelServico = aluguelServico;
        this.pessoaServico = pessoaServico;
        this.agenciaServico = agenciaServico;
        this.veiculoServico = veiculoServico;
        this.devolucaoAluguel = new DevolucaoAluguel();
        this.menuAluguel = new MenuAluguel();
        this.leitura = new Scanner(System.in);
    }

    public void exibirMenuAluguel() {
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
                case 3:
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
                List<Veiculo> veiculosDisponiveis = listarVeiculosDisponiveisParaAlugar();
                if (!veiculosDisponiveis.isEmpty()) {
                    Veiculo veiculoSelecionado = selecionarVeiculoParaAlugar(veiculosDisponiveis);
                    Agencia agenciaSelecionada = escolherAgenciaParaRetirada();
                    informarDadosDeRetirada(pessoa, veiculoSelecionado, agenciaSelecionada);
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


    private List<Veiculo> listarVeiculosDisponiveisParaAlugar() {
        List<Veiculo> veiculosDisponiveis = veiculoServico.estaDisponivel();
        if (veiculosDisponiveis.isEmpty()) {
            Leitor.erro("❌ Não há veículos disponíveis para aluguel.");
            return null;
        }

        System.out.println("Veículos disponíveis para aluguel:");
        veiculosDisponiveis.forEach(veiculo ->
                System.out.println(veiculo.getModelo() + " - " + veiculo.getPlaca())
        );

        String placa = Leitor.ler(leitura, "Informe a placa do veículo que deseja selecionar: ").trim();
        System.out.println("Buscando veículo com placa: " + placa);

        Optional<Veiculo> veiculoSelecionado = veiculoServico.buscarVeiculoPorPlaca(placa);

        if (veiculoSelecionado.isEmpty()) {
            Leitor.erro("❌ Veículo com a placa " + placa + " não encontrado.");
            return null;
        }

        Veiculo veiculo = veiculoSelecionado.get();
        if (!veiculosDisponiveis.contains(veiculo)) {
            Leitor.erro("❌ O veículo " + veiculo.getModelo() + " - " + placa + " não está disponível para aluguel.");
            return null;
        }

        return List.of(veiculo);
    }

    private Veiculo selecionarVeiculoParaAlugar(List<Veiculo> veiculosDisponiveis) {
        System.out.println("Selecione um veículo para alugar:");
        for (int i = 0; i < veiculosDisponiveis.size(); i++) {
            Veiculo veiculo = veiculosDisponiveis.get(i);
            System.out.println((i + 1) +
                    ". Modelo: " + veiculo.getModelo() +
                    " | Marca: " + veiculo.getMarca() +
                    " | Valor Diária: R$ " + veiculo.getValorDiaria() +
                    " | Placa: " + veiculo.getPlaca());
        }

        int opcaoVeiculo = 0;
        while (opcaoVeiculo < 1 || opcaoVeiculo > veiculosDisponiveis.size()) {
            System.out.println("Confirma esse veículo: ");
            System.out.print("Se sim, confirme com a tecla (1): ");
            opcaoVeiculo = leitura.nextInt();
            leitura.nextLine();
            if (opcaoVeiculo < 1 || opcaoVeiculo > veiculosDisponiveis.size()) {
                Leitor.erro("❌ Opção inválida, por favor, selecione um número válido.");
            }
        }

        return veiculosDisponiveis.get(opcaoVeiculo - 1);
    }


    private Agencia escolherAgenciaParaRetirada() {
        List<Agencia> agencias = agenciaServico.buscarTodos();
        System.out.println("Locadoras disponíveis para retirada:");
        agencias.forEach(agencia -> System.out.println(agencia.getNomeAgencia()));

        String nomeAgencia = Leitor.ler(leitura, "Informe o nome da locadora para retirada: ").trim();
        Optional<Agencia> agenciaSelecionada = agencias.stream()
                .filter(a -> a.getNomeAgencia().equalsIgnoreCase(nomeAgencia))
                .findFirst();

        if (agenciaSelecionada.isPresent()) {
            return agenciaSelecionada.get();
        } else {
            Leitor.erro("❌ Locadora não encontrada!");
            return null;
        }
    }

    private void informarDadosDeRetirada(Pessoa pessoa, Veiculo veiculoSelecionado, Agencia agenciaSelecionada) {
        try {
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
        List<Agencia> agencias = agenciaServico.buscarTodos();
        System.out.println("Locadoras disponíveis para devolução:");
        agencias.forEach(agencia -> System.out.println(agencia.getNomeAgencia()));

        String nomeAgencia = Leitor.ler(leitura, "Informe o nome da locadora para devolução: ").trim();
        Optional<Agencia> agenciaSelecionada = agencias.stream()
                .filter(a -> a.getNomeAgencia().equalsIgnoreCase(nomeAgencia))
                .findFirst();

        if (agenciaSelecionada.isPresent()) {
            Agencia agencia = agenciaSelecionada.get();
            if (agencia.equals(agenciaRetirada)) {
                Leitor.erro("❌ A agência de devolução não pode ser a mesma que a de retirada!");
                return null;
            }
            return agencia;
        } else {
            Leitor.erro("❌ Locadora não encontrada!");
            return null;
        }
    }

// ====== Realizar revolucao ======
    private void realizarDevolucao() {
        try {
            Aluguel aluguel = buscarAluguelParaDevolucao();
            if (aluguel != null) {
                Agencia agenciaSelecionada = escolherAgenciaDevolucao(aluguel.getAgenciaRetirada());
                if (agenciaSelecionada != null) {
                    LocalDate dataDevolucao = LocalDate.now();

                    DevolucaoAluguel devolucao = new DevolucaoAluguel(aluguel, agenciaSelecionada, dataDevolucao);

                    BigDecimal multa = devolucao.calcularMulta();
                    String comprovante = devolucao.gerarComprovante();

                    Leitor.escrever(comprovante);
                    Leitor.escrever("✅ Devolução realizada com sucesso!");
                }
            }
        } catch (Exception e) {
            Leitor.erro("❌ Ocorreu um erro ao realizar a devolução: " + e.getMessage());
        }
    }

    private Aluguel buscarAluguelParaDevolucao() {
        String entrada = Leitor.ler(leitura, "Informe o protocolo do aluguel ou CPF do cliente para devolução: ").trim();
        Aluguel aluguel = null;

        try {
            aluguel = aluguelServico.buscarAluguelPorIdentificador(entrada);
            if (aluguel != null) {
                Leitor.escrever("Aluguel encontrado pelo protocolo: " + aluguel);
                return aluguel;
            }

            aluguel = aluguelServico.buscarAluguelPorIdentificador(entrada);
            if (aluguel != null) {
                Leitor.escrever("Aluguel encontrado pelo CPF: " + aluguel);
                return aluguel;
            }
            Leitor.erro("❌ Aluguel não encontrado com o protocolo ou CPF: " + entrada);
        } catch (Exception e) {
            Leitor.erro("❌ Erro ao buscar aluguel: " + e.getMessage());
        } finally {
            Leitor.aguardarContinuacao(leitura);
        }

        return null;
    }



    private void gerarComprovanteAluguel(Aluguel aluguel) {
        System.out.println(aluguel.toString());
    }

    private void gerarComprovanteDevolucao(Aluguel aluguel, Agencia agenciaSelecionada, LocalDate dataDevolucao) {
        if (devolucaoAluguel == null) {
            devolucaoAluguel = new DevolucaoAluguel(aluguel, agenciaSelecionada, dataDevolucao);
        }

        System.out.println("Gerando comprovante de devolução...");
        System.out.println(devolucaoAluguel.gerarComprovante());
    }


}