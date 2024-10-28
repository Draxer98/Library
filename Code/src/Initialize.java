import data.JsonReader;
import events.Loan;
import events.Sell;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

class Initialize {
    private ArrayList<User> users;
    private LoginManager loginManager;

    public Initialize(String path) {
        users = new ArrayList<>();
        users = loadUsersFromFile(path);
    }

    /**
     * Carica un elenco di utenti da un file JSON specificato, popolando ogni utente
     * con i rispettivi dati di prestiti (loans) e vendite (sells).
     * <p>
     * Il file JSON deve avere la seguente struttura:
     * <pre>
     * [
     *   {
     *     "id": "stringa",
     *     "password": "stringa",
     *     "name": "stringa",
     *     "surname": "stringa",
     *     "phoneNumber": numero intero,
     *     "loans": [
     *       {
     *         "isbn": "stringa",
     *         "loanDate": "stringa (formato: dd-MM-yyyy)",
     *         "expirationDate": "stringa (formato: dd-MM-yyyy)"
     *       }
     *     ],
     *     "sells": [
     *       {
     *         "isbnSoldBook": "stringa",
     *         "idUser": "stringa",
     *         "price": numero intero,
     *         "sellDate": "stringa (formato: dd-MM-yyyy)"
     *       }
     *     ]
     *   }
     * ]
     * </pre>
     *
     * @param path il percorso del file JSON da cui caricare i dati degli utenti.
     *
     * @return una lista di oggetti `User` caricati dal file JSON.
     */
    private ArrayList<User> loadUsersFromFile(String path) {
        ArrayList<User> users = new ArrayList<>();

        JSONArray jsonArray = JsonReader.readArray(path);

        // Cycle through the json array of jsonObjects
        for (Object obj : jsonArray) {
            JSONObject jsonUser = (JSONObject) obj;

            // Get the loans from the json Obj of the user
            ArrayList<Loan> loans = new ArrayList<>();
            JSONArray jsonLoans = (JSONArray) jsonUser.get("loans");

            for (Object objLoans : jsonLoans) {
                JSONObject jsonLoan = (JSONObject) objLoans;

                loans.add(new Loan(
                        (String) jsonLoan.get("isbn"),
                        LocalDate.parse((String) jsonLoan.get("loanDate"), DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                        LocalDate.parse((String) jsonLoan.get("expirationDate"), DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                ));
            }

            // Get the sells from the json Obj of the user
            ArrayList<Sell> sells = new ArrayList<>();
            JSONArray jsonSells = (JSONArray) jsonUser.get("sells");

            for (Object objSell : jsonSells) {
                JSONObject jsonSell = (JSONObject) objSell;

                sells.add(new Sell(
                        (String) jsonSell.get("isbnSoldBook"),
                        (String) jsonSell.get("idUser"),
                        (int) jsonSell.get("price"),
                        LocalDate.parse((String) jsonSell.get("sellDate"), DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                ));
            }

            users.add(new User(
                    (String) jsonUser.get("id"), (String) jsonUser.get("password"),
                    (String) jsonUser.get("name"), (String) jsonUser.get("surname"),
                    (int) jsonUser.get("phoneNumber"),
                    loans, sells
            ));
        }

        return users;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
