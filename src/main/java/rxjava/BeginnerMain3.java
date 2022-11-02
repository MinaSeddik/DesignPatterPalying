package rxjava;

import rx.Observable;
import rx.Subscription;
import rx.observables.ConnectableObservable;
import rx.subscriptions.Subscriptions;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

public class BeginnerMain3 {

    public static void main(String[] args) {

        Observable
                .just(8, 9, 10)
                .doOnNext(i -> System.out.println("A: " + i))
                .filter(i -> i % 3 > 0)
                .doOnNext(i -> System.out.println("B: " + i))
                .map(i -> "#" + i * 10)
                .doOnNext(s -> System.out.println("C: " + s))
                .filter(s -> s.length() < 4)
                .subscribe(s -> System.out.println("D: " + s));


        System.out.println("===================");

    }


    private static void sleep(int timeout, TimeUnit unit) {
        try {
            unit.sleep(timeout);
        }catch (Exception e){}
    }

}
