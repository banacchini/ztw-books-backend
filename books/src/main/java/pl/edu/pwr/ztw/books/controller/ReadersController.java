package pl.edu.pwr.ztw.books.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.ztw.books.service.IReadersService;
import pl.edu.pwr.ztw.books.model.Reader;

import java.util.Collection;

@RestController
public class ReadersController {
    @Autowired
    IReadersService readersService;

    @RequestMapping(value = "/get/readers", method = RequestMethod.GET)
    public ResponseEntity<Object> getReaders() {
        Collection<Reader> readers = readersService.getReaders();
        return new ResponseEntity<>(readers, HttpStatus.OK);
    }

    @RequestMapping(value = "/get/reader/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getReader(@PathVariable("id") int id) {
        Reader reader = readersService.getReader(id);
        if (reader == null) {
            return new ResponseEntity<>("Reader not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reader, HttpStatus.OK);
    }

    @RequestMapping(value = "/add/reader", method = RequestMethod.POST)
    public ResponseEntity<Object> addReader(@RequestBody Reader reader) {
        readersService.addReader(reader);
        return new ResponseEntity<>("Reader is added successfully", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/reader/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateReader(@PathVariable("id") int id, @RequestBody Reader reader) {
        readersService.updateReader(id, reader);
        return new ResponseEntity<>("Reader is updated successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/reader/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteReader(@PathVariable("id") int id) {
        readersService.deleteReader(id);
        return new ResponseEntity<>("Reader is deleted successfully", HttpStatus.OK);
    }
}