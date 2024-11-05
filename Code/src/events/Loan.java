package events;

import java.time.LocalDate;

public class Loan {
    private String isbn;
    private LocalDate loanDate;
    private LocalDate expirationDate;

    public Loan(String isbn, LocalDate loanDate, LocalDate expirationDate) {
        this.isbn = isbn;
        this.loanDate = loanDate;
        this.expirationDate = expirationDate;
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
}
