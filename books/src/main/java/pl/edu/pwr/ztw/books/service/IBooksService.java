package pl.edu.pwr.ztw.books.service;

import pl.edu.pwr.ztw.books.model.Book;
import pl.edu.pwr.ztw.books.model.Rental;

import java.util.Collection;
import java.util.Map;

public interface IBooksService {
    Map<String, Object> getBooks(int page, int size);
    public abstract Collection<Book> getBooks();
    public abstract Book getBook(int id);
    void addBook(Book book);
    void updateBook(int id, Book book);
    void deleteBook(int id);
    int getBooksCount();
    Collection<Book> getBooksByAuthor(int authorId);
    Collection<Rental> getRentalsByBook(int bookId);

}
