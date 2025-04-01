package pl.edu.pwr.ztw.books.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.ztw.books.service.IReadersService;
import pl.edu.pwr.ztw.books.model.Reader;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/api/readers")
@Tag(name = "Readers", description = "Endpoints for managing readers")
public class ReadersController {
    @Autowired
    IReadersService readersService;

    @RequestMapping(value = "/get/readers", method = RequestMethod.GET)
    @Operation(summary = "Get all readers", description = "Returns a list of all readers.")
    public ResponseEntity<Object> getReaders() {
        return ResponseEntity.ok(readersService.getReaders());
    }

    @RequestMapping(value = "/get/reader/{id}", method = RequestMethod.GET)
    @Operation(summary = "Get reader by ID", description = "Returns details of a specific reader.")
    public ResponseEntity<Object> getReader(
            @Parameter(description = "ID of the reader to retrieve") @PathVariable("id") int id) {
        return ResponseEntity.ok(readersService.getReader(id));
    }

    @RequestMapping(value = "/add/reader", method = RequestMethod.POST)
    @Operation(summary = "Add a new reader", description = "Creates a new reader.")
    public ResponseEntity<Object> addReader(@Valid @RequestBody Reader reader) {
        readersService.addReader(reader);
        return new ResponseEntity<>(Map.of("message", "Reader added successfully"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/reader/{id}", method = RequestMethod.PUT)
    @Operation(summary = "Update reader details", description = "Updates an existing reader's details.")
    public ResponseEntity<Object> updateReader(
            @Parameter(description = "ID of the reader to update") @PathVariable("id") int id,
            @Valid @RequestBody Reader reader) {
        readersService.updateReader(id, reader);
        return ResponseEntity.ok(Map.of("message", "Reader updated successfully"));
    }

    @RequestMapping(value = "/delete/reader/{id}", method = RequestMethod.DELETE)
    @Operation(summary = "Delete reader", description = "Deletes a reader by ID.")
    public ResponseEntity<Object> deleteReader(
            @Parameter(description = "ID of the reader to delete") @PathVariable("id") int id) {
        readersService.deleteReader(id);
        return ResponseEntity.ok(Map.of("message", "Reader deleted successfully"));
    }
}