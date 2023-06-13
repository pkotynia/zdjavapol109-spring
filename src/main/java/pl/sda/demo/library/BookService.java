package pl.sda.demo.library;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {

    private final BookJpaRepository repository;

    public BookService(BookJpaRepository repository) {
        this.repository = repository;
    }

//    public Optional<Book> findById(Integer id) {
//        return repository.findById(id);
//    }

    public Optional<Book> findByUUID(String uuid) {
        return repository.findBookByBookUUID(uuid);
    }

    public Book save(Book book) {
        UUID bookUUID = UUID.randomUUID();
        book.setBookUUID(bookUUID.toString());
        return repository.save(book);
    }
}
