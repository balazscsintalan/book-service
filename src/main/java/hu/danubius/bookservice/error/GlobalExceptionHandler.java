package hu.danubius.bookservice.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.UUID;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BookServiceException.class)
    public ResponseEntity<ErrorResponse>
    handleBookServiceException(BookServiceException e) {
        log.error("BookServiceException with errorId: {}", e.getErrorId(), e);

        return ResponseEntity
            .status(e.getErrorCodes().getHttpStatus())
            .body(new ErrorResponse(
                e.getErrorCodes(),
                e.getDescription(),
                e.getErrorId())
            );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        String errorId = UUID.randomUUID().toString();

        log.error("Unhandled exception occurred, with errorId: {}", errorId, e);

        return ResponseEntity
            .status(ErrorCodes.INTERNAL_ERROR.getHttpStatus())
            .body(new ErrorResponse(
                ErrorCodes.INTERNAL_ERROR,
                "Internal error",
                errorId
            ));
    }
}
