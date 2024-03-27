package hu.danubius.bookservice.controller.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateAuthorRequest(
    @NotNull
    @NotBlank
    String name
) {
}
