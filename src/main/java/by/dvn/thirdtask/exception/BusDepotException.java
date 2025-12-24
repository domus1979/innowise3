package by.dvn.thirdtask.exception;

public class BusDepotException extends Exception {
    public BusDepotException() {
        super();
    }

    public BusDepotException(String message) {
        super(message);
    }

    public BusDepotException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusDepotException(Throwable cause) {
        super(cause);
    }
}
