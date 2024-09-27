package visual;

public class MenuPrincipal {

    private static final String ANSI_RESET = "\u001B[0m"; // Reset
    private static final String ANSI_YELLOW = "\u001B[33m"; // Amarelo
    private static final String ANSI_CYAN = "\u001B[36m"; // Ciano
    private static final String ANSI_BLUE = "\u001B[34m"; // Azul
    private static final String ANSI_WHITE = "\u001B[37m"; // Branco

        public void menuPrincipal() {
            System.out.println(ANSI_YELLOW + "═══════════════════════════════════════════════" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "         🎬  M E N U  P R I N C I P A L  " + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "═══════════════════════════════════════════════" + ANSI_RESET);
            System.out.println(ANSI_WHITE + "             1️⃣  Gerenciar Veículos" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "═══════════════════════════════════════════════" + ANSI_RESET);
            System.out.println(ANSI_WHITE + "             2️⃣  Gerenciar Clientes" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "═══════════════════════════════════════════════" + ANSI_RESET);
            System.out.println(ANSI_WHITE + "             3️⃣  Gerenciar Agências" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "═══════════════════════════════════════════════" + ANSI_RESET);
            System.out.println(ANSI_WHITE + "             3️⃣  4Gerenciar Aluguel" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "═══════════════════════════════════════════════" + ANSI_RESET);
            System.out.println(ANSI_WHITE + "             0️⃣  Sair" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "═══════════════════════════════════════════════" + ANSI_RESET);
        }
}
