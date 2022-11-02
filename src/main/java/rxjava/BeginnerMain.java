package rxjava;

import rx.Observable;
import rx.Single;
import rx.Subscriber;
import rx.Subscription;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;

import java.math.BigInteger;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BeginnerMain {

    public static void main(String[] args) throws InterruptedException {

        Observable<String> observable = Observable.just("Hello1", "Hello2", "Hello3", "Hello4", "Hello5", "Hello6", "Hello7");

        observable.subscribe(s ->
                System.out.println(Thread.currentThread().getName() + " -> " + s));

        System.out.println("===================");

        observable.subscribe(s ->
                System.out.println(Thread.currentThread().getName() + " -> " + s));

        System.out.println("===================");

        observable.subscribe(
                s -> System.out.println(Thread.currentThread().getName() + " :: onNext -> " + s),  //OnNext
                throwable -> System.out.println(Thread.currentThread().getName() + " :: OnError -> " + throwable.getMessage()), //OnError
                () -> System.out.println(Thread.currentThread().getName() + " :: OnCompleted")  //OnCompleted
        );

        System.out.println("===================");

        observable.map(s -> "_" + s + "_").subscribe(
                s -> System.out.println(Thread.currentThread().getName() + " :: onNext -> " + s),  //OnNext
                throwable -> System.out.println(Thread.currentThread().getName() + " :: OnError -> " + throwable.getMessage()), //OnError
                () -> System.out.println(Thread.currentThread().getName() + " :: OnCompleted")  //OnCompleted
        );


        System.out.println("===================");


        Observable<String> observable2 = observable.map(s -> {
                    System.out.println(Thread.currentThread().getName() + ", inside mapper");
                    return "#" + s + "#";
                })
                .filter(s -> !s.endsWith("3#"));


        observable2.subscribe(
                s -> System.out.println(Thread.currentThread().getName() + " :: onNext -> " + s),  //OnNext
                throwable -> System.out.println(Thread.currentThread().getName() + " :: OnError -> " + throwable.getMessage()), //OnError
                () -> System.out.println(Thread.currentThread().getName() + " :: OnCompleted")  //OnCompleted
        );

        System.out.println("===================");

        Observable<String> observable3 = observable.map(s -> {
                    System.out.println(Thread.currentThread().getName() + ", inside mapper");
                    return "@" + s + "@";
                })
                .filter(s -> !s.endsWith("5@"));

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

        executor.submit(() -> {
            observable2.subscribe(
                    s -> System.out.println(Thread.currentThread().getName() + " :: onNext -> " + s),  //OnNext
                    throwable -> System.out.println(Thread.currentThread().getName() + " :: OnError -> " + throwable.getMessage()), //OnError
                    () -> System.out.println(Thread.currentThread().getName() + " :: OnCompleted")  //OnCompleted
            );
        });

        System.out.println("===================");

        Observable<Integer> o = Observable.create(s -> {
            s.onNext(1);
            s.onNext(2);
            s.onNext(3);
            s.onCompleted();
        });
        o.map(i -> "Number " + i)
                .subscribe(s -> System.out.println(s));

        System.out.println("===================");

        Observable<String> o1 = Observable.create(s -> {
            new Thread(() -> {
                s.onNext("one");
                s.onNext("two");
                s.onNext("three");
                s.onNext("four");
                s.onCompleted();
            }).start();
        });

        Observable<String> o2 = Observable.create(s -> {
            System.out.println(Thread.currentThread().getName() + " -> Hey ... ");
            new Thread(() -> {
                s.onNext("XXXone");
                s.onNext("XXXtwo");
                s.onNext("XXXthree");
                s.onNext("XXXfour");
                s.onCompleted();
            }).start();
        }).map(s -> {
            System.out.println(Thread.currentThread().getName() + " mapper");
            return s + "XXX";
        });

        o1.subscribe(s -> System.out.println(Thread.currentThread().getName() + " -> " + s));
        o1.subscribe(s -> System.out.println(Thread.currentThread().getName() + " -> " + s));
        o1.subscribe(s -> System.out.println(Thread.currentThread().getName() + " -> " + s));
        o1.subscribe(s -> System.out.println(Thread.currentThread().getName() + " -> " + s));
        o2.subscribe(s -> System.out.println(Thread.currentThread().getName() + " -> " + s));
        o2.subscribe(s -> System.out.println(Thread.currentThread().getName() + " -> " + s));
        o1.subscribe(s -> System.out.println(Thread.currentThread().getName() + " -> " + s));

        System.out.println("===================");

        // DO NOT DO THIS
        Observable<String> badObservable = Observable.create(s -> {
            // Thread A
            new Thread(() -> {
                s.onNext("one");
                s.onNext("two");
            }).start();
            // Thread B
            new Thread(() -> {
                s.onNext("three");
                s.onNext("four");
            }).start();
            // ignoring need to emit s.onCompleted() due to race of threads
        });
        // DO NOT DO THIS

        badObservable.subscribe(s -> System.out.println(Thread.currentThread().getName() + " -> " + s));

        System.out.println("===================");

        Observable<String> a = Observable.create(s -> {
            new Thread(() -> {
                s.onNext("one");
                s.onNext("two");
                s.onCompleted();
            }).start();
        });
        Observable<String> b = Observable.create(s -> {
            new Thread(() -> {
                s.onNext("three");
                s.onNext("four");
                s.onCompleted();
            }).start();
        });

        // this subscribes to a and b concurrently,
        // and merges into a third sequential stream
        Observable<String> c = Observable.merge(a, b);

        c.subscribe(s -> System.out.println(Thread.currentThread().getName() + " -> " + s));

        System.out.println("===================");

        Observable<Integer> ox = Observable.create(s -> {
            s.onNext(1);
            s.onNext(2);
            s.onError(new RuntimeException("hahahahahahahahahah"));
            s.onNext(3);
            s.onCompleted();
        });

        ox.subscribe(s -> System.out.println(s), ex -> System.out.println("Ex : " + ex));

        System.out.println("===================");

        Single<String> single = Single.<String>create(obj -> {
            obj.onSuccess("DataA");
        }).subscribeOn(Schedulers.io());

        Subscription subscription = single.subscribe(s -> System.out.println(Thread.currentThread().getName() + " -> " + s));

        System.out.println("===================");


        Observable<Integer> ints = Observable
                .create(new Observable.OnSubscribe<Integer>() {
                    @Override
                    public void call(Subscriber<? super Integer> subscriber) {
                        System.out.println(Thread.currentThread().getName() + " -> " + "Create");
                        System.out.println(Thread.currentThread().getName() + " -> will emit 5");
                        subscriber.onNext(5);
                        System.out.println(Thread.currentThread().getName() + " -> will emit 6");
                        subscriber.onNext(6);
                        System.out.println(Thread.currentThread().getName() + " -> will emit 7");
                        subscriber.onNext(7);
                        System.out.println(Thread.currentThread().getName() + " -> will be completed!");
                        subscriber.onCompleted();
                        System.out.println(Thread.currentThread().getName() + " -> " + "Completed");
                    }
                });

        System.out.println(Thread.currentThread().getName() + " -> " + "Starting");
        ints.subscribe(i -> System.out.println(Thread.currentThread().getName() + " -> " + "Element: " + i));
        System.out.println(Thread.currentThread().getName() + " -> " + "Exit");

        System.out.println("===================");


        Observable<Integer> ints2 = Observable
                .create(new Observable.OnSubscribe<Integer>() {
                    @Override
                    public void call(Subscriber<? super Integer> subscriber) {
                        System.out.println(Thread.currentThread().getName() + " -> " + "Create");
                        System.out.println(Thread.currentThread().getName() + " -> will emit 5");
                        subscriber.onNext(5);
                        System.out.println(Thread.currentThread().getName() + " -> will emit 6");
                        subscriber.onNext(6);
                        System.out.println(Thread.currentThread().getName() + " -> will emit 7");
                        subscriber.onNext(7);
                        System.out.println(Thread.currentThread().getName() + " -> will be completed!");
                        subscriber.onCompleted();
                        System.out.println(Thread.currentThread().getName() + " -> " + "Completed");
                    }
                }).map(i -> i*i).subscribeOn(Schedulers.io());

        System.out.println(Thread.currentThread().getName() + " -> " + "Starting");
        ints2.subscribe(i -> System.out.println(Thread.currentThread().getName() + " -> " + "Element: " + i));
        System.out.println(Thread.currentThread().getName() + " -> " + "Exit");

        sleep(500, TimeUnit.MILLISECONDS);

        System.out.println("===================");

        Observable<Integer> ints4 =
                Observable.create(subscriber -> {
                    System.out.println("Create");
                            subscriber.onNext(42);
                            subscriber.onCompleted();
                        }
                );
        System.out.println("Starting");
        ints4.subscribe(i -> System.out.println(Thread.currentThread().getName() + " -> " + "Element A: " + i));
        ints4.subscribe(i -> System.out.println(Thread.currentThread().getName() + " -> " + "Element B: " + i));
        System.out.println("Exit");


        System.out.println("===================");

        Observable<Integer> ints5 =
                Observable.create(subscriber -> {
                            System.out.println("Create");
                            subscriber.onNext(42);
                            subscriber.onCompleted();
                        }
                );

        Observable<Integer> ints6  = ints5.cache();
        System.out.println("Starting");
        ints6.subscribe(i -> System.out.println(Thread.currentThread().getName() + " -> " + "Element X: " + i));
        ints6.subscribe(i -> System.out.println(Thread.currentThread().getName() + " -> " + "Element Y: " + i));
        System.out.println("Exit");

        System.out.println("===================");


        Observable<BigInteger> naturalNumbers = Observable.create(
                subscriber -> {
                    BigInteger i = BigInteger.ZERO;
                    while (true) { //don't do this!
                        subscriber.onNext(i);
                        i = i.add(BigInteger.ONE);
                    }
                });
//        naturalNumbers.subscribe(i -> System.out.println(Thread.currentThread().getName() + " -> " + i));



        System.out.println("===================");

        Observable<BigInteger> naturalNumbers1 = Observable.create(
                subscriber -> {
                    Runnable r = () -> {
                        BigInteger i = BigInteger.ZERO;

                        while (!subscriber.isUnsubscribed()) { //don't do this!
                            subscriber.onNext(i);
                            i = i.add(BigInteger.ONE);
                        }
                    };
                    new Thread(r).start();
                });
        Subscription subscription1 = naturalNumbers1.subscribe(i -> System.out.println(Thread.currentThread().getName() + " -> " + i));

        sleep(700, TimeUnit.MILLISECONDS);
        subscription1.unsubscribe();

        System.out.println("===================");










        sleep(1, TimeUnit.SECONDS);

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);

    }

    private static void sleep(int timeout, TimeUnit unit) {
        try {
            unit.sleep(timeout);
        }catch (Exception e){}
    }

}


