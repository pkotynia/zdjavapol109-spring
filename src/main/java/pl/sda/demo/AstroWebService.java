package pl.sda.demo;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AstroWebService {

    private final RestTemplateBuilder builder;

    public AstroWebService(RestTemplateBuilder builder) {
        this.builder = builder;
    }

    Map<String, Long> getAstronauts() {
        String url = "http://api.open-notify.org/astros.json";
        return builder
                .build()
                .getForObject(url, AstronautsResult.class)
                .people()
                .stream()
                .collect(Collectors.groupingBy(astronaut -> astronaut.craft(), Collectors.counting()));
    }
}
