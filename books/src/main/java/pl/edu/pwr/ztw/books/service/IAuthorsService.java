package pl.edu.pwr.ztw.books.service;

import pl.edu.pwr.ztw.books.model.Author;

import java.util.Collection;
import java.util.List;

public interface IAuthorsService {
    List<Author> getAuthors(int page, int size);
    Collection<Author> getAuthors();
    Author getAuthor(int id);
    void addAuthor(Author author);
    void updateAuthor(int id, Author author);
    void deleteAuthor(int id);
}
