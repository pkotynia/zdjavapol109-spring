package pl.sda.demo.astronaut;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/astronauts-in-space")
public class AstronautsInSpaceController {

    private final AstroWebService astroWebService;

    public AstronautsInSpaceController(AstroWebService astroWebService) {
        this.astroWebService = astroWebService;
    }

    @GetMapping
    public Map<String, Long> getAstronauts() {
        return astroWebService.getAstronauts();
    }
}
