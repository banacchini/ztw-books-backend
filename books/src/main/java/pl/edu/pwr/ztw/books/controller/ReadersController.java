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


    @GetMapping
    @Operation(summary = "Get all readers", description = "Returns a list of all readers.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Object> getReaders() {
        return ResponseEntity.ok(readersService.getReaders());
    }

    @RequestMapping(value = "/get/readers", method = RequestMethod.GET)
    @Operation(summary = "Get all readers", description = "Returns a list of all readers.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "400", description = "Page out of range"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Object> getReaders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size
    ) {
        Map<String, Object> response = readersService.getReaders(page, size);

        if (response.containsKey("message")) {
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/get/reader/{id}", method = RequestMethod.GET)
    @Operation(summary = "Get reader by ID", description = "Returns details of a specific reader.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved reader"),
            @ApiResponse(responseCode = "404", description = "Reader not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Object> getReader(
            @Parameter(description = "ID of the reader to retrieve") @PathVariable("id") int id) {
        return ResponseEntity.ok(readersService.getReader(id));
    }

    @RequestMapping(value = "/add/reader", method = RequestMethod.POST)
    @Operation(summary = "Add a new reader", description = "Creates a new reader.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reader created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Object> addReader(@Valid @RequestBody Reader reader) {
        readersService.addReader(reader);
        return new ResponseEntity<>(Map.of("message", "Reader added successfully"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/reader/{id}", method = RequestMethod.PUT)
    @Operation(summary = "Update reader details", description = "Updates an existing reader's details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reader updated successfully"),
            @ApiResponse(responseCode = "404", description = "Reader not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Object> updateReader(
            @Parameter(description = "ID of the reader to update") @PathVariable("id") int id,
            @Valid @RequestBody Reader reader) {
        readersService.updateReader(id, reader);
        return ResponseEntity.ok(Map.of("message", "Reader updated successfully"));
    }

    @RequestMapping(value = "/delete/reader/{id}", method = RequestMethod.DELETE)
    @Operation(summary = "Delete reader", description = "Deletes a reader by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reader deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Reader not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Object> deleteReader(
            @Parameter(description = "ID of the reader to delete") @PathVariable("id") int id) {
        readersService.deleteReader(id);
        return ResponseEntity.ok(Map.of("message", "Reader deleted successfully"));
    }
}