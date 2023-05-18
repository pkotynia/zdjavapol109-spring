package pl.sda.demo;

import org.springframework.web.bind.annotation.*;

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
        //todo save astronaut
        //astroWebService.save(astronaut)

        return new Astronaut(astronaut.name(), astronaut.craft());
    }
}
