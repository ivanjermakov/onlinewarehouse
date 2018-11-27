package by.itechart.exception;

public class NotFoundEntityException extends RuntimeException {

    public NotFoundEntityException(String message) {
        super(message + " NOT FOUND");
    }
}
