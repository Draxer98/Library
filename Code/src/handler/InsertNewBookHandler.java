package handler;

import books.Author;
import books.Book;
import books.BookCopy;
import category.Category;

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



        return new Book("", "", new ArrayList<Author>(), 0, Category.CAT1);
    }

    private ArrayList<Author> takeAuthorFromTheUser() {

    }

    /**
     * This method check if the isbn passed as parameter if valid
     * by cycling the booksForLoan attribute of the class.
     *
     * @param isbn string to check
     * @return true if the isbn as parameter is unique, else false.
     */
    private boolean verifyIsbn(String isbn) {

        for (Book book : booksForLoan) {
            // if the book's isbn is equals to the one passed ad parameter
            // stop cycling by return false
            if (book.getIsbn().equals(isbn)) {
                return false;
            }

            for (String bookCopy : book.getIsbnCopyBook()) {
                // if the bookCopy's isbn is equals to the one passed ad parameter
                // stop cycling by return false
                if (bookCopy.equals(isbn)) {
                    return false;
                }
            }
        }

        // if the program arrived here is because
        // the isbn passed ad parameter is unique
        return true;
    }
}