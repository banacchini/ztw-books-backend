package pl.edu.pwr.ztw.books.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import pl.edu.pwr.ztw.books.model.Book;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BooksService implements IBooksService {
    private static List<Book> booksRepo = new ArrayList<>();

    @Autowired
    @Lazy
    private IAuthorsService authorsService;

    @Autowired
    @Lazy
    private IRentalsService rentalsService;

    static {
        booksRepo.add(new Book(1, "Potop", 1, 936));
        booksRepo.add(new Book(2, "Wesele", 2, 150));
        booksRepo.add(new Book(3, "Dziady", 3, 292));
        booksRepo.add(new Book(4, "Zaginiona Korona", 6, 346));
        booksRepo.add(new Book(5, "Podróż do Krainy Cieni", 37, 966));
        booksRepo.add(new Book(6, "Legenda Ostatniego Smoka", 37, 259));
        booksRepo.add(new Book(7, "Szept Lasu", 6, 733));
        booksRepo.add(new Book(8, "Miasto Złudzeń", 18, 146));
        booksRepo.add(new Book(9, "Ostatni Strażnik Światła", 14, 366));
        booksRepo.add(new Book(10, "Krwawy Księżyc", 13, 816));
        booksRepo.add(new Book(11, "W Labiryncie Przeznaczenia", 25, 851));
        booksRepo.add(new Book(12, "Przeklęty Pierścień", 16, 867));
        booksRepo.add(new Book(13, "Czarny Szlak", 24, 723));
        booksRepo.add(new Book(14, "Dziedzictwo Upadłych", 1, 836));
        booksRepo.add(new Book(15, "Władca Iluzji", 18, 58));
        booksRepo.add(new Book(16, "Zagubieni w Czasie", 10, 845));
        booksRepo.add(new Book(17, "Księga Zapomnianych Opowieści", 40, 946));
        booksRepo.add(new Book(18, "Córka Burzy", 12, 372));
        booksRepo.add(new Book(19, "Kraina Bez Imienia", 27, 960));
        booksRepo.add(new Book(20, "Wiatr Zmian", 13, 518));
        booksRepo.add(new Book(21, "Pieśń Nocy", 20, 154));
        booksRepo.add(new Book(22, "Oczy Bestii", 25, 761));
        booksRepo.add(new Book(23, "Powrót do Avalon", 11, 372));
        booksRepo.add(new Book(24, "Bractwo Srebrnego Wilka", 22, 741));
        booksRepo.add(new Book(25, "Sekretne Kroniki Zakonu", 34, 132));
        booksRepo.add(new Book(26, "Księżniczka Mgły", 8, 924));
        booksRepo.add(new Book(27, "Przez Mrok i Ogień", 13, 226));
        booksRepo.add(new Book(28, "Dom na Wzgórzu", 33, 56));
        booksRepo.add(new Book(29, "Czarodziejskie Zwierciadło", 7, 185));
        booksRepo.add(new Book(30, "Mroczna Symfonia", 21, 686));
        booksRepo.add(new Book(31, "Cień Nad Rzeką", 1, 234));
        booksRepo.add(new Book(32, "Skrytobójca Królestwa", 1, 911));
        booksRepo.add(new Book(33, "Serce Magii", 2, 729));
        booksRepo.add(new Book(34, "Uwięzieni w Śnie", 5, 333));
        booksRepo.add(new Book(35, "Przepowiednia Zapomnianego Oręża", 24, 128));
        booksRepo.add(new Book(36, "W Królestwie Złamanego Serca", 36, 152));
        booksRepo.add(new Book(37, "Ostatnia Pieczęć", 10, 368));
        booksRepo.add(new Book(38, "Bramy Podziemia", 32, 837));
        booksRepo.add(new Book(39, "Zaklęcie Smoczej Łzy", 33, 838));
        booksRepo.add(new Book(40, "Krew i Popiół", 49, 239));
        booksRepo.add(new Book(41, "Królowa Ciemności", 3, 485));
        booksRepo.add(new Book(42, "Most do Zaświatów", 29, 134));
        booksRepo.add(new Book(43, "Władca Czasu", 44, 452));
        booksRepo.add(new Book(44, "Lśniące Ostrze", 15, 489));
        booksRepo.add(new Book(45, "Przebudzenie Czarnej Róży", 4, 878));
        booksRepo.add(new Book(46, "Na Granicy Światów", 44, 652));
        booksRepo.add(new Book(47, "Czerwona Twierdza", 16, 225));
        booksRepo.add(new Book(48, "Rytuał Zmierzchu", 22, 728));
        booksRepo.add(new Book(49, "Wąż i Korona", 20, 159));
        booksRepo.add(new Book(50, "Smocze Echo", 3, 499));
        booksRepo.add(new Book(51, "Przeznaczenie Wybrańca", 29, 914));
        booksRepo.add(new Book(52, "Wojna Pomiędzy Gwiazdami", 43, 693));
        booksRepo.add(new Book(53, "Ostatnie Tchnienie Magii", 2, 78));
        booksRepo.add(new Book(54, "Władcy Piekieł", 25, 764));
        booksRepo.add(new Book(55, "Duchy Przyszłości", 37, 709));
        booksRepo.add(new Book(56, "Wędrowiec po Światach", 10, 191));
        booksRepo.add(new Book(57, "Pieśń Utraconych Dusz", 10, 50));
        booksRepo.add(new Book(58, "Zmierzch Bogów", 16, 406));
        booksRepo.add(new Book(59, "Dziennik Zaginionego Podróżnika", 40, 696));
        booksRepo.add(new Book(60, "Pod Skrzydłami Feniksa", 2, 504));
        booksRepo.add(new Book(61, "Maskarada Śmierci", 10, 682));
        booksRepo.add(new Book(62, "Czarna Akademia", 21, 701));
        booksRepo.add(new Book(63, "Tajemnicza Wyspa Cieni", 31, 194));
        booksRepo.add(new Book(64, "Poszukiwacze Pradawnej Prawdy", 47, 609));
        booksRepo.add(new Book(65, "Wiedźma z Czarnego Lasu", 17, 56));
        booksRepo.add(new Book(66, "Mgła Nad Krainą Snów", 33, 812));
        booksRepo.add(new Book(67, "Bestia w Ludzkiej Skórze", 26, 785));
        booksRepo.add(new Book(68, "Klątwa Białej Róży", 30, 84));
        booksRepo.add(new Book(69, "Po Drugiej Stronie Zwierciadła", 14, 141));
        booksRepo.add(new Book(70, "Krąg Wybrańców", 49, 762));
        booksRepo.add(new Book(71, "W Otchłani Ciemności", 33, 781));
        booksRepo.add(new Book(72, "Czasoprzestrzenne Zawirowania", 31, 837));
        booksRepo.add(new Book(73, "Korona i Dym", 33, 372));
        booksRepo.add(new Book(74, "Pustynia Zapomnienia", 44, 691));
        booksRepo.add(new Book(75, "Bractwo Czarnej Ręki", 16, 82));
        booksRepo.add(new Book(76, "Książę Wiatru", 16, 941));
        booksRepo.add(new Book(77, "Zakon Czarnej Gwiazdy", 9, 875));
        booksRepo.add(new Book(78, "Pod Skrzydłami Smoka", 15, 262));
        booksRepo.add(new Book(79, "Las Duchów", 16, 944));
        booksRepo.add(new Book(80, "Lament Wiecznych Cieni", 16, 919));
        booksRepo.add(new Book(81, "Pieśń Lodowego Żniwiarza", 28, 141));
        booksRepo.add(new Book(82, "Krew i Stal", 6, 502));
        booksRepo.add(new Book(83, "Tajemnice Starej Krypt", 40, 992));
        booksRepo.add(new Book(84, "Upadek Złotej Dynastii", 9, 139));
        booksRepo.add(new Book(85, "Zamknięci w Czasie", 4, 558));
        booksRepo.add(new Book(86, "Świątynia Ognia", 6, 533));
        booksRepo.add(new Book(87, "Czarodziej z Południa", 13, 570));
        booksRepo.add(new Book(88, "Oko Mędrca", 27, 943));
        booksRepo.add(new Book(89, "Głosy Umarłych", 25, 598));
        booksRepo.add(new Book(90, "W Mroku Krwi", 7, 805));
        booksRepo.add(new Book(91, "Strażnicy Ukrytej Prawdy", 20, 354));
        booksRepo.add(new Book(92, "Wojna w Świecie Iluzji", 32, 502));
        booksRepo.add(new Book(93, "Sztorm Nad Zapomnianym Królestwem", 41, 709));
        booksRepo.add(new Book(94, "Pieczęć Przeznaczenia", 40, 554));
        booksRepo.add(new Book(95, "Przekleństwo Czerwonej Księgi", 28, 480));
        booksRepo.add(new Book(96, "Mistrz Alchemii", 18, 540));
        booksRepo.add(new Book(97, "Władca Demonów", 41, 886));
        booksRepo.add(new Book(98, "Ścieżka Bez Powrotu", 43, 209));
        booksRepo.add(new Book(99, "Mglisty Tron", 19, 1000));
    }

    @Override
    public Collection<Book> getBooks() {
        return booksRepo;
    }

    @Override
    public Book getBook(int id) {
        return booksRepo.stream()
                .filter(b -> b.getId() == id)
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("Book not found with ID: " + id));
    }

    @Override
    public void addBook(Book book) {
        if (booksRepo.stream().anyMatch(b -> b.getId() == book.getId())) {
            throw new IllegalArgumentException("Book ID already exists");
        }
        if (authorsService.getAuthor(book.getAuthorId()) == null) {
            throw new NoSuchElementException("Author not found with ID: " + book.getAuthorId());
        }
        int nextId = booksRepo.isEmpty() ? 1 : booksRepo.get(booksRepo.size() - 1).getId() + 1;
        book.setId(nextId);
        booksRepo.add(book);
    }

    @Override
    public void updateBook(int id, Book book) {
        Book existingBook = getBook(id);
        if (existingBook == null) {
            throw new NoSuchElementException("Book not found with ID: " + id);
        }
        else {
            if (authorsService.getAuthor(book.getAuthorId()) == null) {
                throw new NoSuchElementException("Author not found with ID: " + book.getAuthorId());
            }
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthorId(book.getAuthorId());
            existingBook.setPages(book.getPages());
        }
    }

    @Override
    public void deleteBook(int id) {
        if (booksRepo.stream().noneMatch(b -> b.getId() == id)) {
            throw new NoSuchElementException("Book not found with ID: " + id);
        }
        if (rentalsService.isBookRented(id)) {
            throw new IllegalArgumentException("Cannot delete book with ongoing rental");
        }
        booksRepo.removeIf(b -> b.getId() == id);
    }
}