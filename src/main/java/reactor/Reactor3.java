package reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import java.util.Map;

public class Reactor3 {

    public static void main(final String... args) {
        final Flux<String> greetings = Flux.just("Hubert", "Sharon")
                .flatMap(Reactor3::nameToGreeting)
                .subscriberContext(context ->
                        Context.of("greetingWord", "Hello")  // context initialized
                );
        greetings.subscribe(System.out::println);
    }

    private static Mono<String> nameToGreeting(final String name) {
//        return Mono.just("Hello " + name + " !!!");  // ALERT: we don't have Context here

        return Mono.just(name)
                .flatMap(n -> Mono.subscriberContext()
                        .map(context -> {
                            String greeting = context.get("greetingWord"); // (2.1)
                            return greeting + " " + n + " !!!----";
                        }));

    }


}
