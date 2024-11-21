package handler;

import events.Loan;

import java.time.LocalDate;
import java.util.ArrayList;

public class GiveBackLoanHandler {
    private ArrayList<Loan> loans;

    public GiveBackLoanHandler(ArrayList<Loan> loans) {
        this.loans = loans;
    }

    private Loan selectLoan() {


        return new Loan("", LocalDate.now(), LocalDate.now());
    }
}
