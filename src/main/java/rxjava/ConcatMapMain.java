package rxjava;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ConcatMapMain {


    public static void main(String[] args) throws InterruptedException {

        Random rnd = new Random();

//        Observable<Temperature> ob = Observable
//                .range(0, Integer.MAX_VALUE)
//                .concatMap(tick -> Observable
//                        .just(tick)
////                        .doOnNext(System.out::println)
//                        .delay(rnd.nextInt(5000), TimeUnit.MILLISECONDS)
//                        .map(tickValue -> new Temperature(16 + rnd.nextGaussian() * 10)))
////                        .doOnNext(s -> System.out.println("--> " + s)))
//                .publish()
//                .refCount().take(10);
//        ob.subscribe(System.out::println);


//        Observable.interval(1, TimeUnit.SECONDS)
//                .concatMap(tick -> Observable.just(rnd.nextInt(100)))
//                .take(10)
//                .subscribe(System.out::println,System.out::println, () -> System.out.println("Completed!"));
//        System.out.println("-------------------------");

//        Flowable<Integer> ob = Flowable.range(1, 1_000_000);
//        PublishSubject<Integer> ob = PublishSubject.<Integer>create();
//        Observable<Integer> ob = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Observable<Long> ob = Observable.rangeLong(1L, 1000_000_000L);

//        ob.window(3)
//                .subscribeOn(Schedulers.computation())
//                .concatMap(obs -> obs.reduce((x, y) -> x+y).toObservable())
//                .take(10)
//                .subscribe(System.out::println);
//
//        ob.window(10, TimeUnit.MILLISECONDS)
//                .subscribeOn(Schedulers.computation())
//                .concatMap(obs -> obs.reduce((x, y) -> x+y).toObservable())
//                .take(10)
//                .subscribe(System.out::println);


//        ob.buffer(10)
//                .subscribeOn(Schedulers.computation())
//                .concatMap(obs -> Observable.just(obs.stream().reduce(0L, (a, b) -> a + b)))
//                .take(10)
//                .subscribe(System.out::println);

//                ob.sample(10, TimeUnit.MILLISECONDS)
//                .subscribeOn(Schedulers.computation())
//                .take(10)
//                .subscribe(System.out::println);

        ob.throttleFirst(10, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.computation())
                .take(10)
                .subscribe(System.out::println);

        TimeUnit.HOURS.sleep(2);

    }
}
