package rxjava;

import rx.Observable;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class FlapMapOperator2 {

    public static void main(String[] args) {

        Observable<LocalDate> nextTenDays = Observable.range(1, 10)
                .map(i -> LocalDate.now().plusDays(i));


        Observable<Vacation> possibleVacations = Observable
                .just(City.Warsaw, City.London, City.Paris)
                .flatMap(city -> nextTenDays.map(date -> new Vacation(city, date)));

        possibleVacations.subscribe(System.out::println);
    }




    public static enum City {
        Warsaw,
        London,
        Paris
    }

    public static class Vacation {
        private final City where;
        private final LocalDate when;

        Vacation(City where, LocalDate when) {
            this.where = where;
            this.when = when;
        }

        @Override
        public String toString() {
            return where + " " + when;
        }


//        public Observable<Weather> weather() {
//            //...
//            return null;
//        }
//        public Observable<Flight> cheapFlightFrom(City from) {
//            //...
//
//            return null;
//        }
//
//
//        public Observable<Hotel> cheapHotel() {
//            //...
//
//            return null;
//        }


    }
}
