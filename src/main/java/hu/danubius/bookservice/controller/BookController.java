package hu.danubius.bookservice.controller;

import hu.danubius.bookservice.controller.model.CreateBookRequest;
import hu.danubius.bookservice.controller.model.GetBooksResponse;
import hu.danubius.bookservice.controller.model.UpdateBookRequest;
import hu.danubius.bookservice.model.Book;
import hu.danubius.bookservice.service.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public GetBooksResponse getBooks(
        @RequestParam @Min(0) int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        return bookService.getBooks(page, size);
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }


    @PostMapping
    public void createBook(@RequestBody @Valid CreateBookRequest request) {
        bookService.createBook(request);
    }

    @PutMapping("/{id}")
    public void updateBook(
        @PathVariable Long id,
        @RequestBody @Valid UpdateBookRequest request
    ) {
        bookService.updateBook(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}
