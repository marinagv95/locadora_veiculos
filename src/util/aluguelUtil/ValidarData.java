package util.aluguelUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidarData {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Método para validar e converter a string em LocalDate
    public static LocalDate validarData(String dataString) throws DateTimeParseException {
        return LocalDate.parse(dataString, FORMATTER);
    }

    // Método para formatar LocalDate no formato brasileiro
    public static String formatarData(LocalDate data) {
        return data.format(FORMATTER);
    }
}
