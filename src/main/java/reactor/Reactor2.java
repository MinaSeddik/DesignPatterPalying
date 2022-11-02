package reactor;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.TimeUnit;


public class Reactor2 {

    public static void main(String[] args) throws InterruptedException {

        Flux.range(0, 10000)
                .parallel()
                .runOn(Schedulers.parallel())
                .map(n -> mapper(n))
                .filter(n -> filter(n))
                .subscribe();

        TimeUnit.HOURS.sleep(1);

    }

    private static int mapper(Integer n) {

        System.out.println(Thread.currentThread().getName() + " :: mapper");
        return n * n;
    }

    private static boolean filter(Integer n) {

        System.out.println(Thread.currentThread().getName() + " :: filter");
        return n != 0;
    }

}
