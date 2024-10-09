import events.Loan;
import events.Sell;

import java.util.List;

public class User {
    private String id;
    private String password;
    private String nome;
    private String cognome;
    private int phoneNumber;
    private List<Loan> loans;
    private List<Sell> sells;
}
