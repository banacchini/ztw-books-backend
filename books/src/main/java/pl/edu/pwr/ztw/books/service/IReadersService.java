package pl.edu.pwr.ztw.books.service;

import pl.edu.pwr.ztw.books.model.Reader;

import java.util.Collection;
import java.util.Map;

public interface IReadersService {
    Map<String, Object> getReaders(int page, int size);
    Collection<Reader> getReaders();
    Reader getReader(int id);
    void addReader(Reader reader);
    void updateReader(int id, Reader reader);
    void deleteReader(int id);
    int getReadersCount();
}