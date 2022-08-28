package stream;

import java.util.*;
import java.util.stream.Stream;

public class StreamMain7 {

    public static void main(String[] args) {

//        Set<String> set = new HashSet<>(Arrays.asList("xxxx", "Anaa", "xxxx", "AAnty", "nAxdr", "Howa"));

        Map<String, Integer> map = Map.of("xxxx", 4, "Anaaa", 5, "xxxxxxx", 7, "AAntyr", 6,"nAxdrdfrt", 8, "Howa", 4);

        // error
//        Stream<String> stream = map.stream();

        Stream<String> streamK = map.keySet().stream();
        Stream<Integer> streamV = map.values().stream();
        Stream<Map.Entry<String, Integer>> stream = map.entrySet().stream();

        Stream<String> intermidiateStream = streamK.
                filter(symbol -> testing(symbol, 0, "From filter one"))
                .filter(symbol -> testing(symbol, 1, "From filter two"))
                .filter(symbol -> testing(symbol, 2, "From filter three"))
                .filter(symbol -> testing(symbol, 3, "From filter four"));


//        Optional<String> value = intermidiateStream.findFirst();
//        System.out.println("Result: " + value.get());

//        long value = intermidiateStream.count();
//        System.out.println("Result: " + value);

        long value = intermidiateStream.collect(null);
        System.out.println("Result: " + value);


    }

    private static boolean testing(String symbol, int index, String source) {

        System.out.println(Thread.currentThread().getName() + "---" + source + " -> " + symbol);
        return symbol.charAt(index) != 'A';
    }
}
