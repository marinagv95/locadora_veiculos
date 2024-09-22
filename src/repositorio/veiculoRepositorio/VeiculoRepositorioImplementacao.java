package repositorio.veiculoRepositorio;

import modelo.veiculo.Veiculo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VeiculoRepositorioImplementacao<T extends Veiculo> extends VeiculoRepositorio<T> {

    public List<T> bancoDados;  //pode apontar para um arquivo

    public VeiculoRepositorioImplementacao() {
        this.bancoDados = new ArrayList<>();

    }

    @Override
    public T salvar(T veiculo) {
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
            //para alterar
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
}
