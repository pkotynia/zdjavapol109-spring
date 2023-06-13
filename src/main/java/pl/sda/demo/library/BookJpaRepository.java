package pl.sda.demo.library;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookJpaRepository extends JpaRepository<Book, Integer> {

    Optional<Book> findBookByBookUUID(String bookUUID);

}
