package pl.sda.demo.library;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookJpaRepository repository;

    public BookService(BookJpaRepository repository) {
        this.repository = repository;
    }

    public Optional<Book> findByUUID(String uuid) {
        return repository.findBookByBookUUID(uuid);
    }

    public Book save(Book book) {
        UUID bookUUID = UUID.randomUUID();
        book.setBookUUID(bookUUID.toString());
        return repository.save(book);
    }

    public Map<String, Long> countBooksByAuthor() {

        List<Object[]> objects = repository.countBooksByAuthor();

//        return objects
//                .stream()
//                .collect(Collectors.toMap(
//                        objectsArray -> (String)objectsArray[0] ,
//                        objectsArray -> (Long)objectsArray[1]
//                ));

        Map<String, Long> result = new HashMap<>();
        for (Object[] object : objects) {
            result.put((String) object[0], (Long) object[1]);
        }
        return result;
    }

    Long countBooksByAuthor(String authorName) {
        return repository.countBooksByAuthor(authorName);
    }
}
