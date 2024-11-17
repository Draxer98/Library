package util;

import books.Book;
import books.BookCopy;
import events.Loan;
import events.Sell;

import java.util.ArrayList;

/**
 * Represents the library. So it has the book that can be sold and the books that can be loaned. <br>
 * It also has all the loans and sells.
 * Modify field indicates whether {@code booksForLoan} or {@code booksForSell} has been changed.
 */
public class Library {
    private double yield;
    private ArrayList<Sell> sells;
    private ArrayList<Loan> loans;
    private ArrayList<Book> booksForLoan;
    private ArrayList<BookCopy> booksForSell;
    private boolean modify = false;

    public Library(ArrayList<Book> booksForLoan, ArrayList<BookCopy> booksForSell) {
        this.booksForLoan = booksForLoan;
        this.booksForSell = booksForSell;
    }

    /**
     * This method research the parent isbn in the list of the books for loans.
     *
     * @param parentIsbn the parentIsbn to research.
     * @return the book if it finds the parentBook else return null
     */
    public Book findParentBookByIsbn(String parentIsbn) {
        for (Book book : booksForLoan) {
            if (book.getIsbn().equals(parentIsbn)) {
                return book;
            }
        }

        return null;
    }

    public void addBookForLoan(Book b) {
        this.booksForLoan.add(b);

        if (!b.getIsbnCopyBook().isEmpty()) {
            for (String isbn : b.getIsbnCopyBook()) {
                addBookForSell(new BookCopy(isbn, b.getIsbn()));
            }
        }

        modify = true;
    }

    public void addBookForSell(BookCopy b) {
        this.booksForSell.add(b);
        modify = true;
    }

    public ArrayList<Book> getBooksForLoan() {
        return booksForLoan;
    }

    public ArrayList<BookCopy> getBooksForSell(boolean withoutDuplicates) {
        ArrayList<BookCopy> books = new ArrayList<>();

        BookCopy[] previous = {new BookCopy("123", "123")};

        if (withoutDuplicates) {
            booksForSell.forEach(book -> {
                // Verify if the previous obj is equals to the current one
                if (!previous[0].equals(book)) {
                    books.add(book);
                }
                // Set previous as the current book
                previous[0] = book;
            });

            return books;
        }

        return booksForSell;
    }
}
