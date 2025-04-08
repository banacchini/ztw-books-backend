package pl.edu.pwr.ztw.books.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import pl.edu.pwr.ztw.books.model.Reader;
import pl.edu.pwr.ztw.books.model.Rental;

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
        readersRepo.add(new Reader(4, "Oliwier Miksza"));
        readersRepo.add(new Reader(5, "Jerzy Pastuła"));
        readersRepo.add(new Reader(6, "Leonard Misior"));
        readersRepo.add(new Reader(7, "Bianka Puch"));
        readersRepo.add(new Reader(8, "Aleksander Tołoczko"));
        readersRepo.add(new Reader(9, "Urszula Szmajda"));
        readersRepo.add(new Reader(10, "Jeremi Hojka"));
        readersRepo.add(new Reader(11, "Ewa Szajek"));
        readersRepo.add(new Reader(12, "Ksawery Olkowicz"));
        readersRepo.add(new Reader(13, "Sylwia Szramka"));
        readersRepo.add(new Reader(14, "Bruno Suda"));
        readersRepo.add(new Reader(15, "Anastazja Linkiewicz"));
        readersRepo.add(new Reader(16, "Dariusz Piaseczna"));
        readersRepo.add(new Reader(17, "Dawid Kuffel"));
        readersRepo.add(new Reader(18, "Cyprian Wawak"));
        readersRepo.add(new Reader(19, "Antoni Sinkiewicz"));
        readersRepo.add(new Reader(20, "Grzegorz Kołaczyk"));
        readersRepo.add(new Reader(21, "Ewelina Pajdak"));
        readersRepo.add(new Reader(22, "Alan Grzeszkowiak"));
        readersRepo.add(new Reader(23, "Karina Chadaj"));
        readersRepo.add(new Reader(24, "Anna Maria Domurad"));
        readersRepo.add(new Reader(25, "Inga Dyszy"));
        readersRepo.add(new Reader(26, "Witold Tomal"));
        readersRepo.add(new Reader(27, "Bianka Jacak"));
        readersRepo.add(new Reader(28, "Michał Kłysz"));
        readersRepo.add(new Reader(29, "Ernest Zmyślony"));
        readersRepo.add(new Reader(30, "Arkadiusz Ostasz"));
        readersRepo.add(new Reader(31, "Michał Szyguła"));
        readersRepo.add(new Reader(32, "Tymoteusz Jacak"));
        readersRepo.add(new Reader(33, "Bartek Handke"));
        readersRepo.add(new Reader(34, "Nela Parzonka"));
        readersRepo.add(new Reader(35, "Aurelia Cisoń"));
        readersRepo.add(new Reader(36, "Julian Chłąd"));
        readersRepo.add(new Reader(37, "Dorota Franas"));
        readersRepo.add(new Reader(38, "Aurelia Woronko"));
        readersRepo.add(new Reader(39, "Kazimierz Sterna"));
        readersRepo.add(new Reader(40, "Mariusz Smoczyk"));
        readersRepo.add(new Reader(41, "Cyprian Bobrowicz"));
        readersRepo.add(new Reader(42, "Nikodem Wasil"));
        readersRepo.add(new Reader(43, "Tymon Bociek"));
        readersRepo.add(new Reader(44, "Mateusz Janiszek"));
        readersRepo.add(new Reader(45, "Leon Capiga"));
        readersRepo.add(new Reader(46, "Radosław Wołk"));
        readersRepo.add(new Reader(47, "Kajetan Hajdasz"));
        readersRepo.add(new Reader(48, "Leon Dziedzina"));
        readersRepo.add(new Reader(49, "Radosław Loska"));
    }

    @Override
    public Collection<Reader> getReaders() {
        return readersRepo;
    }

    @Override
    public Map<String, Object> getReaders(int page, int size) {
        int totalReaders = readersRepo.size();
        int totalPages = (int) Math.ceil((double) totalReaders / size);
        int start = page * size;
        int end = Math.min(start + size, totalReaders);

        if (start > totalReaders) {
            return Map.of("message", "Page out of range");
        }

        List<Reader> readers = readersRepo.subList(start, end);

        Map<String, Object> response = new HashMap<>();
        response.put("readers", readers);
        response.put("currentPage", page);
        response.put("totalPages", totalPages);

        return response;
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

    @Override
    public int getReadersCount() {
        return readersRepo.size();
    }

    @Override
    public Collection<Rental> getRentalsByReader(int readerId) {
        return rentalsService.getRentalsByReader(readerId);
    }
}