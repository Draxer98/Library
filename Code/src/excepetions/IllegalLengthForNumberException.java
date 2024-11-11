package excepetions;

public class IllegalLengthForNumberException extends RuntimeException {
    public IllegalLengthForNumberException() {
        super("Lunghezza del numero di telefono non valida.");
    }
}
