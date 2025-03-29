package pl.edu.pwr.ztw.books.service;

import pl.edu.pwr.ztw.books.model.Author;

import java.util.Collection;

public interface IAuthorsService {
    Collection<Author> getAuthors();
    Author getAuthor(int id);
    void addAuthor(Author author);
    void updateAuthor(int id, Author author);
    void deleteAuthor(int id);
}
