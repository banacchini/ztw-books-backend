package pl.edu.pwr.ztw.books.service;

import org.springframework.stereotype.Service;
import pl.edu.pwr.ztw.books.model.Author;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AuthorsService implements IAuthorsService {
    private static List<Author> authorsRepo = new ArrayList<>();
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
                .findAny()
                .orElse(null);
    }

    @Override
    public void addAuthor(Author author) {
        if (authorsRepo.stream().anyMatch(a -> a.getId() == author.getId())) {
            throw new IllegalArgumentException("Author ID already exists");
        }
        authorsRepo.add(author);
    }

    @Override
    public void updateAuthor(int id, Author author) {
        Author existingAuthor = getAuthor(id);
        if (existingAuthor != null) {
            existingAuthor.setName(author.getName());
        }
    }

    @Override
    public void deleteAuthor(int id) {
        authorsRepo.removeIf(a -> a.getId() == id);
    }
}