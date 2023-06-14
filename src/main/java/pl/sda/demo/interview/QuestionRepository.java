package pl.sda.demo.interview;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(value = """
            SELECT id, question, answer FROM question
            ORDER BY RAND()
            LIMIT 1;
            """, nativeQuery = true)
    List<Object[]> getRandomQuestion();

}
