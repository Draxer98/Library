package excepetions.phoneNumber;

/**
 * Exception thrown when attempting to add a phone number that already exists.
 * This ensures that each phone number is unique within the system.
 */
public class DuplicatePhoneNumberException extends RuntimeException {
    public DuplicatePhoneNumberException() {
        super("Il numbero di telefono deve essere univoco");
    }
}
