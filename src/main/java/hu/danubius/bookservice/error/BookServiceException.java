package hu.danubius.bookservice.error;

import java.util.UUID;

public class BookServiceException extends RuntimeException {
    private final String errorId = UUID.randomUUID().toString();
    private final String description;
    private final ErrorCodes errorCodes;

    public BookServiceException(Throwable cause, String description, ErrorCodes errorCodes) {
        super(cause);
        this.description = description;
        this.errorCodes = errorCodes;
    }

    public BookServiceException(String message, String description, ErrorCodes errorCodes) {
        super(message);
        this.description = description;
        this.errorCodes = errorCodes;
    }

    public BookServiceException(String description, ErrorCodes errorCodes) {
        this.description = description;
        this.errorCodes = errorCodes;
    }

    public String getErrorId() {
        return errorId;
    }

    public String getDescription() {
        return description;
    }

    public ErrorCodes getErrorCodes() {
        return errorCodes;
    }
}
