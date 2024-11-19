package handler;

import books.Author;
import books.Book;
import category.Category;
import excepetions.isbn.IllegalLengthForIsbnException;
import excepetions.isbn.IsbnNotFoundException;
import util.Initialize;

import java.util.ArrayList;
import java.util.Scanner;

public class DeleteBookHandler {
    private ArrayList<Book> booksForLoan;

    public DeleteBookHandler(ArrayList<Book> booksForLoan) {
        this.booksForLoan = booksForLoan;
    }

    public String selectBookToDelete(Scanner scanner) {
        String isbn;

        while (true) {
            try {
                System.out.println("Inserisci l'isbn del libro che devi eliminare (0 per uscire): ");
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
}
