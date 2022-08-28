package stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class ParallelStreamMain5 {

    public static void main(String[] args) {

        List<String> al = Arrays.asList( "Anaa", "AAnty", "nAxdr", "xxxx", "Howa");


        Stream<String> stream = al.parallelStream();

        Stream<String> intermidiateStream = stream.
                filter(symbol -> testing(symbol, 0, "From filter one"))
                .filter(symbol -> testing(symbol, 1, "From filter two"))
                .filter(symbol -> testing(symbol, 2, "From filter three"))
                .filter(symbol -> testing(symbol, 3, "From filter four"));


        Optional<String> value = intermidiateStream.findFirst();
        System.out.println("Result: " + value.get());

//        long value = intermidiateStream.count();
//        System.out.println("Result: " + value);
    }

    private static boolean testing(String symbol, int index, String source) {

        System.out.println(Thread.currentThread().getName() + "---" + source + " -> " + symbol);
        return symbol.charAt(index) != 'A';
    }
}
