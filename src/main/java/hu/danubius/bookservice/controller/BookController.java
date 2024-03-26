package hu.danubius.bookservice.controller;

import hu.danubius.bookservice.controller.model.GetBooksResponse;
import hu.danubius.bookservice.model.Book;
import hu.danubius.bookservice.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public GetBooksResponse getBooks() {
        return new GetBooksResponse(bookService.getBooks());
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

}
