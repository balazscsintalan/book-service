package hu.danubius.bookservice.controller;

import hu.danubius.bookservice.controller.model.CreateBookRequest;
import hu.danubius.bookservice.controller.model.GetBooksResponse;
import hu.danubius.bookservice.controller.model.UpdateBookRequest;
import hu.danubius.bookservice.model.Book;
import hu.danubius.bookservice.service.BookService;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
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


    @PostMapping("/books")
    public void createBook(@RequestBody @Valid CreateBookRequest request) {
        bookService.createBook(request);
    }

    @PutMapping("/books/{id}")
    public void updateBook(
        @PathVariable Long id,
        @RequestBody @Valid UpdateBookRequest request
    ) {
        bookService.updateBook(id, request);
    }
}
