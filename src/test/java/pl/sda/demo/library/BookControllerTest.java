package pl.sda.demo.library;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.Base64Utils;

import java.time.LocalDate;
import java.util.Set;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {

    @Test
    @WithMockUser
    void shouldReturnNotFoundForNonExistingBook(@Autowired WebTestClient webTestClient) {
        webTestClient
                .get()
                .uri("/books/99")

                .exchange()
                .expectStatus()
                .isNotFound();
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