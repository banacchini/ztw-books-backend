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
import pl.edu.pwr.ztw.books.service.IRentalsService;
import pl.edu.pwr.ztw.books.model.Rental;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/api/rentals")
@Tag(name = "Rentals", description = "Endpoints for managing book rentals")
public class RentalsController {
    @Autowired
    IRentalsService rentalsService;

    @RequestMapping(value = "/get/rentals", method = RequestMethod.GET)
    @Operation(summary = "Get all rentals", description = "Returns a list of all rentals.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Object> getRentals() {
        return ResponseEntity.ok(rentalsService.getRentals());
    }

    @RequestMapping(value = "/get/rental/{id}", method = RequestMethod.GET)
    @Operation(summary = "Get rental by ID", description = "Returns details of a specific rental.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved rental"),
            @ApiResponse(responseCode = "404", description = "Rental not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Object> getRental(
            @Parameter(description = "ID of the rental to retrieve") @PathVariable("id") int id) {
        return ResponseEntity.ok(rentalsService.getRental(id));
    }

    @RequestMapping(value = "/rent/book", method = RequestMethod.POST)
    @Operation(summary = "Rent a book", description = "Registers a new book rental.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Book rented successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Object> rentBook(@Valid @RequestBody Rental rental) {
        rentalsService.rentBook(rental);
        return new ResponseEntity<>(Map.of("message", "Book rented successfully"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/return/book/{id}", method = RequestMethod.PUT)
    @Operation(summary = "Return a rented book", description = "Marks a rented book as returned.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book returned successfully"),
            @ApiResponse(responseCode = "404", description = "Rental not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Object> returnBook(
            @Parameter(description = "ID of the rental to update") @PathVariable("id") int id) {
        rentalsService.returnBook(id);
        return ResponseEntity.ok(Map.of("message", "Book returned successfully"));
    }

    @RequestMapping(value = "/update/rental/{id}", method = RequestMethod.PUT)
    @Operation(summary = "Update a rental", description = "Updates an existing rental.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental updated successfully"),
            @ApiResponse(responseCode = "404", description = "Rental not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Object> updateRental(
            @Parameter(description = "ID of the rental to update") @PathVariable("id") int id,
            @Valid @RequestBody Rental updatedRental) {
        rentalsService.updateRental(id, updatedRental);
        return ResponseEntity.ok(Map.of("message", "Rental updated successfully"));
    }

    @RequestMapping(value = "/delete/rental/{id}", method = RequestMethod.DELETE)
    @Operation(summary = "Delete a rental", description = "Deletes an existing rental.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Rental not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Object> deleteRental(
            @Parameter(description = "ID of the rental to delete") @PathVariable("id") int id) {
        rentalsService.deleteRental(id);
        return ResponseEntity.ok(Map.of("message", "Rental deleted successfully"));
    }
}