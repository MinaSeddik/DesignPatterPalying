package rxjava;

import io.reactivex.Observable;


import java.util.concurrent.TimeUnit;

public class MergeObservables2 {

    public static void main(String[] args) {

        Observable<String> a = Observable.create(s -> {
            System.out.println("Subscribed in a");
            s.onNext("one");
            s.onNext("two");
            s.onNext("three");
            s.onNext("four");
            s.onComplete();
        });

        Observable<String> b = Observable.create(s -> {
            System.out.println("Subscribed in b");
            s.onNext("five");
            s.onNext("six");
            s.onNext("seven");
            s.onComplete();
        });

        Observable<String> c = Observable.create(s -> {
            new Thread(() -> {
                System.out.println("Subscribed in c");
                s.onNext("one*");
                s.onNext("two*");
                s.onNext("three*");
                s.onNext("four*");
                s.onComplete();
            }).start();
        });
        Observable<String> d = Observable.create(s -> {
            new Thread(() -> {
                System.out.println("Subscribed in d");
                s.onNext("five*");
                s.onNext("six*");
                s.onNext("seven*");
                s.onComplete();
            }).start();
        });

//        Observable<String> ab = Observable.merge(a, b);
//        ab.subscribe(System.out::println);
//        sleep(1, TimeUnit.SECONDS);

//        Observable<String> cd = Observable.merge(c, d);
//        cd.subscribe(System.out::println);
//        sleep(1, TimeUnit.SECONDS);

//        a.mergeWith(b);
//        a.zipWith(b, (i,j) -> "dd");

//        a = a.doOnSubscribe(() -> System.out.println("a subscribed!"));
//        b = b.doOnSubscribe(() -> System.out.println("b subscribed!"));
//
        Observable error = Observable.error(new RuntimeException("just 4 test"));
//        error = error.doOnSubscribe(() -> System.out.println("Error subscribed!"));
//
        Observable<String> ab2 = Observable.mergeDelayError(a, error, b)
                .onErrorResumeNext(Observable.just("Final"));
//        ab2.subscribe(System.out::println, th -> System.out.println(th), () -> System.out.println("completed"));

//        System.out.println("---------------------------------");
//
//
        Observable<String> ab3 = ab2.map(s -> "_" + s + "_");
//        ab3.subscribe(System.out::println, th -> System.out.println(th), () -> System.out.println("completed"));

        System.out.println("---------------------------------");

        Observable
                .interval(1, TimeUnit.SECONDS)
                .flatMap(i -> ab3)
                .subscribe((String zero) -> System.out.println(zero));

//        Observable.fromIterable()

//        Observable<String> data = Observable.just("one", "two", "three", "four", "five");
//        Observable<Long> interval = Observable.interval(1L, TimeUnit.SECONDS).skip(1);
//
//        BlockingObservable<String> result = Observable
//                .zip(data, interval, (strData, tick) -> String.format("[%d]=%s", tick, strData))
//                .toBlocking();
//
//        result.subscribe(System.out::println, System.out::println);

        sleep(13, TimeUnit.SECONDS);

    }

    private static void sleep(int timeout, TimeUnit unit) {
        try {
            unit.sleep(timeout);
        } catch (Exception e) {
        }
    }
}
