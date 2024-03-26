package hu.danubius.bookservice.service;

import hu.danubius.bookservice.model.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    public List<Author> getAuthors() {
        return List.of(new Author("Brandon Sanderson"));
    }

    public Author getAuthorById(Long id) {
        return new Author("Brandon Sanderson");
    }
}
