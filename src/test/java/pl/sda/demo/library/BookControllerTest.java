package pl.sda.demo.library;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {

    @Test
    void shouldReturnNotFoundForNonExistingBook(@Autowired WebTestClient webTestClient) {
        webTestClient
                .get()
                .uri("/books/99")
                .exchange()
                .expectStatus()
                .is5xxServerError(); //TODO fix this by implementing exception handling
    }

    @Test
    void shouldWriteNewBook(@Autowired WebTestClient webTestClient) {
        webTestClient
                .post()
                .uri("/books")
                .bodyValue(new Book("Stepy Akermańskie", LocalDate.now(), Set.of(new Author("Mickiewicz", null))))
                .exchange()
                .expectStatus().isCreated();

    }

}