import events.Loan;
import events.Sell;

import java.util.ArrayList;

public class Library {
    private double yield;
    private ArrayList<Sell> sells;
    private ArrayList<Loan> loans;
    private ArrayList<Book> booksForLoan;
    private ArrayList<BookCopy> booksForSell;

    public Library(ArrayList<Book> booksForLoan, ArrayList<BookCopy> booksForSell) {
        this.booksForLoan = booksForLoan;
        this.booksForSell = booksForSell;
    }

    public void addBookForLoan(Book b) {
        this.booksForLoan.add(b);
    }

    public void addBookForSell(BookCopy b) {
        this.booksForSell.add(b);
    }

    public ArrayList<Book> getBooksForLoan() {
        return booksForLoan;
    }

    public ArrayList<BookCopy> getBooksForSell() {
        return booksForSell;
    }
}
