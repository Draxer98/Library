package excepetions.isbn;

public class IllegalLengthForIsbnException extends RuntimeException {
    public IllegalLengthForIsbnException() {
        super("Lunghezza dell'isbn non valida. La corretta e' 13.");
    }
}
