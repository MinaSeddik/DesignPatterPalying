package rxjava;

import rx.Observable;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

public class ObservablePublishRefCount {

    public static void main(String[] args) {

        Observable<BigInteger> refCountObservable = Observable.<BigInteger>create(
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

                }).publish().refCount();
        /*
            refCount(): Returns an Observable that stays connected to this ConnectableObservable as long as there is at least one subscription to this ConnectableObservable
         */

        System.out.println("Start Sub1 ...");
        Subscription subscription1 = refCountObservable.subscribe(i -> System.out.println(Thread.currentThread().getName() + " -> " + i));

        System.out.println("Start Sub2 ...");
        Subscription subscription2 = refCountObservable.subscribe(i -> System.out.println(Thread.currentThread().getName() + " -> " + i));

        System.out.println("Start Sub3 ...");
        Subscription subscription3 = refCountObservable.subscribe(i -> System.out.println(Thread.currentThread().getName() + " -> " + i));


        // sleep some time to let it emit some data ...
        sleep(5, TimeUnit.SECONDS);

        System.out.println("Exit Sub1 ...");
        subscription1.unsubscribe();

        sleep(5, TimeUnit.SECONDS);
        System.out.println("Exit Sub2 ...");
        subscription2.unsubscribe();

        sleep(5, TimeUnit.SECONDS);
        System.out.println("Exit Sub3 ...");
        subscription3.unsubscribe();

        // After the Un-subscription of the last subscriber, the Observable will stop emitting
        // sleep sometime to see that it will stop emition after un-subscription
        sleep(5, TimeUnit.SECONDS);

    }


    private static void sleep(int timeout, TimeUnit unit) {
        try {
            unit.sleep(timeout);
        } catch (Exception e) {
        }
    }


}

