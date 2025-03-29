package pl.edu.pwr.ztw.books.service;

import pl.edu.pwr.ztw.books.model.Reader;

import java.util.Collection;

public interface IReadersService {
    Collection<Reader> getReaders();
    Reader getReader(int id);
    void addReader(Reader reader);
    void updateReader(int id, Reader reader);
    void deleteReader(int id);
}