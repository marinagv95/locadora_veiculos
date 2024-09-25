package repositorio.veiculoRepositorio;

import exception.veiculoException.PlacaDuplicadaException;
import exception.veiculoException.VeiculoNaoExistenteException;
import modelo.veiculo.Veiculo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VeiculoRepositorioImplementacao<T extends Veiculo> extends VeiculoRepositorio<T> {

    public List<T> bancoDados;

    public VeiculoRepositorioImplementacao() {
        this.bancoDados = new ArrayList<>();
    }

    @Override
    public T salvar(T veiculo) throws PlacaDuplicadaException {
        if (this.buscarPorPlaca(veiculo.getPlaca()).isPresent()) {
            throw new PlacaDuplicadaException(veiculo.getPlaca());
        }
        this.bancoDados.add(veiculo);
        return veiculo;
    }

    @Override
    public List<T> listarVeiculos() {
        return this.bancoDados;
    }

    @Override
    public T alterarVeiculo(T veiculo) {
        Optional<T> optionalVeiculo = this.buscarPorPlaca(veiculo.getPlaca());
        if(optionalVeiculo.isPresent()) {
            T veiculoBD = optionalVeiculo.get();
            // Atualizando os dados do veículo
            int index = this.bancoDados.indexOf(veiculoBD);
            veiculoBD.setDisponivel(veiculo.getDisponivel());
            veiculoBD.setMarca(veiculo.getMarca());
            veiculoBD.setModelo(veiculo.getModelo());

            this.bancoDados.set(index, veiculoBD);
            return veiculoBD;
        }
        return null;
    }

    @Override
    public Optional<T> buscarPorPlaca(String placa) {
        return this.bancoDados .stream()
                .filter(veiculo -> placa.equalsIgnoreCase(veiculo.getPlaca()))
                .findFirst();
    }

    @Override
    public T removerVeiculo(String placa) throws VeiculoNaoExistenteException {
        Optional<T> optionalVeiculo = this.buscarPorPlaca(placa);
        if (optionalVeiculo.isPresent()) {
            T veiculoRemovido = optionalVeiculo.get();
            this.bancoDados.remove(veiculoRemovido);
            return veiculoRemovido;
        }
        throw new VeiculoNaoExistenteException("Veículo com placa " + placa + " não encontrado.");
    }

    @Override
    public boolean estaDisponivel(String placa) {
        for (T veiculo : bancoDados) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa)) {
                return veiculo.getDisponivel();
            }
        }
        return false;
    }
}
