package pl.edu.pwr.ztw.books.model;

import java.time.LocalDate;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class Rental {

    private int id;

    @NotNull
    private Book book;

    @NotNull
    private int readerID;
    private LocalDate rentalDate;
    private LocalDate returnDate;

    public Rental(int id, Book book, int readerID, LocalDate rentalDate, LocalDate returnDate) {
        this.id = id;
        this.book = book;
        this.readerID = readerID;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getReaderID() {
        return readerID;
    }

    public void setReaderID(int readerID) {
        this.readerID = readerID;
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
