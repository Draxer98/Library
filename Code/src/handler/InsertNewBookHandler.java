package handler;

import books.Author;
import books.Book;
import books.BookCopy;
import category.Category;
import excepetions.isbn.DuplicateIsbnException;
import excepetions.isbn.IllegalLengthForIsbnException;
import util.Initialize;
import util.Util;

import java.util.ArrayList;
import java.util.Scanner;

public class InsertNewBookHandler {
    private ArrayList<Book> booksForLoan;

    public InsertNewBookHandler(ArrayList<Book> booksForLoan) {
        this.booksForLoan = booksForLoan;
    }

    public BookCopy takeBaseInfoOfBookForSell(Scanner scanner) {
        return new BookCopy("", "");
    }

    public Book takeBaseInfoOfBookForLoan(Scanner scanner) {

        String isbn, title;
        double price;
        ArrayList<Author> authors;
        ArrayList<String> isbnCopy;
        int numOfBookCopy;

        // isbn
        while (true) {
            try {
                System.out.println("Inserisci l'isbn del libro.");
                isbn = scanner.nextLine();

                verifyIsbn(isbn);

                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        // title
        do {
            System.out.println("Inserisci titolo del libro: ");
            title = scanner.nextLine();

            if (title.isEmpty()) {
                System.out.println("Non lasciare vuoto il titolo.");
            }
        } while (title.isEmpty());

        // authors
        do {
            authors = takeAuthors(scanner);

            if (authors.isEmpty()) {
                System.out.println("devi inserire almeno un autore.\nSe non si conosce mettere sconosciuto");
            }
        } while (authors.isEmpty());


        // price
        while (true) {
            try {
                System.out.println("Inserisci il prezzo del libro: ");
                price = Double.parseDouble(scanner.nextLine());

                break;
            } catch (NumberFormatException e) {
                System.out.println("Devi inserire un valore numerico.");
            }
        }

        // category
        Category category = takeCategory(scanner);

        // num of book copy
        while (true) {
            try {
                System.out.println("Inserisci il numero di copie che ha questo libro.");
                numOfBookCopy = Integer.parseInt(scanner.nextLine());

                break;
            } catch (Exception e) {
                System.out.println("Devi inserire un valore numerico.");
            }
        }

        // get isbn of book copy
        isbnCopy = takeIsbnCopy(isbn, numOfBookCopy, scanner);

        return new Book(isbn, title, authors, price, category, isbnCopy);
    }

    private ArrayList<String> takeIsbnCopy(String parentIsbn, int num, Scanner scanner) {

        ArrayList<String> isbns = new ArrayList<>();

        String isbn;

        for (int i = 0; i < num; i++) {
            while (true) {
                try {
                    System.out.println("Inserisci il " + i + " isbn: ");
                    isbn = scanner.nextLine();

                    // Verify if the user already insert this isbn in this loop.
                    if (isbns.contains(isbn) || parentIsbn.equals(isbn)) {
                        throw new Exception("Questo isbn esiste gia'.");
                    }

                    verifyIsbn(isbn);

                    isbns.add(isbn);

                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        return isbns;
    }

    private Category takeCategory(Scanner scanner) {
        int chosenCategory = Util.menu(Category.toMenu(), scanner) - 1;

        return Category.values()[chosenCategory];
    }

    private ArrayList<Author> takeAuthors(Scanner scanner) {
        ArrayList<Author> authors = new ArrayList<>();

        String name, surname, exit;

        System.out.println("\tInserimento autori.");

        // Get the authors
        do {
            // Get the name
            do {
                System.out.println("inserisci il nome dell'autore: ");
                name = scanner.nextLine();

                if (name.isEmpty()) {
                    System.out.println("Non lasciare vuoto il nome.");
                }
            } while (name.isEmpty());

            // Get the surname
            do {
                System.out.println("inserisci il cognome dell'autore: ");
                surname = scanner.nextLine();

                if (surname.isEmpty()) {
                    System.out.println("Non lasciare vuoto il cognome.");
                }
            } while (surname.isEmpty());

            // Add the author
            authors.add(new Author(name, surname));

            // Ask if the admin want to add another author
            System.out.println("Vuoi continuare a inserire autori? [y/n]: ");
            exit = scanner.nextLine().toLowerCase();

        } while (exit.equals("y"));

        return authors;
    }

    /**
     * This method check if the isbn passed as parameter if valid
     * by cycling the booksForLoan attribute of the class.
     *
     * @param isbn string to check
     * @return true if the isbn as parameter is unique, else false.
     */
    private void verifyIsbn(String isbn) throws IllegalLengthForIsbnException, DuplicateIsbnException {

        if (isbn.length() != Initialize.getIsbnLength()) {
            throw new IllegalLengthForIsbnException();
        }

        for (Book book : booksForLoan) {
            // if the book's isbn is equals to the one passed ad parameter
            // stop cycling by return false
            if (book.getIsbn().equals(isbn)) {
                throw new DuplicateIsbnException();
            }

            for (String bookCopy : book.getIsbnCopyBook()) {
                // if the bookCopy's isbn is equals to the one passed ad parameter
                // stop cycling by return false
                if (bookCopy.equals(isbn)) {
                    throw new DuplicateIsbnException();
                }
            }
        }
    }
}