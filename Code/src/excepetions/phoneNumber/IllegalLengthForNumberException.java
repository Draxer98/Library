package excepetions.phoneNumber;

/**
 * Exception thrown when attempting to add a phone
 * number that has a length different from 10.
 */
public class IllegalLengthForNumberException extends RuntimeException {
    public IllegalLengthForNumberException() {
        super("Lunghezza del numero di telefono non valida.");
    }
}
