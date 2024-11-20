package events;

import org.json.simple.JSONObject;

import java.time.LocalDate;

/**
 * Represents a loan of the library,
 * and it tracks isbn of the loaned book, the date when the book was loaned
 * and the date when the user who loaned the book have to return it.
 */
public class Loan {
    private String isbn;
    private LocalDate loanDate;
    private LocalDate expirationDate;

    public Loan(String isbn, LocalDate loanDate, LocalDate expirationDate) {
        this.isbn = isbn;
        this.loanDate = loanDate;
        this.expirationDate = expirationDate;
    }

    public void extendExpirationDate() {
        expirationDate.plusDays(30);
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "Prestito {" +
                "isbn libro preso = '" + isbn + "'" +
                ", Data di prestito = '" + loanDate.toString() + "'" +
                ", Data di consegna = '" + expirationDate.toString() + "'" +
                "}";
    }

    public JSONObject toJson() {
        JSONObject obj = new JSONObject();

        obj.put("isbn", isbn);
        obj.put("loanDate", loanDate.toString());
        obj.put("expirationDate", expirationDate.toString());

        return obj;
    }
}
