package pl.sda.demo;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller
//@ResponseBody
// we can use @Controller and @ResponseBody or @RestController which combines those two annotations
@RestController
public class HelloWorldController {

    @GetMapping("/world")
    public String sayHello() {
        return "Hello World";
    }
}
