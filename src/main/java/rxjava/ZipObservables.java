package rxjava;

import rx.Observable;

import java.util.concurrent.TimeUnit;

public class ZipObservables {

    public static void main(String[] args) {

        Observable<String> a = Observable.create(s -> {
            System.out.println("Subscribed in a");
            s.onNext("one");
            sleep(100, TimeUnit.MILLISECONDS);
            s.onNext("two");
            sleep(100, TimeUnit.MILLISECONDS);
            s.onNext("three");
            sleep(100, TimeUnit.MILLISECONDS);
            s.onNext("four");
            s.onCompleted();
        });

        Observable<String> b = Observable.create(s -> {
            System.out.println("Subscribed in b");
            s.onNext("five");
            sleep(1, TimeUnit.SECONDS);
            s.onNext("six");
            sleep(3, TimeUnit.SECONDS);
            s.onNext("seven");
            s.onCompleted();
        });


        Observable<String> ab = Observable.zip(a, b, (x, y) -> x + "  -  " + y);
        ab.subscribe(s -> System.out.println(s), System.out::println, () -> System.out.println("Completed!"));

//        sleep(1, TimeUnit.SECONDS);
//
//        Observable<String> cd = Observable.merge(c, d);
//        cd.subscribe(System.out::println);
//        sleep(1, TimeUnit.SECONDS);


//        Observable error = Observable.error(new RuntimeException("just 4 test"));
//        Observable<String> ab2 = Observable.mergeDelayError(a, error, b);
//        ab2.subscribe(System.out::println, System.out::println);


        sleep(1, TimeUnit.MINUTES);

    }

    private static void sleep(int timeout, TimeUnit unit) {
        try {
            unit.sleep(timeout);
        } catch (Exception e) {
        }
    }

}
