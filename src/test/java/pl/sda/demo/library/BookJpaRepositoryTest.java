package pl.sda.demo.library;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookJpaRepositoryTest {

    @Autowired
    private BookJpaRepository repository;

    @Test
    void shouldSaveBook() {
        //given book object
        Author mickiewicz = new Author("Adam Mickiewicz", null);
        Book panTadeusz = new Book("Pan Tadeusz",
                LocalDate.of(1832, 6, 28),
                Set.of(mickiewicz));
        mickiewicz.setBooks(Set.of(panTadeusz));

        //when book is saved
        Book saved = repository.save(panTadeusz);
        System.out.println("saved = " + saved);

        //then books count should be 1
        assertEquals(1, repository.count());
    }
}