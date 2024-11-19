package handler;

import books.Author;
import books.Book;
import category.Category;
import excepetions.isbn.DuplicateIsbnException;
import excepetions.isbn.IllegalLengthForIsbnException;
import excepetions.isbn.IsbnNotFoundException;
import util.Initialize;

import java.util.ArrayList;
import java.util.Scanner;

public class InsertNewCopyBookHandler {
    private ArrayList<Book> booksForLoan;

    public InsertNewCopyBookHandler(ArrayList<Book> booksForLoan) {
        this.booksForLoan = booksForLoan;
    }

    public String selectBookToModify(Scanner scanner) {
        String isbn;

        while (true) {
            try {
                System.out.println("Inserisci l'isbn del libro a cui devi aggiungere le copie (0 per uscire): ");
                isbn = scanner.nextLine();

                // Exit case
                if (isbn.equals("0")) {
                    break;
                }

                // Check the length of the isbn
                if (isbn.length() != Initialize.getIsbnLength()) {
                    throw new IllegalLengthForIsbnException();
                }

                // Check if there is a book with the isbn insert by user
                if (!booksForLoan.contains(new Book(isbn, "", new ArrayList<Author>(), 0.0, Category.CAT1, new ArrayList<String>()))) {
                    throw new IsbnNotFoundException();
                }

                break;
            } catch (IllegalLengthForIsbnException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Devi inserire un valore numerico.");
            }
        }

        return isbn;
    }

    // todo fai inserire tutti gli isbn che devono essere inseriti e ritornali
    public ArrayList<String> takeIsbnToInsert(Scanner scanner) {
        ArrayList<String> isbns = new ArrayList<>();
        int num = 0;
        String isbn;

        while (true) {
            try {
                System.out.println("Inserisci il numero di copie da inserire (0 se non si devono inserire copie): ");
                num = Integer.parseInt(scanner.nextLine());

                if (num < 0) {
                    throw new Exception("Il numero deve essere maggiore o uguale 0.");
                }

                break;
            } catch (NumberFormatException e) {
                System.out.println("Devi inserire un numero.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        for (int i = 0; i < num; i++) {
            while (true) {
                try {
                    System.out.println("Inserisci l'isbn della " + (i+1) + " copia: ");
                    isbn = scanner.nextLine();

                    if (isbns.contains(isbn)) {
                        throw new DuplicateIsbnException();
                    }

                    verifyIsbn(isbn);

                    isbns.add(isbn);

                    break;
                } catch (IllegalLengthForIsbnException | DuplicateIsbnException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        return isbns;
    }

    /**
     * This method check if the isbn passed as parameter if valid
     * by cycling the booksForLoan attribute of the class.
     *
     * @param isbn string to check
     * @return true if the isbn as parameter is unique, else false.
     */
    private void verifyIsbn(String isbn) throws IllegalLengthForIsbnException, DuplicateIsbnException {

        // Check the length of isbn
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
