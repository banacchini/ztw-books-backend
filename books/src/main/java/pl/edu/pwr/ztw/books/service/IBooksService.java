package pl.edu.pwr.ztw.books.service;

import pl.edu.pwr.ztw.books.model.Book;

import java.util.Collection;

public interface IBooksService {
    public abstract Collection<Book> getBooks();
    public abstract Book getBook(int id);
    void addBook(Book book);
    void updateBook(int id, Book book);
    void deleteBook(int id);

}
