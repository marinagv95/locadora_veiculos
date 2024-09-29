package visual;

public class MenuPrincipal {

    private static final String ANSI_RESET = "\u001B[0m"; // Reset
    private static final String ANSI_YELLOW = "\u001B[33m"; // Amarelo
    private static final String ANSI_CYAN = "\u001B[36m"; // Ciano
    private static final String ANSI_WHITE = "\u001B[37m"; // Branco

    public void menuPrincipal() {
        System.out.println(ANSI_CYAN + "╭───────────────────────────────────────────────╮" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "          🚗  M E N U  L O C A D O R A  " + ANSI_RESET);
        System.out.println(ANSI_CYAN + "╰───────────────────────────────────────────────╯" + ANSI_RESET);
        System.out.printf(ANSI_WHITE + "             1️⃣  Gerenciar Veículos                   \n" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "├───────────────────────────────────────────────┤" + ANSI_RESET);
        System.out.printf(ANSI_WHITE + "             2️⃣  Gerenciar Clientes                   \n" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "├───────────────────────────────────────────────┤" + ANSI_RESET);
        System.out.printf(ANSI_WHITE + "             3️⃣  Gerenciar Agências                   \n" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "├───────────────────────────────────────────────┤" + ANSI_RESET);
        System.out.printf(ANSI_YELLOW + "             0️⃣  Menu Principal                                 \n" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "╰───────────────────────────────────────────────╯" + ANSI_RESET);
        System.out.println();
    }


    public void menuPrincipalGeral() {
        System.out.println(ANSI_CYAN + "╭───────────────────────────────────────────────╮" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "           🔑  L O C A D O R A  🔑 " + ANSI_RESET);
        System.out.println(ANSI_CYAN + "╰───────────────────────────────────────────────╯" + ANSI_RESET);
        System.out.printf(ANSI_WHITE + "             1️⃣  Locadora                   \n" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "├───────────────────────────────────────────────┤" + ANSI_RESET);
        System.out.printf(ANSI_WHITE + "             2️⃣  Realizar Aluguel                   \n" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "├───────────────────────────────────────────────┤" + ANSI_RESET);
        System.out.printf(ANSI_YELLOW + "             0️⃣  Sair                                 \n" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "╰───────────────────────────────────────────────╯" + ANSI_RESET);
        System.out.println();
    }


}
