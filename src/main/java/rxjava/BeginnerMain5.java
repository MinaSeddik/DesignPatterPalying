package rxjava;

import rx.Observable;

import java.util.concurrent.TimeUnit;

public class BeginnerMain5 {

    public static void main(String[] args) {

        Observable timerObservable = Observable.timer(1, TimeUnit.SECONDS);
        timerObservable.subscribe(i -> System.out.println(i));

        System.out.println("--------------------------");

        Observable delayObservable = Observable.just("x1", "x2", "x3")
                .delay(3, TimeUnit.SECONDS);
        delayObservable.subscribe(i -> System.out.println(i));


        System.out.println("--------------------------");

        Observable.just(10L, 1L)
                .flatMap(x ->
                        Observable.just(x).delay(x, TimeUnit.SECONDS))
                .subscribe(System.out::println);

        sleep(10, TimeUnit.MINUTES);
    }


    private static void sleep(int timeout, TimeUnit unit) {
        try {
            unit.sleep(timeout);
        } catch (Exception e) {
        }
    }

}
