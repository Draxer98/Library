package util;

import books.Book;
import books.BookCopy;
import events.Loan;
import events.Sell;

import java.util.ArrayList;
import java.util.Scanner;

public class Util {
    private Util() {}

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

    public static ArrayList<String> getSellMenu(ArrayList<Sell> sells) {
        ArrayList<String> sellString = new ArrayList<>();
        sellString.add("SCEGLI IL TUO LIBRO");

        sells.forEach(sell -> {
            sellString.add(sell.toString());
        });

        return sellString;
    }

    public static ArrayList<String> getLoanMenu(ArrayList<Loan> loans) {
        ArrayList<String> loansString = new ArrayList<>();
        loansString.add("SCEGLI IL TUO LIBRO");

        loans.forEach(loan -> {
            loansString.add(loan.toString());
        });

        return loansString;
    }

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

    public static ArrayList<String> bookForSellToMenu(ArrayList<BookCopy> booksForSell, Library library) {
        ArrayList<String> bookString = new ArrayList<>();

        bookString.add("SCEGLI IL TUO LIBRO");

        // Transform every book copy into a string
        booksForSell.forEach(book -> {
            bookString.add(book.toString(library));
        });

        return bookString;
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
