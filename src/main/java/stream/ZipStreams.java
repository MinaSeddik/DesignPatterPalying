package stream;

import com.google.common.collect.Streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ZipStreams {

    public static void main(String[] args) {


        List<String> names = new ArrayList<>(Arrays.asList("John", "Jane", "Jack", "Dennis"));

        List<Integer> ages = new ArrayList<>(Arrays.asList(24, 25, 27));


        Streams.zip(names.stream(), ages.stream(), (name, age) -> name + ":" + age)
                .forEach(System.out::println);



    }
}
