package hu.danubius.bookservice.controller.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record UpdateBookRequest(
    @NotNull
    @NotBlank
    String title,
    @NotNull
    @NotBlank
    String isbn,
    @NotNull
    @Min(value = 1)
    Integer totalPages,
    @PastOrPresent
    LocalDate publishedDate
) {
}
