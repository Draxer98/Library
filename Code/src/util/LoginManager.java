package util;

import libraryMembers.Admin;
import libraryMembers.LibraryMember;
import libraryMembers.User;

import java.util.ArrayList;

/**
 * This class takes care of authenticate the user.
 */
public class LoginManager {
    private ArrayList<User> users;
    private ArrayList<Admin> admins;
    private LibraryMember loggedInUser;

    public LoginManager(ArrayList<User> users, ArrayList<Admin> admins) {
        this.users = users == null ? new ArrayList<>() : users;
        this.admins = admins == null ? new ArrayList<>() : admins;
        this.loggedInUser = null;
    }

    /**
     * This method research if there is a user or an admin with the given id and password.
     * If there aren't the method return false.
     *
     * @param id id to research.
     * @param password password to research.
     *
     * @return true if it finds a user or an admin with the given id and password.
     */
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
