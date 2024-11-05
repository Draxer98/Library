import util.Util;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Initialize initialize = new Initialize("src/data/files/Users.json", "");
        LoginManager loginManager = new LoginManager(initialize.getUsers(), initialize.getAdmins());

        String[] loginMenu = {
                "BENVENUTO",
                "Accedi",
                "Esci"
        };

        String[] userMainMenu = {
                "LIBRERIA",
                "Comprare un libro",
                "Prendere in prestito un libro",
                "Visualizza prestiti correnti",
                "Visualizza storico prestiti",
                "Visualizza storico vendite",
                "Estendere la data di consegna di un libro",
                "Info account",
                "Esci",
        };

        String[] buyBookMenu = {
                "COMPRA UN LIBRO",
                "Inseisci le informazioni del libro da comprare",
                "Visualizzare libri disponibili",
                "Esci",
        };

        String[] loanBookMenu = {
                "PRENDI IN PRESTITO UN LIBRO",
                "Inserire informazioni del libro da prendere in prestito",
                "Visualizzare libri disponibili",
                "Esci",
        };

        boolean exit = true;
        String loginId, loginPassword;

        /* LOGIN PROCESS */
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
        /* END LOGIN PROCESS */

        exit = true;


        if (loginManager.getLoggedInUser() instanceof User) {
            while (exit) {
                switch (Util.menu(userMainMenu, scanner)) {
                    /* Comprare un libro */
                    case 1: {

                        break;
                    }
                    case 2: {

                        break;
                    }
                    case 3: {

                        break;
                    }
                    case 4: {

                        break;
                    }
                    case 5: {

                        break;
                    }
                    case 6: {

                        break;
                    }
                    case 7: {

                        break;
                    }
                }
            }
        } else {
            /* ADMIN STUFF */
        }
    }
}