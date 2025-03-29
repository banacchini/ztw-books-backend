package pl.edu.pwr.ztw.books.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.ztw.books.model.Author;
import pl.edu.pwr.ztw.books.service.IAuthorsService;

@RestController
public class AuthorsController {
    @Autowired
    IAuthorsService authorsService;

    @RequestMapping(value = "/get/authors", method = RequestMethod.GET)
    public ResponseEntity<Object> getAuthors() {
        return new ResponseEntity<>(authorsService.getAuthors(), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/author/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getAuthor(@PathVariable("id") int id) {
        return new ResponseEntity<>(authorsService.getAuthor(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/add/author", method = RequestMethod.POST)
    public ResponseEntity<Object> addAuthor(@Valid @RequestBody Author author) {
        authorsService.addAuthor(author);
        return new ResponseEntity<>("Author is created successfully", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/author/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateAuthor(@PathVariable("id") int id, @Valid @RequestBody Author author) {
        authorsService.updateAuthor(id, author);
        return new ResponseEntity<>("Author is updated successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/author/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteAuthor(@PathVariable("id") int id) {
        authorsService.deleteAuthor(id);
        return new ResponseEntity<>("Author is deleted successfully", HttpStatus.OK);
    }
}