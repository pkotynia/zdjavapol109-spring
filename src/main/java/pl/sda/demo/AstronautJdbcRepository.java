package pl.sda.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class AstronautJdbcRepository implements CrudRepository<Astronaut, Integer> {

    private final JdbcTemplate jdbcTemplate;

    public AstronautJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public <S extends Astronaut> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Astronaut> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Astronaut> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<Astronaut> findAll() {
        String sql = "SELECT * FROM astronaut";
        RowMapper<Astronaut> rowMapper = (rs, rowNum) -> new Astronaut(
                rs.getString("craft"),
                rs.getString("name"));
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Iterable<Astronaut> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Astronaut entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Astronaut> entities) {

    }

    @Override
    public void deleteAll() {

    }
}

class AstronautRowMapper implements RowMapper<Astronaut> {

    @Override
    public Astronaut mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Astronaut(
                rs.getString("craft"),
                rs.getString("name"));
    }
}