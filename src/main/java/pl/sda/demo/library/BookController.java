package pl.sda.demo.library;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.sda.demo.exception.ObjectNotFoundException;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    private static Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/byAuthor")
    public Map<String, Long> countBooksByAuthor(){
        return service.countBooksByAuthor();
    }

    @GetMapping("/{uuid}")
    public Book findBookById(@PathVariable String uuid) {
        LOGGER.info("Finding object with id {}", uuid);
        Optional<Book> byId = service.findByUUID(uuid);

// this is a manual way of handling not found case - it was replaced by global exception handling mechanism        //
//        ResponseEntity<Book> bookResponseEntity = byId
//                .map(book -> ResponseEntity.ok(book))
//                .orElseGet(() -> ResponseEntity.notFound().build());

        return byId.orElseThrow(() -> new ObjectNotFoundException("book with id " + uuid + " not found"));
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book saved = service.save(book);

        URI location = createLocationHeader(saved);

        return ResponseEntity
                .created(location)
                .body(saved);

    }

    @GetMapping("/byAuthor/{name}")
    public Long countBooksByAuthorName(@PathVariable String name) {
        return service.countBooksByAuthor(name);
    }

    private static URI createLocationHeader(Book saved) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getBookId())
                .toUri();
    }
}
