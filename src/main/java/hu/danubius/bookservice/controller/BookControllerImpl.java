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
public class BookControllerImpl
    implements BookController {

    private final BookService bookService;

    public BookControllerImpl(BookService bookService) {
        this.bookService = bookService;
    }


    @Override
    public GetBooksResponse getBooks(
        int page, int size
    ) {
        return bookService.getBooks(page, size);
    }

    @Override
    public Book getBookById(Long id) {
        return bookService.getBookById(id);
    }

    @Override
    public void createBook(CreateBookRequest request) {
        bookService.createBook(request);
    }


    @Override
    public void updateBook(
        Long id,
        UpdateBookRequest request
    ) {
        bookService.updateBook(id, request);
    }

    @Override
    public void deleteBook(Long id) {
        bookService.deleteBook(id);
    }
}
