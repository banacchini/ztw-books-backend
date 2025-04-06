package pl.edu.pwr.ztw.books.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pwr.ztw.books.service.AuthorsService;
import pl.edu.pwr.ztw.books.service.BooksService;
import pl.edu.pwr.ztw.books.service.ReadersService;
import pl.edu.pwr.ztw.books.service.RentalsService;

import java.util.Map;

@RestController
@RequestMapping("/api/stats")
public class StatsController {

    @Autowired
    private BooksService booksService;

    @Autowired
    private AuthorsService authorsService;

    @Autowired
    private ReadersService readersService;

    @Autowired
    private RentalsService rentalsService;

    @GetMapping
    public ResponseEntity<Map<String, Integer>> getStats() {
        Map<String, Integer> stats = Map.of(
                "booksCount", booksService.getBooksCount(),
                "authorsCount", authorsService.getAuthorsCount(),
                "readersCount", readersService.getReadersCount(),
                "ongoingRentalsCount", rentalsService.getOngoingRentalsCount()
        );
        return ResponseEntity.ok(stats);
    }
}
