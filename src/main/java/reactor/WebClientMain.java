package reactor;

import org.springframework.web.reactive.function.client.WebClient;

public class WebClientMain {

    public static void main(String[] args) {

        int userId = 9;

        WebClient.create("http://localhost/api")
                .get()
                .uri("/users/{id}", userId)
                .retrieve()
//                .bodyToMono(User.class)
                .bodyToFlux(User.class)
                .map(User::getName)
                .subscribe(System.out::println);

    }
}
