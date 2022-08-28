package stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamPartitioningCollector {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("xxxx", "Anaa", "0000", "xxxx", "AAnty", "nAxdr", "0000", "0000", "Howa", "WIZO0", "kokololo", "koko");


        Stream<String> intermidiateStream = list.stream()
                .filter(symbol -> testing(symbol, 0, "From filter one"))
                .filter(symbol -> testing(symbol, 1, "From filter two"))
                .filter(symbol -> testing(symbol, 2, "From filter three"))
                .filter(symbol -> testing(symbol, 3, "From filter four"));

        Map<Boolean, List<String>> result = intermidiateStream.collect(Collectors.partitioningBy(s -> s.length() > 4));


        System.out.println("=========================================");
        System.out.println("Result: " + result);


    }

    private static boolean testing(String symbol, int index, String source) {

        System.out.println(Thread.currentThread().getName() + "---" + source + " -> " + symbol);
        return symbol.charAt(index) != 'A';
    }


}
