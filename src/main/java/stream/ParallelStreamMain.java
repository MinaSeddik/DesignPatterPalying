package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ParallelStreamMain {

    public static void main(String[] args) {

        List<String> al = Arrays.asList("Ana", "AAnty", "Howa");


        Stream<String> stream = al.parallelStream();
//        Optional<String> t = stream.filter(symbol -> testing(symbol, "From filter one"))
//                .filter(symbol -> testing(symbol, "From filter two"))
//                .filter(symbol -> testing(symbol, "From filter three"))
//                .filter(symbol -> testing(symbol, "From filter four"))
//                .findFirst();

        Stream<String> intermidiateStream = stream.
                filter(symbol -> testing(symbol, "From filter one"))
                .filter(symbol -> testing(symbol, "From filter two"))
                .filter(symbol -> testing(symbol, "From filter three"))
                .filter(symbol -> testing(symbol, "From filter four"));
//                .findFirst();

        intermidiateStream.findFirst();

    }

    private static boolean testing(String symbol, String source) {

        System.out.println(Thread.currentThread().getName() + "  " + source + " -> " + symbol);
        return symbol.charAt(0) != 'A';
    }
}
