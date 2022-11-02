package rxjava;

import rx.Observable;
import rx.Subscription;
import rx.observables.ConnectableObservable;
import rx.subscriptions.Subscriptions;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

public class ObservablePublishConnect {

    public static void main(String[] args) {

        ConnectableObservable<BigInteger> observable = Observable.<BigInteger>create(
                subscriber -> {
                    System.out.println("will subscribe .. subscriber = " + subscriber);

                    BigInteger limit = new BigInteger("100000");

                    Runnable r = () -> {
                        BigInteger i = BigInteger.ZERO;

                        while (!subscriber.isUnsubscribed() && i.compareTo(limit) <= 0) {
                            System.out.println("---------------->>>> Will emit " + i);
                            subscriber.onNext(i);
                            i = i.add(BigInteger.ONE);

                            sleep(150, TimeUnit.MILLISECONDS);
                        }
                    };
                    new Thread(r).start();

                    subscriber.add(Subscriptions.create(() -> System.out.println("------------------------------------------------------------------Clear resources")));

                }).publish();


        System.out.println("Start Sub1 ...");
        Subscription subscription1 = observable.subscribe(i -> System.out.println(Thread.currentThread().getName() + " -> " + i));

        System.out.println("Start Sub2 ...");
        Subscription subscription2 = observable.subscribe(i -> System.out.println(Thread.currentThread().getName() + " -> " + i));

        // Nothing will happen until connect is called
        observable.connect();

        // try after connect(), they will miss some emitted events

//        System.out.println("Start Sub1 ...");
//        observable.subscribe(i -> System.out.println(Thread.currentThread().getName() + " -> " + i));
//        System.out.println("Start Sub2 ...");
//        observable.subscribe(i -> System.out.println(Thread.currentThread().getName() + " -> " + i));

        // sleep some time to let it emit some data ...
        sleep(5, TimeUnit.SECONDS);

        System.out.println("Exit Sub1 ...");
        subscription1.unsubscribe();

        System.out.println("Exit Sub2 ...");
        subscription2.unsubscribe();

        // even after Un-subscription, the ConnectableObservable still emitting
        // sleep sometime to see that it will continue emition after un-subscription
        sleep(2, TimeUnit.SECONDS);

        // with  connect()
        // Even all subscribers unSubscribe, observable will still be alive.

        /*

        autoConnect(): Returns an Observable that automatically connects to this ConnectableObservable when the first Observer subscribes.

        So even all subscribers unSubscribe, observable will still be alive.

        refCount(): Returns an Observable that stays connected to this ConnectableObservable as long as there is at least one subscription to this ConnectableObservable

        */
    }


    private static void sleep(int timeout, TimeUnit unit) {
        try {
            unit.sleep(timeout);
        } catch (Exception e) {
        }
    }


}
