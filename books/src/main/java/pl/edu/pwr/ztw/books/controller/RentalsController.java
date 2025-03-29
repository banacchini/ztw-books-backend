package pl.edu.pwr.ztw.books.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.ztw.books.service.IRentalsService;
import pl.edu.pwr.ztw.books.model.Rental;

import java.util.Collection;

@RestController
public class RentalsController {
    @Autowired
    IRentalsService rentalsService;

    @RequestMapping(value = "/get/rentals", method = RequestMethod.GET)
    public ResponseEntity<Object> getRentals() {
        Collection<Rental> rentals = rentalsService.getRentals();
        return new ResponseEntity<>(rentals, HttpStatus.OK);
    }

    @RequestMapping(value = "/get/rental/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getRental(@PathVariable("id") int id) {
        Rental rental = rentalsService.getRental(id);
        if (rental == null) {
            return new ResponseEntity<>("Rental not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(rental, HttpStatus.OK);
    }

    @RequestMapping(value = "/rent/book", method = RequestMethod.POST)
    public ResponseEntity<Object> rentBook(@RequestBody Rental rental) {
        rentalsService.rentBook(rental);
        return new ResponseEntity<>("Book is rented successfully", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/return/book/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> returnBook(@PathVariable("id") int id) {
        rentalsService.returnBook(id);
        return new ResponseEntity<>("Book is returned successfully", HttpStatus.OK);
    }
}