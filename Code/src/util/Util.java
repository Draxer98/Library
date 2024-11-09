package util;

import java.util.ArrayList;
import java.util.Scanner;

public class Util {

    static Scanner scanner = new Scanner(System.in);

    private Util() {
    }

    public static void clrScr() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void wait(int millisecond) {
        try {
            Thread.sleep(millisecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int menu(String[] options, Scanner scanner) {
        int scelta;

        do {
            // Print title
            for (int i = 0; i < options.length; i++) {
                System.out.print("-");
            }
            System.out.println();

            System.out.println(options[0]);

            for (int i = 0; i < options.length; i++) {
                System.out.print("-");
            }
            System.out.println();

            // Print choice
            for (int i = 1; i < options.length; i++) {
                System.out.println("[" + i + "]" + " " + options[i]);
            }

            scelta = (Integer.parseInt(scanner.nextLine()));

            if ((scelta < 1) || (scelta > options.length - 1)) {
                System.out.println("Opzione Sbagliata");
            }
        }
        while ((scelta < 1) || (scelta > options.length - 1));

        return scelta;
    }

    public static int menu(ArrayList<String> options, Scanner scanner) {
        int scelta;

        do {
            // Print title
            for (int i = 0; i < options.getFirst().length(); i++) {
                System.out.print("-");
            }
            System.out.println();

            System.out.println(options.getFirst());

            for (int i = 0; i < options.getFirst().length(); i++) {
                System.out.print("-");
            }
            System.out.println();

            // Print choice
            for (int i = 1; i < options.size(); i++) {
                System.out.println("[" + i + "]" + " " + options.get(i));
            }

            try {
                scelta = (Integer.parseInt(scanner.nextLine()));
            } catch (Exception e) {
                scelta = -1;
            }

            if (scelta < 1 || scelta > options.size() - 1) {
                System.out.println("Opzione Sbagliata");
            }
        }
        while (scelta < 1 || scelta > options.size() - 1);

        return scelta;
    }
}
