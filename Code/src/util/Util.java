package util;

import books.Book;
import books.BookCopy;
import events.Loan;
import events.Sell;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class has all method that prepare the data to be displayed in the console.
 */
public class Util {
    private Util() {}

    /**
     * Clears the console screen using a system-specific command.
     * Only works on Windows; may not be compatible with other operating systems.
     */
    public static void clrScr() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Pauses execution for the specified amount of milliseconds.
     *
     * @param millisecond The number of milliseconds to wait.
     */
    public static void wait(int millisecond) {
        try {
            Thread.sleep(millisecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates a menu with all available sales for displaying in the console.
     *
     * @param sells List of sales to be displayed.
     * @return ArrayList of strings representing the sales menu options.
     */
    public static ArrayList<String> getSellMenu(ArrayList<Sell> sells) {
        ArrayList<String> sellString = new ArrayList<>();
        sellString.add("SCEGLI IL TUO LIBRO");

        sells.forEach(sell -> {
            sellString.add(sell.toString());
        });

        return sellString;
    }

    /**
     * Generates a menu with all active loans for displaying in the console.
     *
     * @param loans List of loans to be displayed.
     * @return ArrayList of strings representing the loan menu options.
     */
    public static ArrayList<String> getLoanMenu(ArrayList<Loan> loans) {
        ArrayList<String> loansString = new ArrayList<>();
        loansString.add("SCEGLI IL TUO LIBRO");

        loans.forEach(loan -> {
            loansString.add(loan.toString());
        });

        return loansString;
    }

    /**
     * Generates a menu with all books available for loan, displaying only available books.
     *
     * @param booksForLoan List of books that can be loaned.
     * @return ArrayList of strings representing the available books menu.
     */
    public static ArrayList<String> bookForLoanToMenu(ArrayList<Book> booksForLoan) {
        ArrayList<String> bookString = new ArrayList<>();
        bookString.add("SCEGLI IL TUO LIBRO");

        // Transform only the available books into a string
        booksForLoan.forEach(book -> {
            if (book.isAvaiable()) {
                bookString.add(book.toString());
            }
        });

        return bookString;
    }

    /**
     * Generates a menu with all book copies available for sale.
     *
     * @param booksForSell List of book copies available for sale.
     * @param library      Reference to the library to retrieve contextual information.
     * @return ArrayList of strings representing the books for sale menu.
     */
    public static ArrayList<String> bookForSellToMenu(ArrayList<BookCopy> booksForSell, Library library) {
        ArrayList<String> bookString = new ArrayList<>();

        bookString.add("SCEGLI IL TUO LIBRO");

        // Transform every book copy into a string
        booksForSell.forEach(book -> {
            bookString.add(book.toString(library));
        });

        return bookString;
    }

    /**
     * Displays a menu based on the provided options and retrieves the user’s selection.
     *
     * @param options Array of menu options to be displayed.
     * @param scanner Scanner instance for reading user input.
     * @return The user's choice as an integer.
     */
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

            try {
                scelta = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                scelta = -1;
            }

            if ((scelta < 1) || (scelta > options.length - 1)) {
                System.out.println("Opzione Sbagliata");
            }
        }
        while ((scelta < 1) || (scelta > options.length - 1));

        return scelta;
    }

    /**
     * Displays a menu based on the provided list of options and retrieves the user’s selection.
     *
     * @param options ArrayList of menu options to be displayed.
     * @param scanner Scanner instance for reading user input.
     * @return The user's choice as an integer.
     */
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
                scelta = Integer.parseInt(scanner.nextLine());
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
