package pl.sda.demo.library;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;
import java.util.Set;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class BookControllerTest {

    @Test
    void shouldReturnNotFoundForNonExistingBook(@Autowired WebTestClient webTestClient) {
        webTestClient
                .get()
                .uri("/books/99")
                .headers(httpHeaders -> httpHeaders.setBasicAuth("user", "haslo"))
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    void shouldWriteNewBook(@Autowired WebTestClient webTestClient) {
        webTestClient
                .post()
                .uri("/books")
                .headers(httpHeaders -> httpHeaders.setBasicAuth("user", "haslo"))
                .bodyValue(new Book("Stepy Akermańskie", LocalDate.now(), Set.of(new Author("Mickiewicz", null))))
                .exchange()
                .expectStatus().isCreated();

    }

}