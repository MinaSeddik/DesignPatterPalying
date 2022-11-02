package stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MergeStreams {

    public static void main(String[] args) {

        Stream<Integer> stream1 = Stream.of(1, 3, 5);
        Stream<Integer> stream2 = Stream.of(2, 4, 6);

        Stream<Integer> resultingStream = Stream.concat(stream1, stream2);
        List<Integer> list = resultingStream.collect(Collectors.toList());

        System.out.println(list);

    }
}
