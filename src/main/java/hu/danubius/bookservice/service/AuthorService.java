package hu.danubius.bookservice.service;

import hu.danubius.bookservice.controller.model.CreateAuthorRequest;
import hu.danubius.bookservice.entity.AuthorEntity;
import hu.danubius.bookservice.model.Author;
import hu.danubius.bookservice.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAuthors() {
        return authorRepository.findAll()
            .stream()
            .map(authorEntity -> new Author(authorEntity.getName()))
            .toList();
    }

    public Author getAuthorById(Long id) {
        AuthorEntity author = authorRepository
            .findById(id)
            .orElseThrow(() -> new IllegalStateException("Author not found by id"));

        return new Author(author.getName());
    }

    public void createAuthor(CreateAuthorRequest request) {
        AuthorEntity newAuthor = new AuthorEntity();
        newAuthor.setName(request.name());

        authorRepository.save(newAuthor);
    }
}
