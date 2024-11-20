package util;

import books.Book;
import books.BookCopy;
import data.JsonWriter;
import libraryMembers.Admin;
import libraryMembers.User;
import org.json.simple.JSONArray;

public class Close {
    private Initialize initialize;
    private Library library;

    public Close(Initialize initialize, Library library, String userPath, String adminPath, String loanPath, String sellPath, String generalDataPath) {
        this.initialize = initialize;
        this.library = library;

        saveBookForLoan(loanPath);
        saveBookForSell(sellPath);
        saveUser(userPath);
        saveAdmin(adminPath);
    }

    private void saveBookForSell(String sellPath) {
        if (!library.isModify()) {
            return;
        }

        JSONArray jsonSellFile = new JSONArray();

        for (BookCopy bookCopy : library.getBooksForSell(false)) {
            jsonSellFile.add(bookCopy.toJson());
        }

        JsonWriter.writeList(jsonSellFile, sellPath);
    }

    private void saveAdmin(String adminPath) {
        if (!initialize.isModify()) {
            return;
        }

        JSONArray jsonAdminFile = new JSONArray();

        for (Admin admin : initialize.getAdmins()) {
            jsonAdminFile.add(admin.toJson());
        }

        JsonWriter.writeList(jsonAdminFile, adminPath);
    }

    private void saveUser(String userPath) {
        if (!initialize.isModify()) {
            return;
        }

        JSONArray jsonUserFile = new JSONArray();

        for (User user : initialize.getUsers()) {
            jsonUserFile.add(user.toJson());
        }

        JsonWriter.writeList(jsonUserFile, userPath);
    }

    private void saveBookForLoan(String loanPath) {
        if (!library.isModify()) {
            return;
        }

        JSONArray jsonLoanFile = new JSONArray();

        for (Book book : library.getBooksForLoan()) {
            jsonLoanFile.add(book.toJson());
        }

        JsonWriter.writeList(jsonLoanFile, loanPath);
    }
}
