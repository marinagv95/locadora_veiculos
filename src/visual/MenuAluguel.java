package visual;

public class MenuAluguel {
    private static final String ANSI_RESET = "\u001B[0m"; // Reset
    private static final String ANSI_YELLOW = "\u001B[33m"; // Amarelo
    private static final String ANSI_GREEN = "\u001B[32m";  // Verde
    private static final String ANSI_CYAN = "\u001B[36m";   // Ciano
    private static final String ANSI_WHITE = "\u001B[37m";  // Branco

    public static void exibirMenuAluguel() {

        String menu =
                ANSI_YELLOW + "\n╔══════════════════════════════════╗" + ANSI_RESET + "\n" +
                        ANSI_YELLOW + "║" + ANSI_RESET + ANSI_CYAN + "        MENU DE ALUGUEL           " + ANSI_RESET + ANSI_YELLOW +    "║" + ANSI_RESET + "\n" +
                        ANSI_YELLOW + "╠══════════════════════════════════╣" + ANSI_RESET + "\n" +
                        ANSI_YELLOW + "║" + ANSI_RESET + ANSI_WHITE + "  1. ➕ Alugar Veículo            " + ANSI_RESET + ANSI_YELLOW        +"║" + ANSI_RESET + "\n" +
                        ANSI_YELLOW + "║" + ANSI_RESET + ANSI_WHITE + "  2. 🔍 Devolver Veículo          " + ANSI_RESET + ANSI_YELLOW + "║" + ANSI_RESET + "\n" +
                        ANSI_YELLOW + "║" + ANSI_RESET + ANSI_WHITE + "  3. ✏️ Alterar Aluguel           " + ANSI_RESET + ANSI_YELLOW + "║" + ANSI_RESET + "\n" +
                        ANSI_YELLOW + "║" + ANSI_RESET + ANSI_WHITE + "  4. 🗑️ Cancelar Aluguel          " + ANSI_RESET + ANSI_YELLOW + "║" + ANSI_RESET + "\n" +
                        ANSI_YELLOW + "╠══════════════════════════════════╣" + ANSI_RESET + "\n" +
                        ANSI_YELLOW + "║" + ANSI_RESET + ANSI_GREEN + "  5. 🔙 Menu Principal            " + ANSI_RESET + ANSI_YELLOW + "║" + ANSI_RESET + "\n" +
                        ANSI_YELLOW + "╚══════════════════════════════════╝" + ANSI_RESET + "\n";

        System.out.println(menu);
    }
}
