package pl.edu.pwr.ztw.books.service;

import org.springframework.stereotype.Service;
import pl.edu.pwr.ztw.books.model.Reader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ReadersService implements IReadersService {
    private static List<Reader> readersRepo = new ArrayList<>();

    static {
        readersRepo.add(new Reader(1, "John Doe"));
        readersRepo.add(new Reader(2, "Jane Smith"));
        readersRepo.add(new Reader(3, "Alice Johnson"));
    }

    @Override
    public Collection<Reader> getReaders() {
        return readersRepo;
    }

    @Override
    public Reader getReader(int id) {
        return readersRepo.stream()
                .filter(r -> r.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void addReader(Reader reader) {
        if (readersRepo.stream().anyMatch(r -> r.getId() == reader.getId())) {
            throw new IllegalArgumentException("Reader ID already exists");
        }
        readersRepo.add(reader);
    }

    @Override
    public void updateReader(int id, Reader reader) {
        Reader existingReader = getReader(id);
        if (existingReader != null) {
            existingReader.setName(reader.getName());
        }
    }

    @Override
    public void deleteReader(int id) {
        readersRepo.removeIf(r -> r.getId() == id);
    }
}