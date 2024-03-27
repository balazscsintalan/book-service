package hu.danubius.bookservice.service;

import hu.danubius.bookservice.controller.model.CreateAuthorRequest;
import hu.danubius.bookservice.controller.model.UpdateAuthorRequest;
import hu.danubius.bookservice.entity.AuthorEntity;
import hu.danubius.bookservice.error.BookServiceException;
import hu.danubius.bookservice.error.ErrorCodes;
import hu.danubius.bookservice.mapper.AuthorMapper;
import hu.danubius.bookservice.model.Author;
import hu.danubius.bookservice.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    public List<Author> getAuthors() {
        return authorRepository.findAll()
            .stream()
            .map(authorMapper::toDto)
            .toList();
    }

    public Author getAuthorById(Long id) {
        AuthorEntity author = authorRepository
            .findById(id)
            .orElseThrow(() -> new BookServiceException(
                String.format("Author not found by id: %s", id),
                "Author not found",
                ErrorCodes.NOT_FOUND
            ));

        return authorMapper.toDto(author);
    }

    public void createAuthor(CreateAuthorRequest request) {
        AuthorEntity newAuthor = authorMapper.toEntity(request);

        authorRepository.save(newAuthor);
    }

    public void updateAuthor(Long id, UpdateAuthorRequest request) {
        AuthorEntity author = authorRepository
            .findById(id)
            .orElseThrow(() -> new BookServiceException(
                String.format("Author not found by id: %s", id),
                "Author not found",
                ErrorCodes.NOT_FOUND
            ));

        authorMapper.updateEntity(request, author);

        authorRepository.save(author);
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
