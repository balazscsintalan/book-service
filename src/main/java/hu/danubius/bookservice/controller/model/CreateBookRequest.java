package hu.danubius.bookservice.controller.model;

import java.time.LocalDate;

public record CreateBookRequest(
    String title,
    String isbn,
    Integer totalPages,
    LocalDate publishedDate
) {
}
