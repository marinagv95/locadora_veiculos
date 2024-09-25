package servico.agenciaServico;

import exception.agenciaException.AgenciaDuplicadaException;
import modelo.agencia.Agencia;
import modelo.endereco.Endereco;
import repositorio.agenciaRepositorio.AgenciaRepositorio;

import java.util.List;

public class AgenciaServicoImplementacao <T extends Agencia> implements AgenciaServico<T> {

    private AgenciaRepositorio<T> agenciaRepositorio;

    public AgenciaServicoImplementacao(AgenciaRepositorio<T> agenciaRepositorio) {
        this.agenciaRepositorio = agenciaRepositorio;
    }

    @Override
    public T cadastrar(T agencia) throws AgenciaDuplicadaException {
        return agenciaRepositorio.cadastrar(agencia);
    }

    @Override
    public T atualizar(T agencia) {
        return null;
    }

    @Override
    public void remover(T agencia) {

    }

    @Override
    public T buscar(T agencia) {
        return null;
    }

    @Override
    public List<T> buscarTodos() {
        return agenciaRepositorio.listar();
    }

    @Override
    public T buscarPorEndereco(Endereco endereco) {
        return agenciaRepositorio.buscarPorEndereco(endereco);
    }
}
