package excepetions.isbn;

public class IsbnNotFoundException extends RuntimeException {
    public IsbnNotFoundException() {
        super("Isbn non trovato");
    }
}
