package pl.edu.pwr.ztw.books.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import pl.edu.pwr.ztw.books.model.Reader;

import java.util.*;

@Service
public class ReadersService implements IReadersService {
    private static List<Reader> readersRepo = new ArrayList<>();

    @Autowired
    @Lazy
    private IRentalsService rentalsService;

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
                .orElseThrow(() -> new NoSuchElementException("Reader not found with ID: " + id));
    }

    @Override
    public void addReader(Reader reader) {
        int nextId = readersRepo.isEmpty() ? 1 : readersRepo.get(readersRepo.size() -1).getId() + 1;
        reader.setId(nextId);
        readersRepo.add(reader);
    }

    @Override
    public void updateReader(int id, Reader reader) {
        Optional<Reader> existingReaderOpt = readersRepo.stream().filter(r -> r.getId() == id).findFirst();
        if(existingReaderOpt.isEmpty()){
            throw new NoSuchElementException("Reader with id " + id + " not found");
        }
        existingReaderOpt.get().setName(reader.getName());
    }

    @Override
    public void deleteReader(int id) {
        if (rentalsService.getRentals().stream().anyMatch(r -> r.getReaderId() == id && r.getReturnDate() == null)) {
            throw new IllegalArgumentException("Cannot delete reader with ongoing rental");
        }
        if(!readersRepo.removeIf(r -> r.getId() == id)){
            throw new NoSuchElementException("Reader with id " + id + " not found");
        }
    }
}