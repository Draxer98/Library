package excepetions.isbn;
/**
 * Exception thrown when attempting to add a book with an ISBN that is already present.
 * This exception extends {@link RuntimeException} and is used to indicate that an ISBN 
 * already exists in the system, preventing the addition of duplicate entries.
 * 
 * The exception provides a default error message: "Isbn gia' presente." ("ISBN already present").
 */
public class DuplicateIsbnException extends RuntimeException {
    public DuplicateIsbnException() {
        super("Isbn gia' presente.");
    }
}
