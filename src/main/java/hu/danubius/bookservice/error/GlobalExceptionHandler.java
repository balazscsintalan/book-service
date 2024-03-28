package hu.danubius.bookservice.error;

import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.UUID;
import java.util.stream.Collectors;

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

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity handleAccessDeniedException(AccessDeniedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
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

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingRequestParameter(
        MissingServletRequestParameterException e
    ) {
        String errorId = UUID.randomUUID().toString();
        log.error("MissingServletParameterException with errorId: {}", errorId, e);

        return ResponseEntity
            .status(ErrorCodes.VALIDATION_ERROR.getHttpStatus())
            .body(new ErrorResponse(
                ErrorCodes.VALIDATION_ERROR,
                e.getBody().getDetail(),
                errorId
            ));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException e) {
        String errorId = UUID.randomUUID().toString();
        log.error("ConstraintViolationException with errorId: {}", errorId, e);

        var constraintViolations = e.getConstraintViolations().stream()
            .map(it -> String.format("%s, %s", it.getPropertyPath().toString(), it.getMessage()))
            .collect(Collectors.joining(", "));

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new ErrorResponse(
                ErrorCodes.VALIDATION_ERROR,
                constraintViolations,
                errorId
            ));
    }
}
