package hu.danubius.bookservice.controller.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateAuthorRequest(
    @NotNull
    @NotBlank
    String name
) {
}
