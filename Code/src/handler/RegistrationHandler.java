package handler;

import excepetions.DuplicatePhoneNumberException;
import excepetions.IllegalLengthForNumberException;
import libraryMembers.Admin;
import libraryMembers.User;

import java.util.ArrayList;
import java.util.Random;

public class RegistrationHandler {
    private ArrayList<User> users;
    private ArrayList<Admin> admins;

    // Set of characters to use for the password
    private final String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    private final String DIGITS = "0123456789";

    // Combine all characters into a single string
    private final String ALL_CHARACTERS = UPPER_CASE + LOWER_CASE + DIGITS;

    private final int passwordLength = 10;

    public RegistrationHandler(ArrayList<User> users, ArrayList<Admin> admins) {
        this.users = users;
        this.admins = admins;
    }

    public void verifyPhoneNumber(long phoneNumber) throws DuplicatePhoneNumberException {

        if (String.valueOf(phoneNumber).length() != 10) {
            throw new IllegalLengthForNumberException();
        }

        for (Admin admin : admins) {
            if (admin.getPhoneNumber() == phoneNumber) {
                throw new DuplicatePhoneNumberException();
            }
        }

        for (User user : users) {
            if (user.getPhoneNumber() == phoneNumber) {
                throw new DuplicatePhoneNumberException();
            }
        }
    }

    public String generateRandomPassword() {
        final Random random = new Random();
        StringBuilder password = new StringBuilder(passwordLength);

        for (int i = 0; i < passwordLength; i++) {
            // Select a random character from ALL_CHARACTERS
            int randomIndex = random.nextInt(ALL_CHARACTERS.length());
            password.append(ALL_CHARACTERS.charAt(randomIndex));
        }

        return password.toString();
    }
}
