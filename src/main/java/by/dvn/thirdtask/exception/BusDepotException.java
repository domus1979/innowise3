package by.dvn.secondtask.exception;

public class TextProcessingException extends Exception {
    public TextProcessingException() {
        super();
    }

    public TextProcessingException(String message) {
        super(message);
    }

    public TextProcessingException(String message, Throwable cause) {
        super(message, cause);
    }

    public TextProcessingException(Throwable cause) {
        super(cause);
    }
}
