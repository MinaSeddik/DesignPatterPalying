package reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Reactor4 {

    public static void main(String[] args) throws InterruptedException {

        Flux.range(0, 10) //
                .flatMap(k -> //
                        Mono.subscriberContext() // (1)
                                .doOnNext(context -> { // (1.1)
                                    Map<Object, Object> map = context.get("randoms"); // (1.2)
//                                    map.put(k, new Random(k).nextGaussian()); //
                                    map.put(k, "_" + ((Number)k).toString() + "_"); //
                                }) //
                                .thenReturn(k) // (1.3)
                ) //
                .publishOn(Schedulers.parallel()) //
                .flatMap(k -> //
                        Mono.subscriberContext() // (2)
                                .map(context -> { //
                                    Map<Object, Object> map = context.get("randoms"); // (2.1)
                                    return map.get(k); // (2.2)
                                }) //
                ) //
                .subscriberContext(context -> // (3)
//                        context.put("randoms", new HashMap()) //
                        Context.of("randoms", new HashMap()) //
                ) //
//                .blockLast();
                .subscribe(System.out::println);


        TimeUnit.HOURS.sleep(1);
    }
}
