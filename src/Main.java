import modelo.agencia.Agencia;
import modelo.aluguel.Aluguel;
import modelo.aluguel.DevolucaoAluguel;
import modelo.pessoa.Pessoa;
import modelo.veiculo.Veiculo;
import principal.principalAgencia.PrincipalAgencia;
import principal.principalAluguel.PrincipalAluguel;
import principal.principalPessoa.PrincipalPessoa;
import principal.principalVeiculo.PrincipalVeiculo;
import repositorio.agenciaRepositorio.AgenciaRepositorio;
import repositorio.agenciaRepositorio.AgenciaRepositorioImplementacao;
import repositorio.aluguelRepositorio.AluguelRepositorio;
import repositorio.aluguelRepositorio.AluguelRepositorioImplementacao;
import repositorio.pessoaRepositorio.PessoaRepositorio;
import repositorio.pessoaRepositorio.PessoaRepositorioImplementacao;
import repositorio.veiculoRepositorio.VeiculoRepositorio;
import repositorio.veiculoRepositorio.VeiculoRepositorioImplementacao;
import servico.agenciaServico.AgenciaServicoImplementacao;
import servico.aluguelServico.AluguelServicoImplementacao;
import servico.pessoaServico.PessoaServicoImplementacao;
import servico.veiculoServico.VeiculoServicoImplementacao;
import visual.MenuGeral;
import visual.MenuPrincipal;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MenuGeral menuGeral = new MenuGeral();
        menuGeral.iniciar();
    }
}
