package rxjava;

import io.reactivex.Observable;
import io.reactivex.Single;
import rx.observables.BlockingObservable;

import java.util.List;

public class BlockingObs {


    public static void main(String[] args) {
//        Observable<String> peopleStream = listPeople();
//        Single<List<String>> peopleList = peopleStream.toList();
//        BlockingObservable<List<String>> peopleBlocking = peopleStream.toBlocking();
//        List<String> people = peopleBlocking.single();
//
//        System.out.println(people);


        Observable.defer(() ->
                Observable.fromArray());

    }

    private static Observable<String> listPeople() {

        return null;
    }
}
