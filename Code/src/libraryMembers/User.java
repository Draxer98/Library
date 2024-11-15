package libraryMembers;

import events.Loan;
import events.Sell;
import org.json.simple.JSONObject;

import java.util.ArrayList;

/**
 * Represents the user account in the library.
 * Is the child class of {@link LibraryMember}.
 */
public class User extends LibraryMember {
    private ArrayList<Loan> loans;
    private ArrayList<Sell> sells;

    public User(String id, String password, String name, String surname, long phoneNumber, ArrayList<Loan> loans, ArrayList<Sell> sells) {
        super(id, password, name, surname, phoneNumber);
        this.loans = loans == null ? new ArrayList<>() : loans;
        this.sells = sells == null ? new ArrayList<>() : sells;
    }

    public User(String id, String password, String name, String surname, long phoneNumber) {
        super(id, password, name, surname, phoneNumber);
        this.sells = new ArrayList<>();
        this.loans = new ArrayList<>();
    }

    public void addLoan(Loan loan) {
        this.loans.add(loan);
    }

    public void addSell(Sell sell) {
        this.sells.add(sell);
    }

    public ArrayList<Loan> getLoans() {
        return loans;
    }

    public void setLoans(ArrayList<Loan> loans) {
        this.loans = loans;
    }

    public ArrayList<Sell> getSells() {
        return sells;
    }

    public void setSells(ArrayList<Sell> sells) {
        this.sells = sells;
    }

    public boolean equals(User user) {
        return false;
    }

    private String loansToString() {
        StringBuilder a = new StringBuilder();

        a.append("\n");

        for (Loan loan : loans) {
            a.append("\t\t").append(loan.toString()).append("\n");
        }

        return a.toString();
    }

    private String sellsToString() {
        StringBuilder a = new StringBuilder();

        a.append("\n");

        for (Sell sell : sells) {
            a.append("\t\t").append(sell.toString()).append(",\n");
        }

        return a.toString();
    }

    @Override
    public String toString() {
        return "Utente: {" + "\n" +
                "\tid = '" + id + "'\n" +
                "\tpassword = '" + password + "'\n" +
                "\tnome = '" + name + "'\n" +
                "\tcognome = '" + surname + "'\n" +
                "\tNumero di telefono = '" + phoneNumber + "'\n" +
                "\tPrestiti = [" + loansToString() + "\t]," + "\n" +
                "\tVendite = [" + sellsToString() + "\t]" + "\n" +
                "}";
    }

    public JSONObject toJson() {
        return new JSONObject();
    }
}
