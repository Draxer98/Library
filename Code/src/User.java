import events.Loan;
import events.Sell;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class User {
    private String id;
    private String password;
    private String name;
    private String surname;
    private long phoneNumber;
    private ArrayList<Loan> loans;
    private ArrayList<Sell> sells;

    public User(String id, String password, String name, String surname, long phoneNumber, ArrayList<Loan> loans, ArrayList<Sell> sells) {
         this.id = id;
         this.password = password;
         this.name = name;
         this.surname = surname;
         this.phoneNumber = phoneNumber;
         this.loans = loans;
         this.sells = sells;
    }

    public User(String id, String password, String name, String surname, int phoneNumber) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.loans = new ArrayList<>();
        this.sells = new ArrayList<>();
    }

    public User(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    @Override
    public String toString() {
        return "User: " +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", loans=" + loans +
                ", sells=" + sells;
    }

    public JSONObject toJson() {
        return new JSONObject();
    }
}
