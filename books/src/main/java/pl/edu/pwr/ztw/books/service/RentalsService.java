package pl.edu.pwr.ztw.books.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pwr.ztw.books.model.Book;
import pl.edu.pwr.ztw.books.model.Rental;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class RentalsService implements IRentalsService {
    private static List<Rental> rentalsRepo = new ArrayList<>();

    @Autowired
    private BooksService booksService;

    @Override
    public Collection<Rental> getRentals() {
        return rentalsRepo;
    }

    @Override
    public Rental getRental(int id) {
        return rentalsRepo.stream()
                .filter(r -> r.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void rentBook(Rental rental) {
        Book book = booksService.getBook(rental.getBook().getId());
        if (book == null) {
            throw new IllegalArgumentException("Book not found");
        }
        rentalsRepo.add(rental);
    }

    @Override
    public void returnBook(int id) {
        Rental rental = getRental(id);
        if (rental != null) {
            rental.setReturnDate(LocalDate.now());
        }
    }
}