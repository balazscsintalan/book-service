package hu.danubius.bookservice.controller;

import hu.danubius.bookservice.controller.model.CreateAuthorRequest;
import hu.danubius.bookservice.controller.model.GetAuthorsResponse;
import hu.danubius.bookservice.controller.model.UpdateAuthorRequest;
import hu.danubius.bookservice.model.Author;
import hu.danubius.bookservice.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public GetAuthorsResponse getAuthors() {
        return new GetAuthorsResponse(
            authorService.getAuthors()
        );
    }

    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }

    @PostMapping
    public void createAuthor(@RequestBody @Valid CreateAuthorRequest request) {
        authorService.createAuthor(request);
    }

    @PutMapping("/{id}")
    public void updateAuthor(
        @PathVariable Long id,
        @RequestBody @Valid UpdateAuthorRequest request
    ) {
        authorService.updateAuthor(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }
}
