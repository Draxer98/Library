package excepetions;

public class DuplicatePhoneNumberException extends RuntimeException {
    public DuplicatePhoneNumberException() {
        super("Il numbero di telfono deve essere univoco");
    }
}
