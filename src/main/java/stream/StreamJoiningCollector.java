package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamJoiningCollector {

    public static void main(String[] args) {

//        Set<String> set = new HashSet<>(Arrays.asList("xxxx", "Anaa", "xxxx", "AAnty", "nAxdr", "Howa", "WIZO0", "kokololo", "koko"));
        List<String> list = Arrays.asList("xxxx", "Anaa", "0000", "xxxx", "AAnty", "nAxdr", "0000", "0000", "Howa", "WIZO0", "kokololo", "koko");


        Stream<String> intermidiateStream = list.stream()
                .filter(symbol -> testing(symbol, 0, "From filter one"))
                .filter(symbol -> testing(symbol, 1, "From filter two"))
                .filter(symbol -> testing(symbol, 2, "From filter three"))
                .filter(symbol -> testing(symbol, 3, "From filter four"));

//        String result = intermidiateStream.collect(Collectors.joining());
//        String result = intermidiateStream.collect(Collectors.joining(", "));
        String result = intermidiateStream.collect(Collectors.joining(", ", "[", "]"));


        System.out.println("=========================================");
        System.out.println("Result: " + result);


    }

    private static boolean testing(String symbol, int index, String source) {

        System.out.println(Thread.currentThread().getName() + "---" + source + " -> " + symbol);
        return symbol.charAt(index) != 'A';
    }


}
