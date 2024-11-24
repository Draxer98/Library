package excepetions.isbn;
/**
 * Exception thrown when an ISBN has an invalid length.
 * This exception extends {@link RuntimeException} and is used to indicate that the length of the provided ISBN
 * does not match the expected length (which is 13 characters).
 * 
 * The exception provides a default error message: "Lunghezza dell'isbn non valida. La corretta e' 13."
 * ("Invalid ISBN length. The correct length is 13").
 */
public class IllegalLengthForIsbnException extends RuntimeException {
    public IllegalLengthForIsbnException() {
        super("Lunghezza dell'isbn non valida. La corretta e' 13.");
    }
}
