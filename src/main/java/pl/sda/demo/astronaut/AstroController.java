package pl.sda.demo.astronaut;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.sda.demo.exception.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;

@RestController
//this a main url for all following mappings
@RequestMapping("/astronauts")
public class AstroController {

    private final AstroWebService astroWebService;

    //constructor dependency injection does not require @Autowire annotation.
    //astroWebService is fetched form ApplicationContext and assigned to class field
    public AstroController(AstroWebService astroWebService) {
        this.astroWebService = astroWebService;
    }

    //request POST - localhost:8080/astronauts
    @PostMapping
    public Astronaut postAstronaut(@RequestBody Astronaut astronaut){
        return astroWebService.save(astronaut);
    }

    @GetMapping
    public Iterable<Astronaut> getAllAstronauts() {
        return astroWebService.getAll();
    }

    //in this case we will have request localhost:8080/astronauts/all/crafts
    @GetMapping("/all/crafts")
    public List<String> getAllCrafts() {
        return astroWebService.getAllCrafts();
    }

    //request localhost:8080/astronauts/1
    @GetMapping("/{id}")
    //PathVariable annotation allows to assign {id} to argument - names must match
    public Astronaut getAstronautById(@PathVariable int id) {
        Optional<Astronaut> result = astroWebService.getAstronautById(id);
        //This our error handling. IF we can't find object we throw an exception that is handled in ExceptionsHandler class
        return result
                .orElseThrow(() -> new ObjectNotFoundException("Astronaut with id " + id + " not found"));
// Alternatively we could create ResponseEntity and set message and error code in every controller method
// but this would be not effective and error-prone

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
