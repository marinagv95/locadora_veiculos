package util.leitura;

import java.util.Scanner;

public class Leitor {
    public static void escrever(String texto) {
        System.out.println(texto);
    }

    public static void erro(String texto) {
        System.err.println(texto);
    }

    public static String ler(Scanner entrada, String texto) {
        System.out.println(texto);
        return entrada.nextLine();
    }

    public static void aguardarContinuacao(Scanner entrada) {
        System.out.println("\nPressione Enter para continuar...");
        ler(entrada, "");
    }
}
