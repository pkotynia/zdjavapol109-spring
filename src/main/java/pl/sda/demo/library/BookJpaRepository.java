package pl.sda.demo.library;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookJpaRepository extends JpaRepository<Book, Integer> {

}
