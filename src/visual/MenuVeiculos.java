package visual;

public class MenuVeiculos {
    private static final String ANSI_RESET = "\u001B[0m"; // Reset
    private static final String ANSI_YELLOW = "\u001B[33m"; // Amarelo
    private static final String ANSI_GREEN = "\u001B[32m";  // Verde
    private static final String ANSI_BLUE = "\u001B[34m";   // Azul
    private static final String ANSI_CYAN = "\u001B[36m";   // Ciano
    private static final String ANSI_WHITE = "\u001B[37m";  // Branco

    public static void exibirMenuVeiculos() {
        String menu =
                ANSI_YELLOW + "\n╔══════════════════════════════════╗" + ANSI_RESET + "\n" +
                        ANSI_YELLOW + "║" + ANSI_RESET + ANSI_CYAN + "        MENU DE VEÍCULOS          " + ANSI_RESET + ANSI_YELLOW + "║" + ANSI_RESET + "\n" +
                        ANSI_YELLOW + "╠══════════════════════════════════╣" + ANSI_RESET + "\n" +
                        ANSI_YELLOW + "║" + ANSI_RESET + ANSI_WHITE + "  1. ➕ Cadastrar Veículos        " + ANSI_RESET + ANSI_YELLOW +"║" + ANSI_RESET + "\n" +
                        ANSI_YELLOW + "║" + ANSI_RESET + ANSI_WHITE + "  2. ✏️ Alterar Veículos          " + ANSI_RESET + ANSI_YELLOW +"║" + ANSI_RESET + "\n" +
                        ANSI_YELLOW + "║" + ANSI_RESET + ANSI_WHITE + "  3. 🔍 Buscar Veículos           " + ANSI_RESET + ANSI_YELLOW +"║" + ANSI_RESET + "\n" +
                        ANSI_YELLOW + "║" + ANSI_RESET + ANSI_WHITE + "  4. 🗑️ Remover Veículos          " + ANSI_RESET + ANSI_YELLOW +"║" + ANSI_RESET + "\n" +
                        ANSI_YELLOW + "╠══════════════════════════════════╣" + ANSI_RESET + "\n" +
                        ANSI_YELLOW + "║" + ANSI_RESET + ANSI_GREEN + "  5. 🔙 Menu Principal            " + ANSI_RESET + ANSI_YELLOW +"║" + ANSI_RESET + "\n" +
                        ANSI_YELLOW + "╚══════════════════════════════════╝" + ANSI_RESET + "\n";

        System.out.println(menu);
    }
}
