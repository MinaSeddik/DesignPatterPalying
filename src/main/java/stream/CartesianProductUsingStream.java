package stream;


import com.google.common.collect.Streams;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CartesianProductUsingStream {

    public static void main(String[] args) {

        LocalDate today = LocalDate.now();

        Stream<LocalDate> dateStream = IntStream.range(0, 10)
                .boxed()
                .map(i -> today.plusDays(i));

        List<LocalDate> dateList = dateStream.collect(Collectors.toList());

        Set<MyCity> allCity = EnumSet.allOf(MyCity.class);

//        allCity.stream()
//                .flatMap(city -> dateStream.map(d -> new MyVacation(city, d)))
//                .forEach(System.out::println);

        allCity.stream()
                .flatMap(city -> dateList.stream().map(d -> new MyVacation(city, d)))
                .forEach(System.out::println);


    }

    public enum MyCity {
        Warsaw,
        London,
        Paris
    }

    public static class MyVacation {
        private final MyCity where;
        private final LocalDate when;

        MyVacation(MyCity where, LocalDate when) {
            this.where = where;
            this.when = when;
        }

        @Override
        public String toString() {
            return where + " " + when;
        }
    }
}
