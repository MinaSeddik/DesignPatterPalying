package rxjava;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DefaultSubscriber;

import java.util.concurrent.TimeUnit;


public class Backpressure2 {

    public static void main(String[] args) throws InterruptedException {

        Flowable<Integer> observable = Flowable.range(1, 1_000_000_000);

        observable.subscribeOn(Schedulers.computation())
                .subscribe(new DefaultSubscriber<Integer>() {
            @Override
            public void onStart() {
                request(1);
            }

            @Override
            public void onNext(Integer t) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("item" + t);
                request(1);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(" " + t);
            }

            @Override
            public void onComplete() {
                System.out.println("Completed!");
            }
        });


        TimeUnit.HOURS.sleep(5);
    }
}
