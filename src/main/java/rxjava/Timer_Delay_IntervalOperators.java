package rxjava;

import rx.Observable;

import java.util.concurrent.TimeUnit;

public class Timer_Delay_IntervalOperators {

    public static void main(String[] args) {

        System.out.println("------------- Observable.timer -------------");

        /*
            Whenever we have the use-case in which we want to do the task after a particular span of time, we can use the Timer Operator.
         */

        Observable.timer(2, TimeUnit.SECONDS).flatMap(i -> {
            return Observable.create(subscriber -> {
                System.out.println("Inside here 1...");
                subscriber.onNext("MindOrks");
                subscriber.onNext("ssadgasdg");
                subscriber.onNext("MinsgsagddOrks");
                subscriber.onNext("MindfgasdfgdfgdfgfdsgOrks");
                subscriber.onCompleted();
            });
        }).subscribe(System.out::println);

        sleep(5, TimeUnit.SECONDS);

        System.out.println("------------- delay operator -------------");

        /*
            Whenever we have the use-case in which we want to do the task first as usual and delay only the emission for a particular span of time, we can use the Delay Operator.
         */

        Observable.create(subscriber -> {
                    System.out.println("Inside here 2...");
                    subscriber.onNext("XXXXXXXXXXx");
                    subscriber.onNext("yyyyyyyyyyyyyy");
                    subscriber.onCompleted();
                }).delay(2, TimeUnit.SECONDS)
                .subscribe(System.out::println);


        sleep(7, TimeUnit.SECONDS);

        System.out.println("------------- Observable.interval -------------");

        /*
            Whenever we have the use-case in which we want to do the task again and again with the particular time interval, we can use the Interval Operator.

            One more very important thing to notice is that all the Operators Timer, Delay, and Interval run on Schedulers.computation(). So we do not need to worry about that.
         */
        Observable.interval(0, 2, TimeUnit.SECONDS)
                .take(5)
                .flatMap(i -> {
            return Observable.create(subscriber -> {
                System.out.println("Inside here 3... i = " + i);
                subscriber.onNext("111111");
                subscriber.onNext("222222");
                subscriber.onNext("333333");
                subscriber.onCompleted();
            });
        }).subscribe(System.out::println);

//        One thing to notice: It will keep going on forever.

        sleep(30, TimeUnit.SECONDS);

    }

    private static void sleep(int timeout, TimeUnit unit) {
        try {
            unit.sleep(timeout);
        } catch (Exception e) {
        }
    }
}
