package pl.sda.demo.astronaut.astronaut;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AstroWebService {

    //Dependency injection using Autowired on filed - this is not recommended way
    //@Autowired
    private final RestTemplateBuilder builder;

    private final AstronautJdbcRepository repository;

//    Dependency injection using constructor
    public AstroWebService(RestTemplateBuilder builder, AstronautJdbcRepository repository) {
        this.builder = builder;
        this.repository = repository;
    }

    Astronaut save(Astronaut astronaut) {
        return repository.save(astronaut);
    }

    Iterable<Astronaut> getAll() {
        return repository.findAll();
    }

    Map<String, Long> getAstronauts() {
        String url = "http://api.open-notify.org/astros.json";
        return builder
                .build()
                .getForObject(url, AstronautsResult.class)
                .people()
                .stream()
                .collect(Collectors
                        .groupingBy(astronaut -> astronaut.craft(), Collectors.counting()));
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    public Optional<Astronaut> getAstronautById(int id) {
        return repository.findById(id);
    }

    public List<String> getAllCrafts() {
        return null;
//        return repository.findAll()
//                .stream()
//                .map(astronaut -> astronaut.craft())
//                .toList();
    }
//    Dependency injection by using Autowired on setter method
//    @Autowired
//    public void setBuilder(RestTemplateBuilder builder) {
//        this.builder = builder;
//    }
}
