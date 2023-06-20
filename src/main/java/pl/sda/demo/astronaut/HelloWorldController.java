package pl.sda.demo.astronaut;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller
//@ResponseBody
// we can use @Controller and @ResponseBody or @RestController which combines those two annotations
@RestController
public class HelloWorldController {

    //allows to define URL for GET request in this our case localhost:8080/world
    @GetMapping("/world")
    public String sayHello() {
        return "Hello World";
    }
}
