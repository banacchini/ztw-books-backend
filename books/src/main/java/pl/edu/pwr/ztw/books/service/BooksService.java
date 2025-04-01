package pl.edu.pwr.ztw.books.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import pl.edu.pwr.ztw.books.model.Book;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BooksService implements IBooksService {
    private static List<Book> booksRepo = new ArrayList<>();

    @Autowired
    @Lazy
    private IAuthorsService authorsService;

    @Autowired
    @Lazy
    private IRentalsService rentalsService;

    static {
        booksRepo.add(new Book(1, "Potop", 1, 936));
        booksRepo.add(new Book(2, "Wesele", 2, 150));
        booksRepo.add(new Book(3, "Dziady", 3, 292));
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
        if (authorsService.getAuthor(book.getAuthorId()) == null) {
            throw new NoSuchElementException("Author not found with ID: " + book.getAuthorId());
        }
        booksRepo.add(book);
    }

    @Override
    public void updateBook(int id, Book book) {
        Book existingBook = getBook(id);
        if (existingBook != null) {
            if (authorsService.getAuthor(book.getAuthorId()) == null) {
                throw new NoSuchElementException("Author not found with ID: " + book.getAuthorId());
            }
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthorId(book.getAuthorId());
            existingBook.setPages(book.getPages());
        }
    }

    @Override
    public void deleteBook(int id) {
        if (rentalsService.getRentals().stream().anyMatch(r -> r.getBook().getId() == id && r.getReturnDate() == null)) {
            throw new IllegalArgumentException("Cannot delete book with ongoing rental");
        }
        booksRepo.removeIf(b -> b.getId() == id);
    }
}