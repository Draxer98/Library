package handler;

import excepetions.phoneNumber.DuplicatePhoneNumberException;
import excepetions.phoneNumber.IllegalLengthForNumberException;
import libraryMembers.Admin;
import libraryMembers.LibraryMember;
import libraryMembers.User;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * This class takes care of the registration of a new user.
 */
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

    /**
     * This method check if the number is unique within all the user and admins.
     *
     * @param phoneNumber
     * @throws DuplicatePhoneNumberException If the phoneNumber is a phone number of another admin or user.
     * @throws IllegalLengthForNumberException If the phoneNumber has a length different from 10.
     */
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

    /**
     * This method generate a random password.
     *
     * @return a random password
     */
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

    /**
     * This method ask the user the insert the name, surname and phone number.
     * If all the data are correct then return a new {@link LibraryMember}.
     *
     * @param scanner Scanner to read the data.
     * @return a new {@link LibraryMember} if all the data are correct.
     */
    public LibraryMember takeBaseInfoOfUser(Scanner scanner) {

        String name, surname;

        // name
        do {
            System.out.println("Inserisci il nome: ");
            name = scanner.nextLine();
        } while (name.isEmpty());

        // surname
        do {
            System.out.println("Inserisci il congome: ");
            surname = scanner.nextLine();
        } while (surname.isEmpty());

        long phoneNumber;

        // phone Number
        while (true) {
            try {
                System.out.println("Inserisci il numero di telefono: ");
                phoneNumber = Long.parseLong(scanner.nextLine());

                // verify
                verifyPhoneNumber(phoneNumber);

                break;
            } catch (IllegalLengthForNumberException | DuplicatePhoneNumberException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Il valore deve essere solo numerico");
            }
        }

        return new LibraryMember(name, surname, phoneNumber);
    }
}
