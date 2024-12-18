package books;

import org.json.simple.JSONObject;

/**
 * The author class represents an author of books.
 * An author has an identifier, first name and last name.
 */
public class Author {
    private String name;
    private String surname;

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
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

    @Override
    public String toString() {
        return "Autore {" +
                "nome = '" + name + "'" +
                ", cognome = '" + surname + "'" +
                '}';
    }

    public JSONObject toJson() {
        JSONObject obj = new JSONObject();

        obj.put("name", name);
        obj.put("surname", surname);

        return obj;
    }
}
