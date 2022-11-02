package rxjava;

import rx.Observable;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

public class ObservablePublishAutoConnect {

    public static void main(String[] args) {

        Observable<BigInteger> autoConnectObservable = Observable.<BigInteger>create(
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

                }).publish().autoConnect();
        /*
        autoConnect(): Returns an Observable that automatically connects to this ConnectableObservable when the first Observer subscribes.
         */

        System.out.println("Start Sub1 ...");
        Subscription subscription1 = autoConnectObservable.subscribe(i -> System.out.println(Thread.currentThread().getName() + " -> " + i));

        System.out.println("Start Sub2 ...");
        Subscription subscription2 = autoConnectObservable.subscribe(i -> System.out.println(Thread.currentThread().getName() + " -> " + i));

        // sleep some time to let it emit some data ...
        sleep(5, TimeUnit.SECONDS);

        System.out.println("Exit Sub1 ...");
        subscription1.unsubscribe();

        System.out.println("Exit Sub2 ...");
        subscription2.unsubscribe();

        // even after Un-subscription, the ConnectableObservable still emitting
        // sleep sometime to see that it will continue emition after un-subscription
        sleep(2, TimeUnit.SECONDS);

        // with  autoConnect()
        // Even all subscribers unSubscribe, observable will still be alive.

    }


    private static void sleep(int timeout, TimeUnit unit) {
        try {
            unit.sleep(timeout);
        } catch (Exception e) {
        }
    }


}
