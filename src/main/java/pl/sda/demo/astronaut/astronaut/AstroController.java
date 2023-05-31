package pl.sda.demo.astronaut.astronaut;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/astronauts")
public class AstroController {

    private final AstroWebService astroWebService;

    public AstroController(AstroWebService astroWebService) {
        this.astroWebService = astroWebService;
    }

    @PostMapping
    public Astronaut postAstronaut(@RequestBody Astronaut astronaut){
        return astroWebService.save(astronaut);
    }

    @GetMapping()
    public Iterable<Astronaut> getAllAstronauts() {
        return astroWebService.getAll();
    }

    @GetMapping("/all/crafts")
    public List<String> getAllCrafts() {
        return astroWebService.getAllCrafts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Astronaut> getAstronautById(@PathVariable int id) {
        Optional<Astronaut> result = astroWebService.getAstronautById(id);
        return result
                .map(astronaut -> ResponseEntity.ok(astronaut))
                .orElseGet(() -> ResponseEntity.notFound().build());

//        if (!result.isPresent()) {
//            return ResponseEntity
//                    .notFound()
//                    .build();
//        } else {
//            return ResponseEntity
//                    .ok(result.get());
//        }

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
