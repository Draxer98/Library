import books.Book;
import books.BookCopy;
import events.Loan;
import events.Sell;
import handler.*;
import libraryMembers.Admin;
import libraryMembers.LibraryMember;
import libraryMembers.User;
import util.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String userPath = "src/data/files/Users.json";
        String adminPath = "src/data/files/Admin.json";
        String bookForLoanPath = "src/data/files/BooksForLoan.json";
        String bookForSellPath = "src/data/files/BooksForSell.json";
        String generalData = "src/data/files/GeneralData.json";

        Initialize initialize = new Initialize(
                "src/data/files/Users.json",
                "src/data/files/Admin.json",
                "src/data/files/BooksForLoan.json",
                "src/data/files/GeneralData.json"
        );
        LoginManager loginManager = new LoginManager(initialize.getUsers(), initialize.getAdmins());
        Library library = new Library(initialize.getBooksForLoan(), initialize.getBooksForSell());
        RegistrationHandler registrationHandler = new RegistrationHandler(initialize.getUsers(), initialize.getAdmins());
        InsertNewBookHandler insertNewBookHandler;
        InsertNewCopyBookHandler insertNewCopyBookHandler;
        DeleteBookHandler deleteBookHandler;
        ExistBookHandler existBookHandler = new ExistBookHandler(library.getBooksForLoan(), library.getBooksForSell(false));

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
                "Visualizza tutti i libri",
                "Cerca ID",
                "Visualizzazione dei prestiti",
                "Inserimento libro",
                "Inserisci copie di un libro",
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
                        ArrayList<String> bookString = library.bookForSellToMenu();

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
                                library.searchByIsbn(chosenBook.getParentIsbn()).getPrice(),
                                LocalDate.now()
                        );

                        // Add the new sell in the ArrayList of the user
                        user.addSell(newSell);

                        // Delete the sell book
                        library.getBooksForSell(false).remove(chosenBook);

                        library.getBooksForLoan().forEach(book -> {
                            book.getIsbnCopyBook().removeIf(isbn -> isbn.equals(chosenBook.getIsbn()));
                        });
                    }
                    /* Prendere in prestito un libro */
                    case 2 -> {
                        if (library.getBooksForLoan().isEmpty()) {
                            System.out.println("Non ci sono libri. Disponibili per il perstito");
                            break;
                        }

                        ArrayList<String> bookString = library.bookForLoanToMenu();

                        // if the size of the final array is
                        // it means that there is only the title
                        // so there aren't books
                        if (bookString.size() == 1) {
                            System.out.println("Non ci sono libri disponibili per il perstito");
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

                        for (Book book : library.getBooksForLoan()) {
                            if (book.getIsbn().equals(chosenBook.getIsbn())) {
                                book.setAvaiable(false);
                            }
                        }
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
                        LibraryMember libraryMember = registrationHandler.takeBaseInfoOfUser(scanner);

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

                        System.out.println("Admin creato con ID = " + id + ", PASSWORD = " + password);
                    }
                    /* Visualizza tutti utenti */
                    case 3 -> {
                        initialize.getUsers().forEach(System.out::println);

                        initialize.getAdmins().forEach(System.out::println);
                    }
                    /* Visualizza tutti i libri */
                    case 4 -> {
                        System.out.println("Libri per il prestito");

                        library.getBooksForLoan().forEach(book -> System.out.println("\t" + book.toString()));

                        System.out.println("Libri per la vendita'");

                        library.getBooksForSell(false).forEach(book -> System.out.println("\t" + book.toString(library.getBooksForLoan())));
                    }
                    /* Cerca ID */
                    case 5 -> {

                        LibraryMember libraryMember;
                        String id = scanner.nextLine();

                        if ((libraryMember = initialize.getLibraryMemberFormId(id)) != null) {
                            System.out.println("Utente trovato.\n" + libraryMember.toString());
                        } else {
                            System.out.println("Utente non trovato");
                        }
                    }
                    /* Visualizzazione dei prestiti */
                    case 6 -> {
                        initialize.getUsers().forEach(user -> {
                            System.out.println("Questi sono i prestiti di " + user.getName() + " " + user.getSurname() + " (" + user.getId() + ")");
                            user.getLoans().forEach(loan -> {
                                System.out.println("\t" + loan.toString());
                            });
                        });
                    }
                    /* Inserimento libro */
                    case 7 -> {
                        insertNewBookHandler = new InsertNewBookHandler(library.getBooksForLoan());

                        Book newBook = insertNewBookHandler.takeBaseInfoOfBookForLoan(scanner);

                        library.addBookForLoan(newBook);
                    }
                    /* Inserisci copie di un libro */
                    case 8 -> {
                        // Assign to a new class to refresh bookForLoan
                        insertNewCopyBookHandler = new InsertNewCopyBookHandler(library.getBooksForLoan());

                        // Print book for loan list
                        System.out.println("-------------");
                        System.out.println("    LIBRI    ");
                        System.out.println("-------------");

                        library.getBooksForLoan().forEach(System.out::println);

                        System.out.println("-------------");

                        // select book where to add copies
                        Book book = library.searchByIsbn(insertNewCopyBookHandler.selectBookToModify(scanner));

                        // add copies to the book
                        book.addIsbnBookCopy(insertNewCopyBookHandler.takeIsbnToInsert(scanner));

                        library.syncBooksForSell();
                    }
                    /* Cancella libro */
                    case 9 -> {
                        // Check if there are book in library
                        if (library.getBooksForLoan().isEmpty()) {
                            System.out.println("Non ci sono libri nella biblioteca.");
                        }

                        // Assign to a new class to refresh bookForLoan
                        deleteBookHandler = new DeleteBookHandler(library.getBooksForLoan());

                        // Print book for loan list
                        System.out.println("-------------");
                        System.out.println("    LIBRI    ");
                        System.out.println("-------------");

                        library.getBooksForLoan().forEach(System.out::println);

                        System.out.println("-------------");

                        // select book to delete
                        Book book = library.searchByIsbn(deleteBookHandler.selectBookToDelete(scanner));

                        // Delete book from the list
                        if (library.deleteBook(book)) {
                            System.out.println("Libro eliminato.");
                        } else {
                            System.out.println("Libro non trovato.");
                        }
                    }
                    /* Esistenza libro */
                    case 10 -> {
                        existBookHandler = new ExistBookHandler(library.getBooksForLoan(), library.getBooksForSell(false));

                        // take the isbn to check the exists
                        String isbn = existBookHandler.selectBookToVerify(scanner);

                        for (Book book : library.getBooksForLoan()) {
                            if (book.getIsbn().equals(isbn)) {
                                System.out.println("Libro trovato. Eccolo: ");
                                System.out.println(book.toString());
                                break;
                            }
                        }

                        for (BookCopy book : library.getBooksForSell(false)) {
                            if (book.getIsbn().equals(isbn)) {
                                System.out.println("Libro trovato. Eccolo: ");
                                System.out.println(book.toString(library.getBooksForLoan()));
                                break;
                            }
                        }

                        System.out.println("Libro non trovato");
                    }
                    default -> {
                        exit = false;
                    }
                }
            }
        }

        new Close(initialize, library, userPath, adminPath, bookForLoanPath, bookForSellPath, generalData);
    }
}