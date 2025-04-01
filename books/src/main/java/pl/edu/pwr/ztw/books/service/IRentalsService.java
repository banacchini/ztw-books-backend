package pl.edu.pwr.ztw.books.service;

import pl.edu.pwr.ztw.books.model.Rental;

import java.util.Collection;

public interface IRentalsService {
    Collection<Rental> getRentals();
    Rental getRental(int id);
    void rentBook(Rental rental);
    void returnBook(int id);
    void updateRental(int id, Rental updatedRental);
    void deleteRental(int id);
}