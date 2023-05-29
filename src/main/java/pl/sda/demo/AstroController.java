package pl.sda.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/astronauts")
public class AstroController {

    private final AstroWebService astroWebService;

    public AstroController(AstroWebService astroWebService) {
        this.astroWebService = astroWebService;
    }

    @GetMapping
    public Map<String, Long> getAstronauts() {
        return astroWebService.getAstronauts();
    }

    @PostMapping
    public Astronaut postAstronaut(@RequestBody Astronaut astronaut){

        astroWebService.save(astronaut);

        return new Astronaut(astronaut.name(), astronaut.craft());
    }

    @GetMapping("/all")
    public List<Astronaut> getAllAstronauts() {
        return astroWebService.getAll();
    }

    @GetMapping("{id}")
    public Astronaut getAstronautById(@PathVariable int id) {
        return astroWebService.getAstronautById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAstronautById(@PathVariable int id) {
        astroWebService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
