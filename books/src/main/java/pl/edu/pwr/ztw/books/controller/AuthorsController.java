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
import pl.edu.pwr.ztw.books.model.Author;
import pl.edu.pwr.ztw.books.service.IAuthorsService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/authors")
@Tag(name = "Authors", description = "Endpoints for managing authors")
public class AuthorsController {
    @Autowired
    IAuthorsService authorsService;

    @GetMapping
    @Operation(summary = "Get all authors", description = "Returns a list of all authors.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "400", description = "Page out of range"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Object> getAuthors() {
        return ResponseEntity.ok(authorsService.getAuthors());
    }

    @GetMapping(value="/get/authors")
    @Operation(summary = "Get all authors", description = "Returns a list of all authors.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "400", description = "Page out of range"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Object> getAuthors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size
    ) {
        Map<String, Object> response = authorsService.getAuthors(page, size);

        if (response.containsKey("message")) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get author by ID", description = "Returns details of a specific author.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved author"),
            @ApiResponse(responseCode = "404", description = "Author not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Object> getAuthor(
            @Parameter(description = "ID of the author to retrieve") @PathVariable("id") int id) {
        return ResponseEntity.ok(authorsService.getAuthor(id));
    }

    @RequestMapping(value = "/add/author", method = RequestMethod.POST)
    @Operation(summary = "Add a new author", description = "Creates a new author.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Author created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Object> addAuthor(@Valid @RequestBody Author author) {
        authorsService.addAuthor(author);
        return new ResponseEntity<>(Map.of("message", "Author created successfully"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/author/{id}", method = RequestMethod.PUT)
    @Operation(summary = "Update author details", description = "Updates an existing author's details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Author updated successfully"),
            @ApiResponse(responseCode = "404", description = "Author not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Object> updateAuthor(
            @Parameter(description = "ID of the author to update") @PathVariable("id") int id,
            @Valid @RequestBody Author author) {
        authorsService.updateAuthor(id, author);
        return ResponseEntity.ok(Map.of("message", "Author updated successfully"));
    }

    @RequestMapping(value = "/delete/author/{id}", method = RequestMethod.DELETE)
    @Operation(summary = "Delete author", description = "Deletes an author by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Author deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Author not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Object> deleteAuthor(
            @Parameter(description = "ID of the author to delete") @PathVariable("id") int id) {
        authorsService.deleteAuthor(id);
        return ResponseEntity.ok(Map.of("message", "Author deleted successfully"));
    }
}