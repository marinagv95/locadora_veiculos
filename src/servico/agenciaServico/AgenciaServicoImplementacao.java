package servico.agenciaServico;

import exception.agenciaException.AgenciaDuplicadaException;
import modelo.agencia.Agencia;
import modelo.endereco.Endereco;
import repositorio.agenciaRepositorio.AgenciaRepositorio;

import java.util.List;
import java.util.Optional;

public class AgenciaServicoImplementacao implements AgenciaServico<Agencia> {
    private AgenciaRepositorio<Agencia> agenciaRepositorio;

    public AgenciaServicoImplementacao(AgenciaRepositorio<Agencia> agenciaRepositorio) {
        this.agenciaRepositorio = agenciaRepositorio;
    }


    @Override
    public Agencia cadastrar(Agencia agencia) throws AgenciaDuplicadaException {
        if (agenciaRepositorio.listar().stream()
                .anyMatch(a -> a.getEndereco().equals(agencia.getEndereco()))) {
            throw new AgenciaDuplicadaException("Agência já cadastrada para este endereço.");
        }
        return agenciaRepositorio.cadastrar(agencia);
    }

    @Override
    public Agencia atualizar(Agencia agencia) {
        Optional<Agencia> agenciaExistente = agenciaRepositorio.listar().stream()
                .filter(a -> a.getIdAgencia().equals(agencia.getIdAgencia()))
                .findFirst();

        if (agenciaExistente.isPresent()) {
            return agenciaRepositorio.atualizar(agencia);
        } else {
            throw new IllegalArgumentException("Agência não encontrada para atualização.");
        }
    }

    @Override
    public void remover(Agencia agencia) {
        Optional<Agencia> agenciaExistente = agenciaRepositorio.listar().stream()
                .filter(a -> a.getIdAgencia().equals(agencia.getIdAgencia()))
                .findFirst();

        if (agenciaExistente.isPresent()) {
            agenciaRepositorio.remover(agencia);
        } else {
            throw new IllegalArgumentException("Agência não encontrada para remoção.");
        }
    }

    @Override
    public Agencia buscarPorId(Long id) {
        return agenciaRepositorio.listar().stream()
                .filter(a -> a.getIdAgencia().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Agência não encontrada."));
    }

    @Override
    public List<Agencia> buscarTodos() {
        return agenciaRepositorio.listar();
    }

    @Override
    public Agencia buscarPorEndereco(Endereco endereco) {
        return agenciaRepositorio.buscarPorEndereco(endereco);
    }
}
