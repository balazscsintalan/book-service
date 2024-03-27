package hu.danubius.bookservice.service;

import hu.danubius.bookservice.controller.model.CreateBookRequest;
import hu.danubius.bookservice.controller.model.UpdateBookRequest;
import hu.danubius.bookservice.entity.BookEntity;
import hu.danubius.bookservice.error.BookServiceException;
import hu.danubius.bookservice.error.ErrorCodes;
import hu.danubius.bookservice.mapper.BookMapper;
import hu.danubius.bookservice.model.Book;
import hu.danubius.bookservice.repository.BookRepository;
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

    public List<Book> getBooks() {
        List<BookEntity> bookEntities = bookRepository.findAll();

        return bookEntities.stream()
//            .filter(bookEntity -> bookEntity.getIsbn().equals("3afasda"))
            .map(bookMapper::toDto)
            .toList();
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
