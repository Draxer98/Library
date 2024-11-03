import util.Util;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Initialize initialize = new Initialize("src/data/files/Users.json");
        LoginManager loginManager = new LoginManager(initialize.getUsers());

        String[] loginMenu = {
                "BENVENUTO",
                "Accedi",
                "Esci"
        };

        boolean exit = true;
        String loginId, loginPassword;

        while (exit) {
            switch (Util.menu(loginMenu, scanner)) {
                case 1: {
                    // Insert id
                    System.out.print("Inserisci il tuo id (se vuoi uscire inserisici q): ");
                    loginId = scanner.nextLine();

                    // Insert password
                    System.out.print("Inserisci il tuo password (se vuoi uscire inserisici q): ");
                    loginPassword = scanner.nextLine();

                    if (loginManager.login(loginId, loginPassword)) {
                        System.out.println("Login effettuato con successo");
                        exit = false;
                    } else {
                        System.out.println("Login fallito");
                    }

                    break;
                }
                default: {
                    exit = false;
                    break;
                }
            }
        }
    }
}