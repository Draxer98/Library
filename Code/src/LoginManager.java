import java.util.ArrayList;

class LoginManager {
    private ArrayList<User> users;
    private User loggedInUser;

    public LoginManager(ArrayList<User> users) {
        this.users = users;
        this.loggedInUser = null;
    }

    public boolean login(String id, String password) {

        for (User user : users) {
            if (user.getId().equals(id) && user.getPassword().equals(password)) {
                loggedInUser = user;
                return true;
            }
        }

        return false;
    }
}
