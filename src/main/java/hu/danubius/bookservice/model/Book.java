package hu.danubius.bookservice.model;

import java.time.LocalDate;

public record Book(
    String title,
    String isbn,
    Integer totalPages,
    LocalDate publishedDate
) {
}
