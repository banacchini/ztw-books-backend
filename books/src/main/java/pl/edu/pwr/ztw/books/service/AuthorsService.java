package pl.edu.pwr.ztw.books.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pwr.ztw.books.model.Author;

import java.util.*;

@Service
public class AuthorsService implements IAuthorsService {
    private static List<Author> authorsRepo = new ArrayList<>();

    @Autowired
    private BooksService booksService;

    static {
        authorsRepo.add(new Author(1, "Henryk Sienkiewicz"));
        authorsRepo.add(new Author(2, "Stanisław Reymont"));
        authorsRepo.add(new Author(3, "Adam Mickiewicz"));
    }

    @Override
    public Collection<Author> getAuthors() {
        return authorsRepo;
    }

    @Override
    public Author getAuthor(int id) {
        return authorsRepo.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Author not found with ID: " + id));
    }

    @Override
    public void addAuthor(Author author) {
//        if (authorsRepo.stream().anyMatch(a -> a.getId() == author.getId())) {
//            throw new IllegalArgumentException("Author ID already exists");
//        }
        author.setId(authorsRepo.get(authorsRepo.size() - 1).getId() + 1);
        authorsRepo.add(author);
    }

    @Override
    public void updateAuthor(int id, Author author) {
        Optional<Author> existingAuthor = authorsRepo.stream().filter(a -> a.getId() == id).findFirst();
        if (existingAuthor.isEmpty()) {
            throw new NoSuchElementException("Author not found with ID: " + id);
        }
        existingAuthor.get().setName(author.getName());
    }

    @Override
    public void deleteAuthor(int id) {
        if (booksService.getBooks().stream().anyMatch(b -> b.getAuthorId() == id)) {
            throw new IllegalArgumentException("Cannot delete author with existing books");
        }
        if (!authorsRepo.removeIf(a -> a.getId() == id)) {
            throw new NoSuchElementException("Author not found with ID: " + id);
        }
    }
}