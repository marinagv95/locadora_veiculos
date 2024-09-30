package repositorio.agenciaRepositorio;

import exception.agenciaException.AgenciaDuplicadaException;
import modelo.agencia.Agencia;
import modelo.endereco.Endereco;

import java.util.ArrayList;
import java.util.List;

public class AgenciaRepositorioImplementacao<T extends Agencia> extends AgenciaRepositorio<T> {

    public List<T> bancoDados;

    public AgenciaRepositorioImplementacao() {
        bancoDados = new ArrayList<>();
    }


    @Override
    public T cadastrar(T agencia) throws AgenciaDuplicadaException {
        for (T a : bancoDados) {
            if (a.getCnpj().equals(agencia.getCnpj())) {
                throw new AgenciaDuplicadaException("Locadora com CNPJ " + agencia.getCnpj() + " já existe.");
            }
            if (a.getNomeAgencia().equalsIgnoreCase(agencia.getNomeAgencia())) {
                throw new AgenciaDuplicadaException("Locadora com nome " + agencia.getNomeAgencia() + " já existe.");
            }
            if (a.getEndereco().getCEP().equals(agencia.getEndereco().getCEP())) {
                throw new AgenciaDuplicadaException("Locadora com CEP " + agencia.getEndereco().getCEP() + " já existe.");
            }
        }
        bancoDados.add(agencia);
        return agencia;
    }

    @Override
    public T atualizar(T agencia) {
        for (int i = 0; i < bancoDados.size(); i++) {
            T agenciaExistente = bancoDados.get(i);
            if (agenciaExistente.getCnpj().equals(agencia.getCnpj())) {
                bancoDados.set(i, agencia);
                return agencia;
            }
        }
        throw new IllegalArgumentException("Locadora com CNPJ " + agencia.getCnpj() + " não encontrada para atualização.");
    }

    @Override
    public void remover(T agencia) {
        bancoDados.remove(agencia);
    }



    @Override
    public List<T> listar() {
        return new ArrayList<>(bancoDados);
    }

    @Override
    public T buscarPorCNPJ(String cnpj) {
        for(T agencia : bancoDados){
            if(agencia.getCnpj().equals(cnpj)){
                return agencia;
            }
        }
        return null;
    }

    @Override
    public T buscarPorEndereco(Endereco endereco) {
        for (T agencia : bancoDados) {
            if (agencia.getEndereco().getCEP().equals(endereco.getCEP())) {
                return agencia;
            }
        }
        return null;
    }

}