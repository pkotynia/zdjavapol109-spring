package pl.sda.demo.library;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    //Service should go here instead of repository to implement business logic
    private final BookJpaRepository repository;

    public BookController(BookJpaRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public Book findBookById(@PathVariable Integer id) {
        Optional<Book> byId = repository.findById(id);
        return byId.orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book saved = repository.save(book);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getBookId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(saved);

    }
}
