package repositorio.agenciaRepositorio;

import exception.agenciaException.AgenciaDuplicadaException;
import modelo.agencia.Agencia;
import modelo.endereco.Endereco;

import java.util.List;

public abstract class AgenciaRepositorio<T extends Agencia> {
    public abstract T cadastrar(T agencia) throws AgenciaDuplicadaException;
    public abstract T atualizar(T agencia);
    public abstract void remover(T agencia);
    public abstract List<T> listar();
    public abstract T buscarPorEndereco(Endereco endereco);
}
