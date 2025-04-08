package pl.edu.pwr.ztw.books.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import pl.edu.pwr.ztw.books.model.Author;
import pl.edu.pwr.ztw.books.model.Book;
import pl.edu.pwr.ztw.books.model.Reader;
import pl.edu.pwr.ztw.books.model.Rental;

import java.time.LocalDate;
import java.util.*;

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

    static {
        rentalsRepo.add(new Rental(1, 2, 4, LocalDate.of(2025, 3, 23), null));
        rentalsRepo.add(new Rental(2, 1, 5, LocalDate.of(2025, 3, 23), LocalDate.now()));
        rentalsRepo.add(new Rental(3, 10, 4, LocalDate.of(2025, 3, 23), null));
        rentalsRepo.add(new Rental(4, 5, 3, LocalDate.of(2025, 3, 23), null));
        rentalsRepo.add(new Rental(5, 14, 3, LocalDate.of(2025, 3, 23), LocalDate.of(2025, 3, 30)));
        rentalsRepo.add(new Rental(6, 11, 4, LocalDate.of(2025, 3, 23), null));
        rentalsRepo.add(new Rental(7, 14, 5, LocalDate.of(2025, 4, 1), null));
    }

    @Override
    public Map<String, Object> getRentals(int page, int size) {
        int totalRentals = rentalsRepo.size();
        int totalPages = (int) Math.ceil((double) totalRentals / size);
        int start = page * size;
        int end = Math.min(start + size, totalRentals);

        if (start > totalRentals) {
            return Map.of("message", "Page out of range");
        }

        List<Rental> rentals = rentalsRepo.subList(start, end);

        Map<String, Object> response = new HashMap<>();
        response.put("rentals", rentals);
        response.put("currentPage", page);
        response.put("totalPages", totalPages);

        return response;
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
        if (isBookRented(book.getId())){
            throw new IllegalArgumentException("Book is already rented");
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

    @Override
    public boolean isBookRented(int bookId){
        return rentalsRepo.stream()
                .anyMatch(r -> r.getBookId() == bookId && r.getReturnDate() == null);
    }

    @Override
    public int getOngoingRentalsCount() {
        return (int) rentalsRepo.stream()
                .filter(r -> r.getReturnDate() == null)
                .count();
    }

    @Override
    public Collection<Rental> getRentalsByReader(int readerId) {
        return rentalsRepo.stream()
                .filter(r -> r.getReaderId() == readerId)
                .toList();
    }

    @Override
    public Collection<Rental> getRentalsByBook(int bookId) {
        return rentalsRepo.stream()
                .filter(r -> r.getBookId() == bookId)
                .toList();
    }
}