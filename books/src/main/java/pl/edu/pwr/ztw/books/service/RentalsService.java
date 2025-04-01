package pl.edu.pwr.ztw.books.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import pl.edu.pwr.ztw.books.model.Book;
import pl.edu.pwr.ztw.books.model.Reader;
import pl.edu.pwr.ztw.books.model.Rental;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RentalsService implements IRentalsService {
    private static List<Rental> rentalsRepo = new ArrayList<>();

    @Autowired
    @Lazy
    private IBooksService booksService;

    @Autowired
    @Lazy
    private IReadersService readersService;

    @Override
    public Collection<Rental> getRentals() {
        return rentalsRepo;
    }

    @Override
    public Rental getRental(int id) {
        return rentalsRepo.stream()
                .filter(r -> r.getId() == id)
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("Rental not found with ID: " + id));
    }

    @Override
    public void rentBook(Rental rental) {
        Book book = booksService.getBook(rental.getBookId());
        if (book == null) {
            throw new NoSuchElementException("Book not found");
        }
        Reader reader = readersService.getReader(rental.getReaderId());
        if (reader == null) {
            throw new NoSuchElementException("Reader not found");
        }
        int nextId = rentalsRepo.isEmpty() ? 1 : rentalsRepo.get(rentalsRepo.size() - 1).getId() + 1;
        rental.setId(nextId);
        rental.setReturnDate(null);
        rentalsRepo.add(rental);
    }

    @Override
    public void returnBook(int id) {
        Rental rental = getRental(id);
        if (rental != null) {
            rental.setReturnDate(LocalDate.now());
        }
    }

    @Override
    public void updateRental(int id, Rental updatedRental) {
        Rental existingRental = getRental(id);
        if (existingRental != null) {
            Book book = booksService.getBook(updatedRental.getBookId());
            if (book == null) {
                throw new NoSuchElementException("Book not found");
            }
            Reader reader = readersService.getReader(updatedRental.getReaderId());
            if (reader == null) {
                throw new NoSuchElementException("Reader not found");
            }
            existingRental.setBookId(updatedRental.getBookId());
            existingRental.setReaderId(updatedRental.getReaderId());
            existingRental.setRentalDate(updatedRental.getRentalDate());
            existingRental.setReturnDate(updatedRental.getReturnDate());
        } else {
            throw new NoSuchElementException("Rental not found with ID: " + id);
        }
    }

    @Override
    public void deleteRental(int id) {
        Rental rental = rentalsRepo.stream()
                .filter(r -> r.getId() == id)
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("Rental not found with ID: " + id));

        if (rental.getReturnDate() == null) {
            throw new IllegalArgumentException("Cannot delete rental with ongoing rental");
        }

        rentalsRepo.removeIf(r -> r.getId() == id);
    }
}