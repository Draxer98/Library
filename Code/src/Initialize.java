import java.util.ArrayList;

class Initialize {
    private ArrayList<User> users;
    private LoginManager loginManager;

    public Initialize(String path) {
        users = new ArrayList<>();
        loadUsersFromFile(path);
    }

    private void loadUsersFromFile(String path) {

    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
