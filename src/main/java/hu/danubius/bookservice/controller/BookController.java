package hu.danubius.bookservice.controller;


import hu.danubius.bookservice.controller.model.CreateBookRequest;
import hu.danubius.bookservice.controller.model.GetBooksResponse;
import hu.danubius.bookservice.controller.model.UpdateBookRequest;
import hu.danubius.bookservice.error.ErrorResponse;
import hu.danubius.bookservice.model.Book;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/books")
@Validated
@Tags(value = @Tag(name = "book-service"))
public interface BookController {

    @GetMapping
    @Operation(summary = "Get all the books, it is pageagle.")
//    @ApiResponse(
//        responseCode = "200",
//        content = @Content(
//            schema = @Schema(implementation = GetBooksResponse.class)
//        )
//    )
    GetBooksResponse getBooks(
        @Parameter(description = "Which page to return, starts from 0")
        @RequestParam @Min(0) int page,
        @Parameter(description = "Size of the page, defaultValue: 10")
        @RequestParam(defaultValue = "10") int size
    );

    @GetMapping("/{id}")
    @Operation(summary = "Get book by id")
    @ApiResponse(
        responseCode = "404",
        description = "Book with id was not found",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = ErrorResponse.class)
        )
    )
    Book getBookById(@PathVariable Long id);

    @PostMapping
    void createBook(
        @RequestBody @Valid CreateBookRequest request
    );

    void updateBook(
        @PathVariable Long id,
        @RequestBody @Valid UpdateBookRequest request
    );

    @DeleteMapping("/{id}")
    void deleteBook(@PathVariable Long id);
}
