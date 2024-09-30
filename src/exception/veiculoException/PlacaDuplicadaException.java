package exception.veiculoException;

public class PlacaDuplicadaException extends Exception {
    public PlacaDuplicadaException() {
        super("Já existe um veículo cadastrado com a placa");
    }

    public PlacaDuplicadaException(String message) {
        super(message);
    }


}
