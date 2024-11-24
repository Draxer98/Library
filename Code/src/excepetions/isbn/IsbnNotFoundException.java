package excepetions.isbn;
/**
 * Exception thrown when an ISBN is not found in the system.
 * This exception extends {@link RuntimeException} and is used to indicate that the specified ISBN could not be found.
 * 
 * The exception provides a default error message: "Isbn non trovato" ("ISBN not found").
 */
public class IsbnNotFoundException extends RuntimeException {
    public IsbnNotFoundException() {
        super("Isbn non trovato");
    }
}
