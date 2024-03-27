package hu.danubius.bookservice.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record Book(

    String title,
    String isbn,
    Integer totalPages,
    LocalDate publishedDate
) {
}
