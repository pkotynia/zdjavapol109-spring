package pl.sda.demo.astronaut;

import java.util.List;

public record AstronautsResult(String message, Integer number, List<Astronaut> people) {
}
