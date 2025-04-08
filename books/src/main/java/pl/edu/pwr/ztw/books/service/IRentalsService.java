package pl.edu.pwr.ztw.books.service;

import pl.edu.pwr.ztw.books.model.Rental;

import java.util.Collection;
import java.util.Map;

public interface IRentalsService {
    Map<String, Object> getRentals(int page, int size);
    Collection<Rental> getRentals();
    Rental getRental(int id);
    void rentBook(Rental rental);
    void returnBook(int id);
    void updateRental(int id, Rental updatedRental);
    void deleteRental(int id);
    boolean isBookRented(int bookId);
    int getOngoingRentalsCount();
    Collection<Rental> getRentalsByReader(int readerId);
    Collection<Rental> getRentalsByBook(int bookId);
}