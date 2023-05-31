package pl.sda.demo.astronaut.astronaut;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AstronautJdbcRepository implements CrudRepository<Astronaut, Integer> {

    private final JdbcTemplate jdbcTemplate;

    public AstronautJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public <S extends Astronaut> S save(S entity) {
        SimpleJdbcInsert astronautInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("astronaut")
                .usingGeneratedKeyColumns("astronaut_id");

        Map<String, Object> params = Map.of(
                "craft", entity.craft(),
                "name", entity.name());

        astronautInsert.execute(params);
        return entity;
    }

    @Override
    public <S extends Astronaut> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Astronaut> findById(Integer integer) {
        String sql = "SELECT * FROM astronaut WHERE astronaut_id=?";
        RowMapper<Astronaut> rowMapper = getAstronautRowMapper();
        List<Astronaut> result = jdbcTemplate.query(sql, rowMapper, integer);
        Astronaut astronaut = DataAccessUtils.singleResult(result);
        return Optional.ofNullable(astronaut);
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<Astronaut> findAll() {
        String sql = "SELECT * FROM astronaut";
        RowMapper<Astronaut> rowMapper = getAstronautRowMapper();
        return jdbcTemplate.query(sql, rowMapper);
    }

    private static RowMapper<Astronaut> getAstronautRowMapper() {
        return (rs, rowNum) -> new Astronaut(
                rs.getString("craft"),
                rs.getString("name"));
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
        String sql = "DELETE FROM astronaut WHERE astronaut_id=?";
        jdbcTemplate.update(sql, integer);
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