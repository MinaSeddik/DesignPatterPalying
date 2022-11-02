package rxjava;

import rx.Observable;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

public class BeginnerMain2 {

    public static void main(String[] args) {


        Observable<BigInteger> observable = Observable.<BigInteger>create(
                subscriber -> {
                    Runnable r = () -> {
                        BigInteger i = BigInteger.ZERO;

                        while (!subscriber.isUnsubscribed()) {
                            System.out.println("---------------->>>> Will emit " + i);
                            subscriber.onNext(i);
                            i = i.add(BigInteger.ONE);
                        }
                    };
                    new Thread(r).start();

                    subscriber.add(Subscriptions.create(() -> System.out.println("------------------------------------------------------------------Clear resources")));


                }).publish().refCount().map(i -> i.multiply(new BigInteger("10")));


        System.out.println("Start Sub1 ...");
        Subscription subscription1 = observable.subscribe(i -> System.out.println(Thread.currentThread().getName() + " -> " + i));

        System.out.println("Start Sub2 ...");
        Subscription subscription2 = observable.subscribe(i -> System.out.println(Thread.currentThread().getName() + " -> " + i));

        Subscription subscription3 = observable.subscribe(i -> System.out.println(Thread.currentThread().getName() + " -> " + i));
        Subscription subscription4 = observable.subscribe(i -> System.out.println(Thread.currentThread().getName() + " -> " + i));

        sleep(5, TimeUnit.SECONDS);
        subscription1.unsubscribe();

        sleep(5, TimeUnit.SECONDS);
        subscription2.unsubscribe();

        sleep(5, TimeUnit.SECONDS);
        subscription3.unsubscribe();

        sleep(5, TimeUnit.SECONDS);
        Subscription subscription5 = observable.subscribe(i -> System.out.println(Thread.currentThread().getName() + " -> " + i));

        sleep(8, TimeUnit.SECONDS);
        subscription4.unsubscribe();

        sleep(5, TimeUnit.SECONDS);
        subscription5.unsubscribe();


        System.out.println("===================");


    }


    private static void sleep(int timeout, TimeUnit unit) {
        try {
            unit.sleep(timeout);
        }catch (Exception e){}
    }


}
