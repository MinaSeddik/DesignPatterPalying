package rxjava;


import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class MergeObservables {

    public static void main(String[] args) {

        CarPhoto photo = new CarPhoto();

        Observable<LicensePlate> all = Observable.merge(
                preciseAlgo(photo),
                fastAlgo(photo),
                experimentalAlgo(photo)
        ).subscribeOn(Schedulers.io());

        all.subscribe(System.out::println);

        System.out.println("--------------------------------");
//        Observable<LicensePlate> all2 = Observable.concat(
//                preciseAlgo(photo),
//                fastAlgo(photo),
//                experimentalAlgo(photo)
//        ).subscribeOn(Schedulers.io());
//
//        all2.subscribe(System.out::println);


        sleep(1, TimeUnit.MINUTES);

    }

    private static void sleep(int timeout, TimeUnit unit) {
        try {
            unit.sleep(timeout);
        } catch (Exception e) {
        }
    }

    public static Observable<LicensePlate> fastAlgo(CarPhoto photo) {
        //Fast but poor quality

        return Observable.create(subscriber -> {

                    sleep(100, TimeUnit.MILLISECONDS);
                    subscriber.onNext(new LicensePlate("fastAlgo Completed!"));
                    subscriber.onComplete();

                }
        );
    }

    public static Observable<LicensePlate> preciseAlgo(CarPhoto photo) {
        //Precise but can be expensive

        return Observable.create(subscriber -> {

                    sleep(5, TimeUnit.SECONDS);
                    subscriber.onNext(new LicensePlate("preciseAlgo Completed!"));
                    subscriber.onComplete();

                }
        );

    }

    public static Observable<LicensePlate> experimentalAlgo(CarPhoto photo) {
        //Unpredictable, running anyway

        return Observable.create(subscriber -> {

                    sleep(2, TimeUnit.SECONDS);
                    subscriber.onNext(new LicensePlate("experimentalAlgo Completed!"));
                    subscriber.onComplete();

                }
        );
    }

    public static class CarPhoto {
    }

    public static class LicensePlate {

        public String info;

        public LicensePlate(String info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return info;
        }
    }
}
