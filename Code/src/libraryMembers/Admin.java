package libraryMembers;

public class Admin extends LibraryMember {
    public Admin(String id, String password, String name, String surname, long phoneNumber) {
        super(id, password, name, surname, phoneNumber);
    }

    @Override
    public String toString() {
        return "Admin: {" + "\n" +
                "\tId = '" + id + "'\n" +
                "\tPassword = '" + password + "'\n" +
                "\tNome = '" + name + "'\n" +
                "\tCognome = '" + surname + "'\n" +
                "\tNumero di telefono = '" + phoneNumber + "'\n" +
                "}";
    }
}
