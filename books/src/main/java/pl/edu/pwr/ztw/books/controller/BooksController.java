package pl.edu.pwr.ztw.books.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.ztw.books.model.Book;
import pl.edu.pwr.ztw.books.service.IBooksService;

import java.util.Map;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Books", description = "Endpoints for managing books")
public class BooksController {
    @Autowired
    IBooksService booksService;

    @RequestMapping(value = "/get/books", method = RequestMethod.GET)
    @Operation(summary = "Get all books", description = "Returns a list of all books.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Object> getBooks() {
        return ResponseEntity.ok(booksService.getBooks());
    }

    @RequestMapping(value = "/get/book/{id}", method = RequestMethod.GET)
    @Operation(summary = "Get book by ID", description = "Returns details of a specific book.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved book"),
            @ApiResponse(responseCode = "404", description = "Book not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Object> getBook(
            @Parameter(description = "ID of the book to retrieve") @PathVariable("id") int id) {
        return ResponseEntity.ok(booksService.getBook(id));
    }

    @RequestMapping(value = "/add/book", method = RequestMethod.POST)
    @Operation(summary = "Add a new book", description = "Creates a new book.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Book created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Object> addBook(@Valid @RequestBody Book book) {
        booksService.addBook(book);
        return new ResponseEntity<>(Map.of("message", "Book created successfully"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/book/{id}", method = RequestMethod.PUT)
    @Operation(summary = "Update book details", description = "Updates an existing book's details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book updated successfully"),
            @ApiResponse(responseCode = "404", description = "Book not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Object> updateBook(
            @Parameter(description = "ID of the book to update") @PathVariable("id") int id,
            @Valid @RequestBody Book book) {
        booksService.updateBook(id, book);
        return ResponseEntity.ok(Map.of("message", "Book updated successfully"));
    }

    @RequestMapping(value = "/delete/book/{id}", method = RequestMethod.DELETE)
    @Operation(summary = "Delete book", description = "Deletes a book by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Book not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Object> deleteBook(
            @Parameter(description = "ID of the book to delete") @PathVariable("id") int id) {
        booksService.deleteBook(id);
        return ResponseEntity.ok(Map.of("message", "Book deleted successfully"));
    }
}
