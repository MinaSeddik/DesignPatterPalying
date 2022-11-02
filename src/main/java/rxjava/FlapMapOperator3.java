package rxjava;

import rx.Observable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlapMapOperator3 {


    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        List<Integer> list2 = Arrays.asList(100, 200, 300);
        Observable<List<Integer>> observable = Observable.just(list, list2);
//        Observable<Integer> observable = Observable.from(list);

//        Observable<Integer> ob2 = observable.flatMap(Observable::from);
        Observable<Integer> ob3 = observable.flatMapIterable(x -> x);


        ob3.map(x -> x * 2 + " <>")
                .subscribe(System.out::println);

//        observable.flatMapIterable(x -> x)
//                .map(x -> x*2 + " <>")
//                .subscribe(System.out::println);

    }
}
