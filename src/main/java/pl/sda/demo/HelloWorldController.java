package pl.sda.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/world")
    public String sayHello() {
        return "Hello World";
    }
}
