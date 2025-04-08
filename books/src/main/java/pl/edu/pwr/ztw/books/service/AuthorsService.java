package pl.edu.pwr.ztw.books.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import pl.edu.pwr.ztw.books.model.Author;
import pl.edu.pwr.ztw.books.model.Book;

import java.util.*;

@Service
public class AuthorsService implements IAuthorsService {
    private static List<Author> authorsRepo = new ArrayList<>();

    @Autowired
    @Lazy
    private IBooksService booksService;

    static {
        authorsRepo.add(new Author(1, "Henryk Sienkiewicz"));
        authorsRepo.add(new Author(2, "Stanisław Reymont"));
        authorsRepo.add(new Author(3, "Adam Mickiewicz"));
        authorsRepo.add(new Author(4, "Maciej Materek"));
        authorsRepo.add(new Author(5, "Patryk Kołpak"));
        authorsRepo.add(new Author(6, "Aurelia Śliż"));
        authorsRepo.add(new Author(7, "Kornelia Pyza"));
        authorsRepo.add(new Author(8, "Tomasz Chlebda"));
        authorsRepo.add(new Author(9, "Arkadiusz Działak"));
        authorsRepo.add(new Author(10, "Maurycy Langiewicz"));
        authorsRepo.add(new Author(11, "Ignacy Strojna"));
        authorsRepo.add(new Author(12, "Michał Mrózek"));
        authorsRepo.add(new Author(13, "Cyprian Stróż"));
        authorsRepo.add(new Author(14, "Oskar Drywa"));
        authorsRepo.add(new Author(15, "Bartek Mordak"));
        authorsRepo.add(new Author(16, "Gabriel Flisiak"));
        authorsRepo.add(new Author(17, "Urszula Kukuczka"));
        authorsRepo.add(new Author(18, "Piotr Heinrich"));
        authorsRepo.add(new Author(19, "Anita Duczek"));
        authorsRepo.add(new Author(20, "Olga Niedojadło"));
        authorsRepo.add(new Author(21, "Konstanty Sommerfeld"));
        authorsRepo.add(new Author(22, "Adrianna Naczk"));
        authorsRepo.add(new Author(23, "Jakub Myszak"));
        authorsRepo.add(new Author(24, "Miłosz Duras"));
        authorsRepo.add(new Author(25, "Urszula Kyc"));
        authorsRepo.add(new Author(26, "Monika Dzierwa"));
        authorsRepo.add(new Author(27, "Michał Ważna"));
        authorsRepo.add(new Author(28, "Sylwia Mazek"));
        authorsRepo.add(new Author(29, "Marianna Wojniak"));
        authorsRepo.add(new Author(30, "Albert Szkopek"));
        authorsRepo.add(new Author(31, "Tobiasz Pruchnik"));
        authorsRepo.add(new Author(32, "Sonia Armatys"));
        authorsRepo.add(new Author(33, "Rozalia Szałas"));
        authorsRepo.add(new Author(34, "Bruno Majos"));
        authorsRepo.add(new Author(35, "Anita Krzykała"));
        authorsRepo.add(new Author(36, "Roksana Pancerz"));
        authorsRepo.add(new Author(37, "Kacper Lubera"));
        authorsRepo.add(new Author(38, "Róża Cecot"));
        authorsRepo.add(new Author(39, "Ignacy Miętus"));
        authorsRepo.add(new Author(40, "Kacper Pilich"));
        authorsRepo.add(new Author(41, "Oskar Kuczak"));
        authorsRepo.add(new Author(42, "Kaja Bok"));
        authorsRepo.add(new Author(43, "Tobiasz Warczak"));
        authorsRepo.add(new Author(44, "Dagmara Tołoczko"));
        authorsRepo.add(new Author(45, "Ignacy Walenciak"));
        authorsRepo.add(new Author(46, "Witold Hankiewicz"));
        authorsRepo.add(new Author(47, "Julianna Pyszka"));
        authorsRepo.add(new Author(48, "Iwo Labocha"));
        authorsRepo.add(new Author(49, "Klara Pacholak"));
    }

    @Override
    public Collection<Author> getAuthors() {
        return authorsRepo;
    }

    @Override
    public Map<String, Object> getAuthors(int page, int size){
        int totalAuthors = authorsRepo.size();
        int totalPages = (int) Math.ceil((double) totalAuthors / size);
        int start = page * size;
        int end = Math.min(start + size, totalAuthors);

        if (start > totalAuthors) {
            return Map.of("message", "Page out of range");
        }

        List<Author> authors = authorsRepo.subList(start, end);

        Map<String, Object> response = new HashMap<>();
        response.put("authors", authors);
        response.put("currentPage", page);
        response.put("totalPages", totalPages);

        return response;
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
        int nextId = authorsRepo.isEmpty() ? 1 : authorsRepo.get(authorsRepo.size() - 1).getId() + 1;
        author.setId(nextId);
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

    @Override
    public int getAuthorsCount() {
        return authorsRepo.size();
    }

    @Override
    public Collection<Book> getBooksByAuthor(int authorId) {
        return booksService.getBooksByAuthor(authorId);
    }
}