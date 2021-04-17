package spring.jsonlab.exception;

public class InvalidDataEntityException extends RuntimeException {
    public InvalidDataEntityException() {
    }

    public InvalidDataEntityException(String message) {
        super(message);
    }

    public InvalidDataEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDataEntityException(Throwable cause) {
        super(cause);
    }
}
