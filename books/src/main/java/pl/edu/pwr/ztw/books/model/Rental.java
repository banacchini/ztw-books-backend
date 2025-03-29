package pl.edu.pwr.ztw.books.model;

import java.time.LocalDate;

public class Rental {
    private int id;
    private Book book;
    private Reader reader;
    private LocalDate rentalDate;
    private LocalDate returnDate;

    public Rental(int id, Book book, Reader reader, LocalDate rentalDate, LocalDate returnDate) {
        this.id = id;
        this.book = book;
        this.reader = reader;
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

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
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
