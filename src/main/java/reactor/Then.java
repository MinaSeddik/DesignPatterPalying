package reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Then {

    public static void main(String[] args) {

        Flux<Integer> flux = Flux.just(1, 2, 3);

//        flux.then().doOnNext(x -> System.out.println("zzz"))
//                .subscribe(System.out::println, System.out::println, () -> System.out.println("Completed!"));
//
//        flux.thenEmpty(Flux.just(5,6).then()).doOnNext(x -> System.out.println("zzz"))
//                .subscribe(System.out::println, System.out::println, () -> System.out.println("Completed!"));

//        flux.<Integer>thenMany(Flux.just(5,6))
//                .subscribe(System.out::println, System.out::println, () -> System.out.println("Completed!"));

        Mono.justOrEmpty(5).thenReturn(222)
                .subscribe(System.out::println, System.out::println, () -> System.out.println("Completed!"));


//        Mono<Void> mono1 = flux.then();
//        mono1.subscribe(null, null, () -> System.out.println("yes1"));

    }
}
