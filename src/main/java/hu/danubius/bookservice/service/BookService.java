package hu.danubius.bookservice.service;

import hu.danubius.bookservice.controller.model.CreateBookRequest;
import hu.danubius.bookservice.controller.model.GetBooksResponse;
import hu.danubius.bookservice.controller.model.UpdateBookRequest;
import hu.danubius.bookservice.entity.BookEntity;
import hu.danubius.bookservice.error.BookServiceException;
import hu.danubius.bookservice.error.ErrorCodes;
import hu.danubius.bookservice.mapper.BookMapper;
import hu.danubius.bookservice.model.Book;
import hu.danubius.bookservice.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public GetBooksResponse getBooks(int pageNumber, int size) {
        Pageable page = PageRequest.of(pageNumber, size);

        Page<BookEntity> bookEntityPage = bookRepository.findAll(page);

        List<Book> books = bookEntityPage.getContent().stream()
            .map(bookMapper::toDto)
            .toList();

        return new GetBooksResponse(
            books,
            bookEntityPage.getTotalPages(),
            bookEntityPage.getTotalElements()
        );
    }

    public Book getBookById(Long id) {
        Optional<BookEntity> book = bookRepository.findById(id);

        BookEntity bookEntity = book
            .orElseThrow(() -> new BookServiceException(
                String.format("Book not found by id: %s", id),
                "Book not found",
                ErrorCodes.NOT_FOUND
            ));

        return bookMapper.toDto(bookEntity);
    }

    public void createBook(CreateBookRequest request) {
        BookEntity newBook = bookMapper.toEntity(request);
        bookRepository.save(newBook);
    }

    public void updateBook(Long id, UpdateBookRequest request) {
        Optional<BookEntity> book = bookRepository.findById(id);

        BookEntity bookEntity = book
            .orElseThrow(() -> new BookServiceException(
                String.format("Book not found by id: %s", id),
                "Book not found",
                ErrorCodes.NOT_FOUND
            ));

        bookMapper.updateEntity(request, bookEntity);
        bookRepository.save(bookEntity);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
