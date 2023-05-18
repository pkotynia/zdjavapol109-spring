package pl.sda.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AstroWebService {

    //Dependency injection using Autowired on filed - this is not recommended way
    //@Autowired
    private RestTemplateBuilder builder;

    //Dependency injection using constructor
//    public AstroWebService(RestTemplateBuilder builder) {
//        this.builder = builder;
//    }

    Astronaut save(Astronaut astronaut) {
        //repository
        return null;
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
//    Dependency injection by using Autowired on setter method
//    @Autowired
//    public void setBuilder(RestTemplateBuilder builder) {
//        this.builder = builder;
//    }
}
