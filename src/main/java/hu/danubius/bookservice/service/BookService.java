package hu.danubius.bookservice.service;

import hu.danubius.bookservice.controller.model.CreateBookRequest;
import hu.danubius.bookservice.controller.model.UpdateBookRequest;
import hu.danubius.bookservice.entity.BookEntity;
import hu.danubius.bookservice.model.Book;
import hu.danubius.bookservice.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() {
        List<BookEntity> bookEntities = bookRepository.findAll();

//        List<Book> books = new ArrayList<>();
//
//        for (BookEntity bookEntity : bookEntities) {
//            Book newBook = new Book(bookEntity.getTitle());
//            books.add(newBook);
//        }

        return bookEntities.stream()
//            .filter(bookEntity -> bookEntity.getIsbn().equals("3afasda"))
            .map(bookEntity -> new Book(bookEntity.getTitle()))
            .toList();
    }

    public Book getBookById(Long id) {
        Optional<BookEntity> book = bookRepository.findById(id);
//        Optional<BookEntity> book = bookRepository.findByIsbn("3afasda");

//        if (book.isPresent()) {
//            BookEntity bookEntity = book.get();
//        } else {
//            throw new IllegalStateException("Book by id not found");
//        }

        BookEntity bookEntity = book
            .orElseThrow(() -> new IllegalStateException("Book by id not found"));

        return new Book(bookEntity.getTitle());
    }

    public void createBook(CreateBookRequest request) {
        BookEntity newBook = new BookEntity();
        newBook.setIsbn(request.isbn());
        newBook.setTitle(request.title());
        newBook.setPublishedDate(request.publishedDate());
        newBook.setTotalPages(request.totalPages());

        bookRepository.save(newBook);
    }

    public void updateBook(Long id, UpdateBookRequest request) {
        Optional<BookEntity> book = bookRepository.findById(id);

        BookEntity bookEntity = book
            .orElseThrow(() -> new IllegalStateException("Book by id not found"));

        bookEntity.setTotalPages(request.totalPages());
        bookEntity.setTitle(request.title());
        bookEntity.setPublishedDate(request.publishedDate());
        bookEntity.setIsbn(request.isbn());

        bookRepository.save(bookEntity);
    }
}
