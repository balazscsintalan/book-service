package hu.danubius.bookservice.controller;

import hu.danubius.bookservice.controller.model.CreateAuthorRequest;
import hu.danubius.bookservice.controller.model.GetAuthorsResponse;
import hu.danubius.bookservice.model.Author;
import hu.danubius.bookservice.service.AuthorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public GetAuthorsResponse getAuthors() {
        return new GetAuthorsResponse(
            authorService.getAuthors()
        );
    }

    @GetMapping("/authors/{id}")
    public Author getAuthorById(@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }

    @PostMapping("/authors")
    public void createAuthor(@RequestBody CreateAuthorRequest request) {
        authorService.createAuthor(request);
    }
}
