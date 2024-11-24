package handler;

import books.Author;
import books.Book;
import books.BookCopy;
import category.Category;
import excepetions.isbn.IllegalLengthForIsbnException;
import excepetions.isbn.IsbnNotFoundException;
import util.Initialize;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handler class for managing the verification of existing books in the system.
 * Provides functionality to interact with the user for selecting a book by its ISBN
 * to add copies to the inventory. It performs validation on the ISBN length and checks
 * if the book exists in the list before proceeding.
 */
public class ExistBookHandler {
    private ArrayList<Book> bookForLoan;
    private ArrayList<BookCopy> bookForSell;

    /**
     * Constructs an {@link ExistBookHandler} with lists of books available for loan and books for sale.
     * 
     * @param bookForLoan A list of {@link Book} objects representing the books available for loan.
     * @param bookForSell A list of {@link BookCopy} objects representing the books available for sale.
     */
    public ExistBookHandler(ArrayList<Book> bookForLoan, ArrayList<BookCopy> bookForSell) {
        this.bookForLoan = bookForLoan;
        this.bookForSell = bookForSell;
    }

    /**
     * Prompts the user to input the ISBN of the book they wish to verify. It performs validation
     * on the ISBN's length and checks if the book exists in the collection before proceeding.
     * If the input is invalid (wrong length or book not found), it will prompt the user again.
     * The method exits if the user inputs "0".
     * 
     * @param scanner The {@link Scanner} object used to read user input.
     * @return The ISBN of the book selected for verification.
     * @throws IllegalLengthForIsbnException If the ISBN length is not valid according to the expected length.
     * @throws IsbnNotFoundException If no book with the provided ISBN is found in the collection.
     */
    public String selectBookToVerify(Scanner scanner) {
        String isbn;

        while (true) {
            try {
                System.out.println("Inserisci l'isbn del libro a cui devi aggiungere le copie (0 per uscire): ");
                isbn = scanner.nextLine();

                if (isbn.equals("0")) {
                    break;
                }

                if (isbn.length() != Initialize.getIsbnLength()) {
                    throw new IllegalLengthForIsbnException();
                }

                if (!bookForLoan.contains(new Book(isbn, "", new ArrayList<Author>(), 0.0, Category.CAT1, new ArrayList<String>()))) {
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
