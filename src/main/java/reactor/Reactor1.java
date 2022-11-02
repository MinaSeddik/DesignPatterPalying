package reactor;


import reactor.core.publisher.Flux;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Reactor1 {


    public static void main(String[] args) throws InterruptedException, IOException {

//        List<Integer> list =  Flux.range(1, 100) // (1)
//                .repeat() // (2)
//                .collectList() // (3)
//                .block();

//        AtomicReference<Subscription> subscription_ = new AtomicReference<>();
//        Flux.range(1, 100) // (1)
//                .subscribe( // (2)
////                        data -> System.out.println("onNext: " + data),
//                        data -> {
//                            System.out.println("onNext: " + data);
//
//                            System.out.println("before request ... ");
//                            subscription_.get().request(4); // (3.1)
//                            System.out.println("after request ... ");
//
//                        },
//                        err -> { /* ignore */ },
//                        () -> System.out.println("onComplete"),
//                        subscription -> { // (3)
//                            System.out.println("onsubscription ... ");
//                            subscription_.set(subscription);
//
//                            System.out.println("before XXXXXXXXXXXXXXxrequest ... ");
//                            subscription_.get().request(4); // (3.1)
//                            System.out.println("after XXXXXXXXXXXXXXxrequest ... ");
//
//
////                            subscription.cancel(); // (3.2)
//                        }
//                );


//        Flux.range(2018, 5)
////                .timestamp()
//                .index()
//                .subscribe(data -> System.out.println("onNext: " + data));
//
//
//        Flux.just(1, 6, 2, 8, 3, 1, 5, 1)
//                .collectSortedList(Comparator.reverseOrder())
//                .subscribe(System.out::println);

//        Flux.just(3, 5, 7, 9, 11, 15, 16, 17)
//                .any(e -> e % 2 == 0)
//                .subscribe(hasEvens -> System.out.println("Has evens: " + hasEvens));
//
//        Flux.range(1, 5)
//                .reduce(0, (acc, elem) -> acc + elem)
//                .subscribe(result -> System.out.println("Result: " + result));
//
//        Flux.range(1, 5)
//                .scan(0, (acc, elem) -> acc + elem)
//                .subscribe(result -> System.out.println("Result: " + result));

//        int bucketSize = 5; // (1)
//        Flux.range(1, 500) // (2)
//                .index() // (3)
//                .scan( // (4)
//                        new int[bucketSize], // (4.1)
//                        (acc, elem) -> { //
//                            acc[(int)(elem.getT1() % bucketSize)] = elem.getT2(); // (4.2)
//                            return acc; // (4.3)
//                        })
//                .skip(bucketSize) // (5)
//                .map(array -> Arrays.stream(array).sum() * 1.0 / bucketSize) // (6)
//                .subscribe(av -> System.out.println("Running average: "+ av)); // (7)

//        Flux.just(1, 2, 3)
//                .thenMany(Flux.just(4, 5))
//                .subscribe(e -> System.out.println("onNext: " + e));

//        Flux<Flux<Integer>> windowedFlux = Flux.range(101, 20) // (1)
//                .windowUntil(Reactor1::isPrime, true); // (2)
//        windowedFlux.subscribe(window -> window // (3)
//                .collectList() // (4)
//                .subscribe(e -> log.info("window: {}", e))); // (5)

//        Flux.range(1, 100)
//                .delayElements(Duration.ofMillis(1))
//                .sample(Duration.ofMillis(20))
//                .subscribe(e -> System.out.println("onNext: " + e));


//        Flux.push(emitter -> IntStream // (1)
//                        .range(2000, 3000) // (1.1)
//                        .forEach(emitter::next)); // (1.2)
//                .delayElements(Duration.ofMillis(1)) // (2)
//                .subscribe(e -> System.out.println("onNext: " + e));


//        Flux flux1 = Flux.generate( // (1)
//                () -> Tuples.of(0L, 1L), // (1.1)
//                (state, sink) -> { //
//                    System.out.println("generated value: " + state.getT2()); //
//                    sink.next(state.getT2()); // (1.2)
//                    long newValue = state.getT1() + state.getT2(); //
//                    return Tuples.of(state.getT2(), newValue); // (1.3)
//                });
//        flux1.take(10).subscribe(e -> System.out.println("onNext: " + e));


//        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
//
//        // creating a flux with resources
//        Flux<Integer> flux2 = Flux.using(
//                () -> list,
//                (l) -> Flux.fromIterable(l),
//                (l) -> l.clear()
//        );
//
//        System.out.println(list);
//        flux2.subscribe(e -> System.out.println("onNext: " + e));
//        System.out.println(list);

        Path ipPath = Paths.get("/etc/passwd");

//        Flux<String> stringFlux = Flux.using(
//                        () -> Files.lines(ipPath),
//                        Flux::fromStream,
//                        Stream::close
//                ).filter(line -> isValidRecord(line))
//                .map(line -> line.split(","))
//                .onErrorMap(Lists::newArrayList)
//                .map(arr -> Item::new);

        Flux.range(0, 5)
                .delayElements(Duration.ofMillis(100))
                .elapsed();
//        stringFlux.subscribe(e -> System.out.println("onNext: " + e));


        // output file
        Path opPath = Paths.get("~/Desktop/large-output-file.txt");
        BufferedWriter bw = Files.newBufferedWriter(opPath, StandardOpenOption.CREATE, StandardOpenOption.APPEND);

//        stringFlux.subscribe(s -> write(bw, s),
//                (e) -> close(bw),  // close file if error / oncomplete
//                () -> close(bw)
//        );

        TimeUnit.HOURS.sleep(1);

    }

//
//    public static boolean isPrime(int i){
//
//        return true;
//    }

    // private methods to handle checked exceptions

    private static void close(Closeable closeable) {
        try {
            closeable.close();
            System.out.println("Closed the resource");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static void write(BufferedWriter bw, String string) {
        try {
            bw.write(string);
            bw.newLine();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
