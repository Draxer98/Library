package handler;

import events.Loan;
import util.Util;

import java.util.ArrayList;
import java.util.Scanner;

public class RepayLoanHandler {
    private ArrayList<Loan> loans;

    public RepayLoanHandler(ArrayList<Loan> loans) {
        this.loans = loans;
    }

    public Loan selectLoan(Scanner scanner) {
        // print all loan in menu
        // get the index
        int index = Util.menu(loanToMenu(), scanner) - 1;

        return loans.get(index);
    }

    private ArrayList<String> loanToMenu() {
        ArrayList<String> loanMenu = new ArrayList<>();

        loanMenu.add("PRESTITI");

        for (Loan loan : loans) {
            loanMenu.add(loan.toString());
        }

        return loanMenu;
    }
}
