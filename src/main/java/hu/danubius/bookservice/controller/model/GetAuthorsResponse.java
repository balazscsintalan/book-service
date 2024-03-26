package hu.danubius.bookservice.controller.model;

import hu.danubius.bookservice.model.Author;

import java.util.List;

public record GetAuthorsResponse(
    List<Author> authors
) {
}
