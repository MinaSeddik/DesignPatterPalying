package stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class StreamReduce {


    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        /*
            reduce stream

            1. identity: the initial value of the reduction operation
            2. Accumulator: a function that takes two parameters: a partial result of the reduction operation and the next element of the stream
            2. Combiner: function used to combine the partial result of the reduction operation
         */


        int total = numbers
                .parallelStream()
                .reduce(0 /* identity */, (subtotal, element) -> subtotal + element);
        System.out.println("Total:" + total);

        int min = numbers
                .parallelStream()
                .reduce(numbers.get(0) , (minSoFar, element) -> element < minSoFar ? element: minSoFar);
        System.out.println("Min:" + min);

        int max = numbers
                .parallelStream()
                .reduce(numbers.get(0) , (maxSoFar, element) -> element > maxSoFar ? element: maxSoFar);
        System.out.println("Max:" + max);


        int multiplication = numbers
                .parallelStream()
                .reduce(1 /* identity */, (subResult, element) -> subResult * element);
        System.out.println("Multiplication:" + multiplication);

    }



}
