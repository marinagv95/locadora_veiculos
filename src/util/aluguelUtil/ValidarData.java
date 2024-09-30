package util.aluguelUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidarData {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate validarData(String dataString) throws DateTimeParseException {
        return LocalDate.parse(dataString, FORMATTER);
    }
    public static String formatarData(LocalDate data) {
        return data.format(FORMATTER);
    }
}
