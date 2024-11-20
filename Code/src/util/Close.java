package util;

import books.Book;
import data.JsonWriter;
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

    }

    private void saveAdmin(String adminPath) {

    }

    private void saveUser(String userPath) {

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
