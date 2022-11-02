package rxjava;

import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Backpressure {


    public static void main(String[] args) throws InterruptedException {

        PublishSubject<Integer> source = PublishSubject.<Integer>create();
//        Observable<Integer> source = Observable.create(subscriber -> {
//            IntStream.range(1, 1_000_000).forEach(subscriber::onNext);
//        });

//        source.buffer(1024)
//                .observeOn(Schedulers.computation())
//                .subscribe(ComputeFunction::compute2, Throwable::printStackTrace);


//        source.window(500)
//                .flatMap(o -> o)
//                .observeOn(Schedulers.computation())
//                .subscribe(ComputeFunction::compute, Throwable::printStackTrace);

        source.sample(200, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.computation())
                .subscribe(ComputeFunction::compute, Throwable::printStackTrace);

//        source.throttleFirst(5, TimeUnit.MILLISECONDS)
//                .onBackpressureBuffer(512, () -> {}, BackpressureOverflow.ON_OVERFLOW_DROP_OLDEST)
//                .observeOn(Schedulers.computation())
//                .subscribe(ComputeFunction::compute, Throwable::printStackTrace);


        IntStream.range(1, 1_000_000_000).forEach(source::onNext);

        TimeUnit.HOURS.sleep(5);
    }
}
