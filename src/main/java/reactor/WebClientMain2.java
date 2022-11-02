package reactor;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class WebClientMain2 {

    public static void main(String[] args) {

//        WebClient client = WebClient.builder()
//                .baseUrl("http://localhost:8080")
//                .defaultCookie("cookieKey", "cookieValue")
//                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
//                .build();

        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8080")
                .build();

        webClient.post()
                .uri("/temperature-stream")
                .body(BodyInserters.fromPublisher(Mono.just(new PasswordDTO("raw", "encoded")), PasswordDTO.class))
                .exchange()
                .flatMap(response -> {
                    if (response.statusCode().is2xxSuccessful()) {
                        return Mono.empty(); //
                    } else if (response.statusCode().isError()) { //
                        return Mono.error(new BadCredentialsException("Invalid password"));
                    }
                    return Mono.error(new IllegalStateException()); //
                });
    }
}
