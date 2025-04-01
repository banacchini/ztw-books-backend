package pl.edu.pwr.ztw.books.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pwr.ztw.books.model.Author;
import pl.edu.pwr.ztw.books.model.Book;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BooksService implements IBooksService {
    private static List<Book> booksRepo = new ArrayList<>();

    @Autowired
    private AuthorsService authorsService;

    static {
        booksRepo.add(new Book(1, "Potop", new Author(1, "Henryk Sienkiewicz"), 936));
        booksRepo.add(new Book(2, "Wesele", new Author(2, "Stanisław Reymont"), 150));
        booksRepo.add(new Book(3, "Dziady", new Author(3, "Adam Mickiewicz"), 292));
    }

    @Override
    public Collection<Book> getBooks() {
        return booksRepo;
    }

    @Override
    public Book getBook(int id) {
        return booksRepo.stream()
                .filter(b -> b.getId() == id)
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("Book not found with ID: " + id));
    }

    @Override
    public void addBook(Book book) {
        if (booksRepo.stream().anyMatch(b -> b.getId() == book.getId())) {
            throw new IllegalArgumentException("Book ID already exists");
        }
        Author author = book.getAuthor();
        if (authorsService.getAuthor(author.getId()) == null) {
            authorsService.addAuthor(author);
        }
        booksRepo.add(book);
    }

    @Override
    public void updateBook(int id, Book book) {
        Book existingBook = getBook(id);
        if (existingBook != null) {
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setPages(book.getPages());
        }
    }

    @Override
    public void deleteBook(int id) {
        booksRepo.removeIf(b -> b.getId() == id);
    }
}