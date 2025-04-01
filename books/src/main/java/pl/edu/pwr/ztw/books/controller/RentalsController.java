package pl.edu.pwr.ztw.books.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
    public ResponseEntity<Object> getRentals() {
        return ResponseEntity.ok(rentalsService.getRentals());
    }

    @RequestMapping(value = "/get/rental/{id}", method = RequestMethod.GET)
    @Operation(summary = "Get rental by ID", description = "Returns details of a specific rental.")
    public ResponseEntity<Object> getRental(
            @Parameter(description = "ID of the rental to retrieve") @PathVariable("id") int id) {
        return ResponseEntity.ok(rentalsService.getRental(id));
    }

    @RequestMapping(value = "/rent/book", method = RequestMethod.POST)
    @Operation(summary = "Rent a book", description = "Registers a new book rental.")
    public ResponseEntity<Object> rentBook(@Valid @RequestBody Rental rental) {
        rentalsService.rentBook(rental);
        return new ResponseEntity<>(Map.of("message", "Book rented successfully"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/return/book/{id}", method = RequestMethod.PUT)
    @Operation(summary = "Return a rented book", description = "Marks a rented book as returned.")
    public ResponseEntity<Object> returnBook(
            @Parameter(description = "ID of the rental to update") @PathVariable("id") int id) {
        rentalsService.returnBook(id);
        return ResponseEntity.ok(Map.of("message", "Book returned successfully"));
    }
}