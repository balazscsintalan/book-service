package hu.danubius.bookservice.controller.model;

import hu.danubius.bookservice.model.Book;

import java.util.List;

public record GetBooksResponse(
    List<Book> books,
    int totalPages,
    long totalElements
) {
}
