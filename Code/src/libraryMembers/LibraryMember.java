package libraryMembers;

/**
 * Represents the Library member. It is the parent class of {@link Admin} and {@link User}.
 */
public class LibraryMember {
    protected String id;
    protected String password;
    protected String name;
    protected String surname;
    protected long phoneNumber;

    public LibraryMember(String id, String password, String name, String surname, long phoneNumber) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
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

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Membro libreria: {" + "\n" +
                "\tId = '" + id + "\n" +
                "\tPassword = '" + password + "\n" +
                "\tNome = '" + name + "\n" +
                "\tCognome = '" + surname + "\n" +
                "\tNumero di telefono = '" + phoneNumber + "\n" +
                "}";
    }
}
