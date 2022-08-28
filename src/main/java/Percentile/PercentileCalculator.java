package Percentile;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PercentileCalculator {

    public static void main(String[] args) {
        List<Integer> latencies = Arrays.asList(3, 6, 7, 300, 8, 9, 10, 13, 15, 16, 20 );
        Collections.sort(latencies);

        System.out.println(percentile(latencies, 25));
        System.out.println(percentile(latencies, 50));
        System.out.println(percentile(latencies, 75));
        System.out.println(percentile(latencies, 90));
        System.out.println(percentile(latencies, 95));
        System.out.println(percentile(latencies, 99));
        System.out.println(percentile(latencies, 100));
    }

    private static long percentile(List<Integer> latencies, double percentile) {
        int index = (int) Math.ceil(percentile / 100.0 * latencies.size());
        return latencies.get(index-1);
    }

}
