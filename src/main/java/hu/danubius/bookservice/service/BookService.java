package hu.danubius.bookservice.service;

import hu.danubius.bookservice.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    public List<Book> getBooks() {
        return List.of(new Book("The Way of Kings"));
    }

    public Book getBookById(Long id) {
        return new Book("Words of Radiance");
    }
}
