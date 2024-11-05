import java.util.ArrayList;

class LoginManager {
    private ArrayList<User> users;
    private ArrayList<Admin> admins;
    private LibraryMember loggedInUser;

    public LoginManager(ArrayList<User> users, ArrayList<Admin> admins) {
        this.users = users;
        this.admins = admins;
        this.loggedInUser = null;
    }

    public boolean login(String id, String password) {

        for (User user : users) {
            if (user.getId().equals(id) && user.getPassword().equals(password)) {
                loggedInUser = user;
                return true;
            }
        }

        for (Admin admin : admins) {
            if (admin.getId().equals(id) && admin.getPassword().equals(password)) {
                loggedInUser = admin;
                return true;
            }
        }

        return false;
    }

    public LibraryMember getLoggedInUser() {
        return loggedInUser;
    }
}
