package pl.sda.demo.astronaut;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.demo.astronaut.astronaut.Astronaut;
import pl.sda.demo.astronaut.astronaut.AstronautJdbcRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AstronautJdbcRepositoryTest {

    @Autowired
    private AstronautJdbcRepository repository;

    @Test
    void shouldSaveAstronaut() {
        Astronaut astronaut = new Astronaut("Tom Hanks", "Apollo 13");
        Astronaut save = repository.save(astronaut);
        assertEquals(new Astronaut("Tom Hanks", "Apollo 13"), save);
    }

    @Test
    void shouldReturnAllAstronauts() {
        Iterable<Astronaut> result = repository.findAll();
        List<Astronaut> astronauts = new ArrayList<>();
        result.forEach(astronaut -> astronauts.add(astronaut));
        assertEquals(4, astronauts.size());
    }

    @Test
    void shouldDelete() {
        repository.deleteById(1);
        Optional<Astronaut> byId = repository.findById(1);

        assertEquals(Optional.empty(), byId);
    }

}