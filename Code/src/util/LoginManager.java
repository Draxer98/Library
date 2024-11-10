package util;

import libraryMembers.Admin;
import libraryMembers.LibraryMember;
import libraryMembers.User;

import java.util.ArrayList;

public class LoginManager {
    private ArrayList<User> users;
    private ArrayList<Admin> admins;
    private LibraryMember loggedInUser;

    public LoginManager(ArrayList<User> users, ArrayList<Admin> admins) {
        this.users = users == null ? new ArrayList<>() : users;
        this.admins = admins == null ? new ArrayList<>() : admins;
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
