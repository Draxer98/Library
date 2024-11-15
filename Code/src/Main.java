import books.Book;
import books.BookCopy;
import events.Loan;
import events.Sell;
import excepetions.DuplicatePhoneNumberException;
import excepetions.IllegalLengthForNumberException;
import handler.RegistrationHandler;
import libraryMembers.Admin;
import libraryMembers.LibraryMember;
import libraryMembers.User;
import util.Initialize;
import util.Library;
import util.LoginManager;
import util.Util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Initialize initialize = new Initialize(
                "src/data/files/Users.json",
                "src/data/files/Admin.json",
                "src/data/files/BooksForLoan.json",
                "src/data/files/idNumber.json"
        );
        LoginManager loginManager = new LoginManager(initialize.getUsers(), initialize.getAdmins());
        Library library = new Library(initialize.getBookForLoan(), initialize.getBookForSell());
        RegistrationHandler registrationHandler = new RegistrationHandler(initialize.getUsers(), initialize.getAdmins());

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
                "Visualizza storico vendite",
                "Info account",
                "Esci",
        };

        String[] adminMainMenu = {
                "LIBRERIA",
                "Registrazione utenti",
                "Registrazione admin",
                "Visualizza tutti utenti",
                "Cerca ID",
                "Visualizzazione dei prestiti",
                "Inserimento libro",
                "Cancella libro",
                "Esistenza libro",
                "Esci"
        };

        boolean exit = true;


        /* LOGIN PROCESS */
        while (exit) {
            switch (Util.menu(loginMenu, scanner)) {
                case 1: {
                    String loginId, loginPassword;

                    // Insert id
                    System.out.print("Inserisci il tuo id (se vuoi uscire inserisici q): ");
                    loginId = scanner.nextLine();

                    if (loginId.equals("q")) {
                        break;
                    }

                    // Insert password
                    System.out.print("Inserisci il tuo password (se vuoi uscire inserisici q): ");
                    loginPassword = scanner.nextLine();

                    if (loginPassword.equals("q")) {
                        break;
                    }

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


        if (loginManager.getLoggedInUser() instanceof User user) {
            while (exit) {
                switch (Util.menu(userMainMenu, scanner)) {
                    /* Comprare un libro */
                    case 1 -> {
                        if (library.getBooksForSell(false).isEmpty()) {
                            System.out.println("Non ci sono libri. Disponibili per la vendita'");
                            break;
                        }

                        // Transform the ArrayList<BookCopy> in a ArrayList<String>
                        // to be printable in Util.menu
                        ArrayList<String> bookString = Util.bookForSellToMenu(library.getBooksForSell(true), library);

                        // if the size of the final array is
                        // it means that there is only the title
                        // so there aren't books
                        if (bookString.size() == 1) {
                            System.out.println("Non ci sono libri. Disponibili per il perstito");
                            break;
                        }

                        // Ask the user choose between all the books that are avaiable for selling
                        int index = Util.menu(bookString, scanner) - 1;
                        BookCopy chosenBook = library.getBooksForSell(true).get(index);

                        // Create new sell obj
                        Sell newSell = new Sell(
                                chosenBook.getIsbn(),
                                library.findParentBookByIsbn(chosenBook.getParentIsbn()).getPrice(),
                                LocalDate.now()
                        );

                        // Add the new sell in the ArrayList of the user
                        user.addSell(newSell);

                        // Delete the sell book
                        library.getBooksForSell(false).remove(chosenBook);
                    }
                    /* Prendere in prestito un libro */
                    case 2 -> {
                        if (library.getBooksForLoan().isEmpty()) {
                            System.out.println("Non ci sono libri. Disponibili per il perstito");
                            break;
                        }

                        ArrayList<String> bookString = Util.bookForLoanToMenu(library.getBooksForLoan());

                        // if the size of the final array is
                        // it means that there is only the title
                        // so there aren't books
                        if (bookString.size() == 1) {
                            System.out.println("Non ci sono libri. Disponibili per il perstito");
                            break;
                        }

                        int index = Util.menu(bookString, scanner) - 1;
                        Book chosenBook = library.getBooksForLoan().get(index);

                        Loan newLoan = new Loan(
                                chosenBook.getIsbn(),
                                LocalDate.now(),
                                LocalDate.now().plusDays(30)
                        );

                        System.out.println("Dovrai restituire il libro in data " + LocalDate.now().plusDays(30).toString());

                        user.addLoan(newLoan);

                        library.getBooksForLoan().remove(chosenBook);
                    }
                    /* Visualizza prestiti correnti */
                    case 3 -> {
                        user.getLoans().forEach(System.out::println);
                    }
                    /* Visualizza storico vendite */
                    case 4 -> {
                        System.out.println("===============");
                        System.out.println("STORICO VENDITE");
                        System.out.println("===============");

                        user.getSells().forEach(System.out::println);
                    }
                    /* Info account */
                    case 5 -> {
                        System.out.println("============");
                        System.out.println("INFO ACCOUNT");
                        System.out.println("============");

                        System.out.println(user.toString());
                    }
                    default -> {
                        exit = false;
                    }
                }
            }
        } else {
            while (exit) {
                switch (Util.menu(adminMainMenu, scanner)) {

                    /* Registrazione utenti */
                    case 1 -> {
                        // Get the name, surname and phone number
                        LibraryMember libraryMember = registrationHandler.takeBaseInfoOfUser(scanner)

                        // Generate id and password
                        String id = "U" + initialize.getIdNumber();
                        String password = registrationHandler.generateRandomPassword();

                        // The user into the list of users
                        initialize.addUser(new User(id, password, libraryMember.getName(), libraryMember.getSurname(), libraryMember.getPhoneNumber()));

                        System.out.println("Utente creato con ID = " + id + ", PASSWORD = " + password);
                    }
                    /* Registrazione admin */
                    case 2 -> {
                        // Get the name, surname and phone number
                        LibraryMember libraryMember = registrationHandler.takeBaseInfoOfUser(scanner);

                        // Generate id and password
                        String id = "A" + initialize.getIdNumber();
                        String password = registrationHandler.generateRandomPassword();

                        // The admin into the list of admins
                        initialize.addAdmin(new Admin(id, password, libraryMember.getName(), libraryMember.getSurname(), libraryMember.getPhoneNumber()));

                        System.out.println("Utente creato con ID = " + id + ", PASSWORD = " + password);
                    }
                    /* Visualizza tutti utenti */
                    case 3 -> {
                        initialize.getUsers().forEach(System.out::println);

                        initialize.getAdmins().forEach(System.out::println);
                    }
                    /* Cerca ID */
                    case 4 -> {

                    }
                    /* Visualizzazione dei prestiti */
                    case 5 -> {

                    }
                    /* Inserimento libro */
                    case 6 -> {

                    }
                    /* Cancella libro */
                    case 7 -> {

                    }
                    /* Esistenza libro */
                    case 8 -> {

                    }
                    default -> {
                        exit = false;
                    }
                }
            }
        }
    }
}