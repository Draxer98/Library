package util;

import books.Author;
import books.Book;
import books.BookCopy;
import category.Category;
import data.JsonReader;
import events.Loan;
import events.Sell;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import libraryMembers.Admin;
import libraryMembers.User;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Initialize {
    private ArrayList<User> users;
    private ArrayList<Admin> admins;
    private ArrayList<Book> bookForLoan;
    private ArrayList<BookCopy> bookForSell;

    public Initialize(String userPath, String adminPath, String loanPath) {
        users = new ArrayList<>();
        users = loadUsersFromFile(userPath);
        admins = new ArrayList<>();
        admins = loadAdminsFromFile(adminPath);
        bookForLoan = new ArrayList<>();
        bookForLoan = loadBooksForLoan(loanPath);
        bookForSell = new ArrayList<>();
        bookForSell = loadBooksForSell(bookForLoan);
    }


    private ArrayList<Admin> loadAdminsFromFile(String path) {
        ArrayList<Admin> admins = new ArrayList<>();
        JSONArray jsonArray;

        try {
            jsonArray = JsonReader.readArray(path);
        } catch (Exception e) {
            return null;
        }

        // Cycle through the json array of jsonObjects
        for (Object obj : jsonArray) {
            JSONObject jsonAdmin = (JSONObject) obj;

            // Create new users.Admin with the correct data
            admins.add(new Admin(
                    (String) jsonAdmin.get("id"), (String) jsonAdmin.get("password")
            ));
        }

        return admins;
    }

    /**
     * Loads a list of users from a specified JSON file, populating each user
     * with their respective loan (loans) and sale (sells) data.
     * <p>
     * The JSON file must have the following structure:
     * <pre>
     * [
     *   {
     *     "id": "string",
     *     "password": "string",
     *     "name": "string",
     *     "surname": "string",
     *     "phoneNumber": integer,
     *     "loans": [
     *       {
     *         "isbn": "string",
     *         "loanDate": "string (format: dd-MM-yyyy)",
     *         "expirationDate": "string (format: dd-MM-yyyy)"
     *       },
     *       ...
     *     ],
     *     "sells": [
     *       {
     *         "isbnSoldBook": "string",
     *         "idUser": "string",
     *         "price": integer,
     *         "sellDate": "string (format: dd-MM-yyyy)"
     *       },
     *       ...
     *     ]
     *   },
     *   ...
     * ]
     * </pre>
     *
     * @param path the path of the JSON file from which to load user data.
     * @return a list of `users.User` objects loaded from the JSON file.
     */
    private ArrayList<User> loadUsersFromFile(String path) {
        ArrayList<User> users = new ArrayList<>();
        JSONArray jsonArray;

        try {
            jsonArray = JsonReader.readArray(path);
        } catch (Exception e) {
            return null;
        }

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
                        ((Number) jsonSell.get("price")).doubleValue(),
                        LocalDate.parse((String) jsonSell.get("sellDate"), DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                ));
            }

            users.add(new User(
                    (String) jsonUser.get("id"), (String) jsonUser.get("password"),
                    (String) jsonUser.get("name"), (String) jsonUser.get("surname"),
                    ((Number) jsonUser.get("phoneNumber")).intValue(),
                    loans, sells
            ));
        }

        return users;
    }

    /**
     * Loads a list of books from a JSON file located at the specified path.
     * Each book in the JSON file should follow the structure expected by the books.Book class,
     * with fields for ISBN, title, authors, price, category, ISBN copies, and availability status.
     *
     * @param path the path to the JSON file containing the array of books
     * @return an ArrayList of books.Book objects loaded from the JSON file,
     *         or null if the file could not be read or parsed
     * @throws IOException if an I/O error occurs while reading the file
     * @throws ParseException if the JSON data cannot be parsed
     * @throws IllegalArgumentException if the category string cannot be converted to a Category enum
     */
    public ArrayList<Book> loadBooksForLoan(String path) {
        // Crea una lista per memorizzare gli oggetti books.Book caricati dal file JSON
        ArrayList<Book> books = new ArrayList<>();
        JSONArray jsonArray = null;

        // Utilizza il blocco try-with-resources per assicurarsi che il FileReader venga chiuso correttamente
        try (FileReader reader = new FileReader(path)) {
            jsonArray = JsonReader.readArray(path);
        } catch (IOException | ParseException | IllegalArgumentException e) {
            // Gestisce eccezioni I/O, errori di parsing JSON e conversioni non valide dell'enum
            e.printStackTrace();
        }

        if (jsonArray == null) return null;

        // Itera su ciascun oggetto del JSONArray
        for (Object obj : jsonArray) {
            JSONObject bookJson = (JSONObject) obj;

            // Estrae i campi di base dal JSON e li assegna alle variabili
            String isbn = (String) bookJson.get("isbn");
            String title = (String) bookJson.get("title");

            // Estrazione degli autori (JSONArray), che viene convertito in una lista di oggetti books.Author
            JSONArray authorsArray = (JSONArray) bookJson.get("authors");
            ArrayList<Author> authors = new ArrayList<>();
            for (Object authorObj : authorsArray) {
                JSONObject authorJson = (JSONObject) authorObj;
                Author author = new Author();
                author.setName((String) authorJson.get("name"));
                author.setSurname((String) authorJson.get("surname"));
                authors.add(author);
            }

            // Estrae il prezzo del libro come double
            double price = ((Number) bookJson.get("price")).doubleValue();

            // Estrazione della categoria del libro, convertendola in un valore dell'enum Category
            Category category = Category.valueOf((String) bookJson.get("category"));

            // Crea un nuovo oggetto books.Book con i dati estratti
            Book book = new Book(isbn, title, authors, price, category);

            // Estrazione dei numeri ISBN per le copie del libro (JSONArray di stringhe)
            JSONArray isbnCopyArray = (JSONArray) bookJson.get("isbnCopyBook");
            ArrayList<String> isbnCopies = new ArrayList<>();
            for (Object isbnCopy : isbnCopyArray) {
                isbnCopies.add((String) isbnCopy);
            }
            book.setIsbnCopyBook(isbnCopies);

            // Imposta la disponibilità del libro in base al valore booleano nel JSON
            book.setAvaiable((Boolean) bookJson.get("isAvailable"));

            // Aggiunge il libro alla lista di libri
            books.add(book);
        }

        // Ritorna la lista di libri caricati dal file JSON
        return books;
    }

    private ArrayList<BookCopy> loadBooksForSell(ArrayList<Book> b) {
        ArrayList<BookCopy> books = new ArrayList<>();

        for (Book book : b) {
            for (String isbnBookCopy : book.getIsbnCopyBook()) {
                books.add(new BookCopy(isbnBookCopy, book.getIsbn()));
            }
        }

        return books;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Admin> getAdmins() {
        return admins;
    }

    public ArrayList<Book> getBookForLoan() {
        return bookForLoan;
    }

    public ArrayList<BookCopy> getBookForSell() {
        return bookForSell;
    }
}
