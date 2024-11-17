package excepetions.isbn;

public class DuplicateIsbnException extends RuntimeException {
    public DuplicateIsbnException() {
        super("Isbn gia' presente.");
    }
}
