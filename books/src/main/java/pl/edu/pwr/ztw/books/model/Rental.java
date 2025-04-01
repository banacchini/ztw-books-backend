package pl.edu.pwr.ztw.books.model;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public class Rental {

    private int id;

    @NotNull
    private int bookId;

    @NotNull
    private int readerId;
    private LocalDate rentalDate;
    private LocalDate returnDate;

    public Rental(int id, int bookId, int readerId, LocalDate rentalDate, LocalDate returnDate) {
        this.id = id;
        this.bookId = bookId;
        this.readerId = readerId;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int book) {
        this.bookId = book;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
