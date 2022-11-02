package rxjava;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rx.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FlapMapOperator {

    public static void main(String[] args) {

        List<User> veryLargeList = new User().getAll();

        Observable<Profile> profilesObservable = Observable
                .from(veryLargeList)
                .flatMap(User::loadProfile, 10);


        profilesObservable.map(s -> Thread.currentThread().getName() + " " + s).subscribe(System.out::println);

        sleep(1, TimeUnit.MINUTES);

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class User {

        private int userId;

        public List<User> getAll() {

//            return new Random().ints(1000000L, Integer.MIN_VALUE, Integer.MAX_VALUE)
//                    .boxed()
//                    .map(User::new)
//                    .collect(Collectors.toList());

            return IntStream.range(1, 1000001)
                    .boxed()
                    .map(User::new)
                    .collect(Collectors.toList());
        }

        public Observable<Profile> loadProfile() {
            //Make HTTP request...

            // simulate HTTP request
            Observable<Profile> profileObservable = Observable.create(subscriber -> {

                Profile profile = new Profile("profile info for user " + userId + " goes here");

                subscriber.onNext(profile);

                Random r = new Random();
                int delay = r.nextInt(500) + 1;
                if(userId %10 == 0) delay = 1000 * 60;

                sleep(delay, TimeUnit.MILLISECONDS);
                subscriber.onCompleted();

            });

            return profileObservable;
        }

        private void sleep(int timeout, TimeUnit unit) {
            try {
                unit.sleep(timeout);
            } catch (Exception e) {
            }
        }

    }

    @Data
    @AllArgsConstructor
    public static class Profile {/* ... */
        private String profileInfo;
    }


    private static void sleep(int timeout, TimeUnit unit) {
        try {
            unit.sleep(timeout);
        } catch (Exception e) {
        }
    }
}
