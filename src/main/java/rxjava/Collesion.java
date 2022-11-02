package rxjava;


import io.reactivex.Observable;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Collesion {


    public static void main(String[] args) {

//        Observable<String> observable = Observable
//                .just("Lorem", "ipsum", "dolor", "sit", "amet",
//                        "consectetur", "adipiscing", "elit", "d")
//                .delay(word -> Observable.timer(word.length(), SECONDS));
//

//        observable.subscribe(System.out::println);

//
//        observable.just(10L, 1L)
//                .flatMap(x ->
//                        observable.just(x).delay(x, TimeUnit.SECONDS))
//                .subscribe(System.out::println);


//        Observable.combineLatest(
//                        Observable.interval(17, TimeUnit.SECONDS).doOnNext(i -> System.out.println("slow emits "+ i)).map(x -> "S" + x),
//                        Observable.interval(10, TimeUnit.SECONDS).doOnNext(i -> System.out.println("fast emits "+ i)).map(x -> "F" + x),
//                        (s, f) -> f + ":" + s
//                ).take(15)
//                .forEach(System.out::println);

//        Observable.range(1, 10)
//                .reduce(
//                        0,
//                        (resultSoFar, x) -> resultSoFar += x)
//                .subscribe(System.out::println);
//
//        Observable.range(1, 10)
//                .collect(
//                        StringBuilder::new,
//                        (sb, x) -> sb.append(x).append(", "))
//                .map(StringBuilder::toString)
//                .subscribe(System.out::println);


//        Observable.range(1, 10)
//                .collect(
//                        ArrayList<Integer>::new,
//                        (list, x) -> list.add(x))
//                .subscribe(System.out::println);


//        Observable.range(1, 9)
//                .groupBy(i -> i % 3)
//                .flatMapSingle(f -> {  // GroupedObservable<Integer, List<Integer>>
//
//                    // Look output : all runs on same thread,
//                    System.out.println("flatMapSingle key : " + f.getKey());
////                    System.out.println("flatMapSingle key : " + f.);
//
//                    // "onComplete" has not been triggered.
//                    // blockingGet will block current thread.
////                    return Observable.just(Collections.singletonMap(f.getKey(), f.toList().blockingGet()));
//
//                    return f.collect(
//                            // (Callable<Map<Integer, List<Integer>>>)
//                            () -> Collections.singletonMap(f.getKey(), new ArrayList<Integer>()),
//
//                            // (BiConsumer<Map<Integer, List<Integer>>, Integer>)
//                            (m, i) -> m.get(f.getKey()).add(i)
//                    );
//
//                })
//                .subscribe(System.out::println);


//        Observable<GroupedObservable<Integer, Integer>> grouped = Observable.range(1, 9)
//                .groupBy(i -> i % 3);


//        grouped.subscribe(byUuid -> {
//            System.out.println(byUuid.getKey());
//            System.out.println(byUuid);
////            byUuid.subscribe(x -> System.out.println(x));
//        });

//        Observable<Boolean> trueFalse = Observable.just(true, false).repeat().take(9);
//        trueFalse.subscribe(System.out::println);


        Observable<List<Integer>> bufferObs = Observable.range(0, 10)
                .buffer(3);


        Observable<Integer> bufferObs2 = bufferObs.concatMapIterable(x -> x);
        bufferObs2.subscribe(System.out::println, System.out::println, () -> System.out.println("Completed!"));


        sleep(10, TimeUnit.MINUTES);
    }

    private static void sleep(int timeout, TimeUnit unit) {
        try {
            unit.sleep(timeout);
        } catch (Exception e) {
        }
    }

}
