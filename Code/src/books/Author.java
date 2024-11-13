package books;

import java.util.ArrayList;

/**
 * The {@code Author} class represents an author of books.
 * An author has an identifier, a first name, a last name, and a list of books they have written.
 */
public class Author {
    private String id;
    private String name;
    private String surname;
    private ArrayList<Book> books;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Autore {" +
                "nome ='" + name + "'" +
                ", cognome ='" + surname + "'" +
                '}';
    }
}
