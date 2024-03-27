package hu.danubius.bookservice.error;

public record ErrorResponse(
    ErrorCodes code,
    String description,
    String errorId
) {
}
