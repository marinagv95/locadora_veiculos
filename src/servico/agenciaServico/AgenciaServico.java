package servico.agenciaServico;

import exception.agenciaException.AgenciaDuplicadaException;
import modelo.agencia.Agencia;
import modelo.endereco.Endereco;

import java.util.List;

public interface AgenciaServico<T extends Agencia> {
    public T cadastrar(T agencia) throws AgenciaDuplicadaException;
    public T atualizar(T agencia);
    public void remover(T agencia);
    public T buscar(T agencia);
    public List<T> buscarTodos();
    public T buscarPorEndereco(Endereco endereco);
}
