package rent.common;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable ex) {
        super(message, ex);
    }
}
