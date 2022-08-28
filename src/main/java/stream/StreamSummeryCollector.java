
package stream;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamSummeryCollector {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 1, 4, 5, 3, 3, 6, 7, 8, 7, 7, 3, 1, 3);

        IntStream stream = Arrays.stream(list.stream().mapToInt(i->i).toArray());

        // Using IntStream summaryStatistics()
        IntSummaryStatistics result = stream.summaryStatistics();

        System.out.println("=========================================");
        System.out.println("Result: " + result);


    }


}
