package pl.sda.demo.library;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookJpaRepository extends JpaRepository<Book, Integer> {

    Optional<Book> findBookByBookUUID(String bookUUID);

    @Query("SELECT a.name, COUNT(a.books) FROM Author as a " +
            "JOIN a.books as b GROUP BY a.name") //todo check why we need this join
//    @Query(value = """
//            SELECT a.name, COUNT(b.book_id) FROM author a
//            JOIN book_author ab ON a.author_id = ab.author_id
//            JOIN book b ON b.book_id = ab.book_id
//            GROUP BY a.name;
//            """, nativeQuery = true )
    List<Object[]> countBooksByAuthor();

    @Query("SELECT COUNT(b) FROM Author as a " +
            "JOIN a.books as b WHERE a.name = :authorName")
    Long countBooksByAuthor(@Param("authorName") String authorName);

}
