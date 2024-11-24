package util;

import books.Book;
import books.BookCopy;
import data.JsonWriter;
import libraryMembers.Admin;
import libraryMembers.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * The {@code Close} class handles the process of saving the library data
 * before the program is closed or terminated. It serializes the library's data
 * into JSON format and writes it to the corresponding files. The data saved 
 * includes books for loan, books for sale, users, admins, and general configuration data. 
 * This ensures that the state of the library is preserved and can be reloaded 
 * when the application is reopened. The class uses the {@link JsonWriter} 
 * utility to perform the writing operations.
 * 
 * <p>The following data is saved when the class is instantiated:</p>
 * <ul>
 *   <li>Books for loan (via the {@link Book} objects)</li>
 *   <li>Books for sale (via the {@link BookCopy} objects)</li>
 *   <li>Users (via the {@link User} objects)</li>
 *   <li>Admins (via the {@link Admin} objects)</li>
 *   <li>General data such as the unique ID number and ISBN length</li>
 * </ul>
 */
public class Close {
    private Initialize initialize;
    private Library library;

    /**
     * Constructs a {@code Close} object that saves all necessary data.
     * This constructor calls the appropriate methods to save books, users,
     * admins, and general data to their respective paths in JSON format.
     * 
     * @param initialize the {@link Initialize} object that contains configuration data
     * @param library the {@link Library} object containing the current library state
     * @param userPath the path where the user data should be saved
     * @param adminPath the path where the admin data should be saved
     * @param loanPath the path where the loan books data should be saved
     * @param sellPath the path where the books for sale data should be saved
     * @param generalDataPath the path where the general configuration data should be saved
     */
    public Close(Initialize initialize, Library library, String userPath, String adminPath, String loanPath, String sellPath, String generalDataPath) {
        this.initialize = initialize;
        this.library = library;

        saveBookForLoan(loanPath);
        saveBookForSell(sellPath);
        saveUser(userPath);
        saveAdmin(adminPath);
    }

    /**
     * Saves the books available for sale into the specified path in JSON format.
     * 
     * @param sellPath the file path where the books for sale data should be saved
     */
    private void saveBookForSell(String sellPath) {
        JSONArray jsonSellFile = new JSONArray();

        for (BookCopy bookCopy : library.getBooksForSell(false)) {
            jsonSellFile.add(bookCopy.toJson());
        }

        JsonWriter.writeList(jsonSellFile, sellPath);
    }

    /**
     * Saves the admin data into the specified path in JSON format.
     * 
     * @param adminPath the file path where the admin data should be saved
     */
    private void saveAdmin(String adminPath) {
        JSONArray jsonAdminFile = new JSONArray();

        for (Admin admin : initialize.getAdmins()) {
            jsonAdminFile.add(admin.toJson());
        }

        JsonWriter.writeList(jsonAdminFile, adminPath);
    }

    /**
     * Saves the user data into the specified path in JSON format.
     * 
     * @param userPath the file path where the user data should be saved
     */
    private void saveUser(String userPath) {
        JSONArray jsonUserFile = new JSONArray();

        for (User user : initialize.getUsers()) {
            jsonUserFile.add(user.toJson());
        }

        JsonWriter.writeList(jsonUserFile, userPath);
    }

    /**
     * Saves the books available for loan into the specified path in JSON format.
     * 
     * @param loanPath the file path where the loan books data should be saved
     */
    private void saveBookForLoan(String loanPath) {
        JSONArray jsonLoanFile = new JSONArray();

        for (Book book : library.getBooksForLoan()) {
            jsonLoanFile.add(book.toJson());
        }

        JsonWriter.writeList(jsonLoanFile, loanPath);
    }

    /**
     * Saves the general configuration data (such as ID number and ISBN length)
     * into the specified path in JSON format.
     * 
     * @param generalDataPath the file path where the general configuration data should be saved
     */
    private void saveGeneralData(String generalDataPath) {
        JSONObject jsonGeneralDataFile = new JSONObject();

        jsonGeneralDataFile.put("idNumber", initialize.getIdNumber());
        jsonGeneralDataFile.put("isbnLength", Initialize.getIsbnLength());

        JsonWriter.writeObj(jsonGeneralDataFile, generalDataPath);
    }
}
