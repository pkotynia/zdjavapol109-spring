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

    @PostMapping
    public Astronaut postAstronaut(@RequestBody Astronaut astronaut){
        astroWebService.save(astronaut);
        return new Astronaut(astronaut.name(), astronaut.craft());
    }

    @GetMapping()
    public List<Astronaut> getAllAstronauts() {
        return astroWebService.getAll();
    }

    @GetMapping("/all/crafts")
    public List<String> getAllCrafts() {
        return astroWebService.getAllCrafts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Astronaut> getAstronautById(@PathVariable int id) {
        Astronaut result = astroWebService.getAstronautById(id);
        if (result == null) {
            return ResponseEntity
                    .notFound()
                    .build();
        } else {
            return ResponseEntity
                    .ok(result);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAstronautById(@PathVariable int id) {
        if (astroWebService.getAstronautById(id) == null) {
            return ResponseEntity.notFound().build();
        } else {
            astroWebService.delete(id);
            return ResponseEntity.noContent().build();
        }
    }
}
