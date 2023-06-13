package pl.sda.demo.library;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BookJpaRepositoryTest {

    //this test is used for demonstration purposes only
    //Normally one should avoid testing generated code :)
    @Autowired
    private BookJpaRepository repository;

    @Test
    void shouldSaveBook() {
        //given book object
        Author mickiewicz = new Author("Adam Mickiewicz", null);
        Book panTadeusz = new Book("Pan Tadeusz",
                LocalDate.of(1832, 6, 28),
                Set.of(mickiewicz));
        panTadeusz.setBookUUID(UUID.randomUUID().toString());
        mickiewicz.setBooks(Set.of(panTadeusz));

        //when book is saved
        Book saved = repository.save(panTadeusz);
        System.out.println("saved = " + saved);

        //then books count should be 1
        assertEquals(1, repository.count());
    }

    @Test
    void shouldGetBookByUUID() {
        //given book object
        Author mickiewicz = new Author("Adam Mickiewicz", null);
        Book panTadeusz = new Book("Pan Tadeusz",
                LocalDate.of(1832, 6, 28),
                Set.of(mickiewicz));
        mickiewicz.setBooks(Set.of(panTadeusz));
        panTadeusz.setBookUUID(UUID.randomUUID().toString());
        Book saved = repository.save(panTadeusz);

        //when
        Optional<Book> bookByBookUUID = repository.findBookByBookUUID(saved.getBookUUID());

        //then
        assertEquals(bookByBookUUID.isPresent(), true);

    }
}