package util;

import books.Book;
import books.BookCopy;
import books.Shelf;
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
    private ArrayList<Shelf> shelves;

    public Library(ArrayList<Book> booksForLoan, ArrayList<BookCopy> booksForSell, ArrayList<Shelf> shelves) {
        this.booksForLoan = booksForLoan;
        this.booksForSell = booksForSell;
        this.shelves = shelves;
    }

    /**
     * Generates a menu with all books available for loan, displaying only available books.
     *
     * @return ArrayList of strings representing the available books menu.
     */
    public ArrayList<String> bookForLoanToMenu() {
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
     * @return ArrayList of strings representing the books for sale menu.
     */
    public ArrayList<String> bookForSellToMenu() {
        ArrayList<String> bookString = new ArrayList<>();
        bookString.add("SCEGLI IL TUO LIBRO");

        // Transform every book copy into a string
        booksForSell.forEach(bookCopy -> {
            bookString.add(bookCopy.toString(this.booksForLoan));
        });

        return bookString;
    }

    /**
     * Synchronizes the `booksForSell` list with the ISBNs in the `isbnCopyBook` attribute of each book in `booksForLoan`.<br>
     * For each book in `booksForLoan`, this method checks if all ISBNs listed in its `isbnCopyBook` attribute have a <br>
     * corresponding `BookCopy` object in `booksForSell`. If a `BookCopy` for an ISBN is missing, it creates and adds one.<br>
     * <br>
     * This method ensures consistency between the books available for loans and those available for sale.<br>
     * If any modifications are made to the `booksForSell` list, the `modify` flag is set to true.<br>
     * <br>
     * <pre>
     * Example:
     * If a book in `booksForLoan` has an ISBN in its `isbnCopyBook` list, but no `BookCopy` object exists for it in
     * `booksForSell`, this method will create and add the missing `BookCopy`.
     * </pre>
     * <pre>
     * Post condition:
     * - The `booksForSell` list will have a `BookCopy` for every ISBN listed in the `isbnCopyBook` attributes of the books
     *   in `booksForLoan`.
     * - If modifications are made, the `modify` flag will be set to true.
     * </pre>
     */
    public void syncBooksForSell() {
        // Iterate through all books available for loan
        for (Book book : booksForLoan) {
            // Iterate through all ISBNs in the book's `isbnCopyBook` list
            for (String isbn : book.getIsbnCopyBook()) {
                // Check if the `booksForSell` list already contains a `BookCopy` for this ISBN
                boolean existsInSellList = booksForSell.stream()
                        .anyMatch(bookCopy -> bookCopy.getIsbn().equals(isbn));

                // If no `BookCopy` exists, create one and add it to the sellable list
                if (!existsInSellList) {
                    booksForSell.add(new BookCopy(isbn, book.getIsbn()));
                }
            }
        }
    }


    /**
     * This method research the parent isbn in the list of the books for loans.
     *
     * @param isbn the isbn to research.
     * @return the book if it finds the parentBook else return null
     */
    public Book searchByIsbn(String isbn) {
        for (Book book : booksForLoan) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }

        return null;
    }

    /**
     * Adds a book to the list of books available for loan.
     * If the book already exists, its ISBN copies are updated, and new copies are added to the sellable list.
     *
     * @param b the book to add.
     */
    public void addBookForLoan(Book b) {
        if (booksForLoan.contains(b)) {
            syncBooksForSell();
            return;
        }

        this.booksForLoan.add(b);

        // Add its ISBN copies to the sellable list
        for (String isbn : b.getIsbnCopyBook()) {
            addBookForSell(new BookCopy(isbn, b.getIsbn()));
        }
    }

    /**
     * Adds a book copy to the list of books available for sale.
     * Ensures no duplicate copies are added.
     *
     * @param b the book copy to add.
     */
    public void addBookForSell(BookCopy b) {
        // Prevent duplicate entries
        for (BookCopy existingCopy : booksForSell) {
            if (existingCopy.getIsbn().equals(b.getIsbn())) {
                return; // Do not add if the copy already exists
            }
        }

        this.booksForSell.add(b);
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

    public boolean deleteBook(Book book) {
        booksForSell.removeIf(bookCopy -> bookCopy.getParentIsbn().equals(book.getIsbn()));

        return booksForLoan.remove(book);
    }

    public ArrayList<Shelf> getShelves() {
        return shelves;
    }

    public ArrayList<Book> getBooksForLoanAvaiable() {
        // Get any book avaiable
        return new ArrayList<>(booksForLoan.stream().filter(Book::isAvaiable).toList());
    }

    public ArrayList<String> getBooksForLoanAvaiableToMenu() {
        ArrayList<String> bookString = new ArrayList<>();
        bookString.add("SCEGLI IL TUO LIBRO");

        // Transform only the available books into a string
        getBooksForLoanAvaiable().forEach(book -> {
            bookString.add(book.toString());
        });

        return bookString;
    }
}
